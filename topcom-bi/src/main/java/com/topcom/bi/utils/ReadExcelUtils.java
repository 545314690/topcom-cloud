package com.topcom.bi.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * 读取Excel
 *
 * @author lism
 */
public class ReadExcelUtils {
    private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public ReadExcelUtils(String filepath) {
        if (filepath == null) {
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @return String 表头内容的数组
     * @author lism
     */
    public String[] readExcelTitle() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
//        int colNum = row.getPhysicalNumberOfCells();//当有标题为空时，该函数不计算列数
        int colNum = row.getLastCellNum();
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            if (row.getCell(i) != null) {
                title[i] = row.getCell(i).getStringCellValue();
            } else {
                title[i] = "";
            }
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @return Map 包含单元格数据内容的Map对象
     * @author lism
     */
    public List<Map<String, Object>> readExcelContent(String[] header) throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        List<Map<String, Object>> content = new ArrayList<>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
//        int colNum = row.getPhysicalNumberOfCells();//当有标题为空时，该函数不计算列数
        int colNum = row.getLastCellNum();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<String, Object> cellValue = new LinkedHashMap<String, Object>();
            while (j < colNum) {
                if (row != null) {
                    Object obj = getCellFormatValue(row.getCell(j));
                    cellValue.put(header[j], obj);
                }
                j++;
            }
            content.add(cellValue);
        }
        return content;
    }

    /**
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author lism
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    public Map<String, String> toTxtFile(String path, List<Map<String, Object>> content) throws Exception {
        StringBuffer contentBuffer = new StringBuffer();
        for (Map<String, Object> map : content) {
            Collection<Object> values = map.values();
            Iterator<Object> iterator = values.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                Object next = iterator.next();
                contentBuffer.append(next);
                if (i == values.size() - 1) {
                    contentBuffer.append("\n");
                } else {
                    contentBuffer.append(",");
                }
                i++;
            }
        }
        File excelFile = new File(path);
        String parent = excelFile.getParent();
        File txtFile = new File(parent, excelFile.getName().substring(0, excelFile.getName().indexOf(".")) + ".txt");
        Map<String, String> map = new HashMap<>();
        map.put("filepath", txtFile.getAbsolutePath());
        map.put("content", contentBuffer.toString());
        return map;
    }

    public static void main(String[] args) {
        try {
            String filepath = "I:大学考试表.xls";
            ReadExcelUtils excelReader = new ReadExcelUtils(filepath);
//             对读取Excel表格标题测试
            String[] title = excelReader.readExcelTitle();
            System.out.println("获得Excel表格的标题:");
            for (String s : title) {
                System.out.print(s + " ");
            }

            // 对读取Excel表格内容测试
            List<Map<String, Object>> content = excelReader.readExcelContent(title);
            System.out.println("获得Excel表格的内容:");
            System.out.println(content);
            Map<String, String> map = excelReader.toTxtFile(filepath, content);
            System.out.println(map);
        } catch (FileNotFoundException e) {
            System.out.println("未找到指定路径的文件!");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}