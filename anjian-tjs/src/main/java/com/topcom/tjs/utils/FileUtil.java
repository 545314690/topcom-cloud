package com.topcom.tjs.utils;


//import org.apache.commons.io.output.FileWriterWithEncoding;

import org.apache.commons.io.output.FileWriterWithEncoding;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileUtil {
    /**
     * 按行读取文件，忽略空行
     *
     * @param fileName
     * @return
     */
    public static List<String> read(String fileName) {
        File dirFile = new File(fileName);
        if (!dirFile.exists()) {
            return new ArrayList<String>();
        }
        List<String> contents = new ArrayList<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String s = "";
            while ((s = br.readLine()) != null) {
                s.replace(" ", "");
                if (s.trim().equals(""))
                    continue;
                contents.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }

    /**
     * 按行读取文件，忽略空行
     *
     * @param file
     * @return
     */
    public static List<String> read(File file) {
        List<String> contents = new ArrayList<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    file), "UTF-8"));
            String s = "";
            while ((s = br.readLine()) != null) {
                s.replace(" ", "");
                if (s.trim().equals(""))
                    continue;
                contents.add(s);
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return contents;
    }

    public static List<String> read(BufferedReader br) {
        List<String> contents = new ArrayList<String>();

        try {
            String s = "";
            while ((s = br.readLine()) != null) {
                if (s.trim().equals(""))
                    continue;
                s.replace(" ", "");
                contents.add(s);
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return contents;
    }

    /**
     * @param str      要写的内容
     * @param fileName （文件路径）文件名
     * @param append   是否追加
     * @throws IOException
     */
    public synchronized static void write(String str, String fileName,
                                          boolean append) throws IOException {
        // 写入文件的url
        String urlcontent = null;
        BufferedWriter bw1;
        urlcontent = str;
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        bw1 = new BufferedWriter(new FileWriterWithEncoding(file, "UTF-8",
                append));
        bw1.write(urlcontent);
        bw1.flush();
        bw1.close();
    }

    /**
     * 写文件
     *
     * @param str      要写的内容
     * @param fileName （文件路径）文件名
     * @param code     编码
     * @param append   是否追加
     * @throws IOException
     */
    public static void write(String str, String fileName, String code,
                             boolean append) throws IOException {
        BufferedWriter bw1;
        str = str + '\n';
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        bw1 = new BufferedWriter(new FileWriterWithEncoding(file, code, append));
        bw1.write(str);
        bw1.flush();
        bw1.close();
    }

    /**
     * 根据文件名 获取文件内容
     *
     * @param fileName
     * @return
     */
    public static String getFileContent(String fileName) {

        BufferedReader reader = null;
        StringBuilder fileContent = new StringBuilder();
        try {
            File f = new File(fileName);
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(f), "UTF-8");
            reader = new BufferedReader(isr);
            String line = "";
            while ((line = reader.readLine()) != null) {
                fileContent.append(line);
                fileContent.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                    reader = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContent.toString();
    }



    /**
     * 按行读取文件，忽略空行
     *
     * @param fileName
     * @return
     */
    public static Set<String> readToSet(String fileName) {
        File dirFile = new File(fileName);
        if (!dirFile.exists()) {
            return new HashSet<String>();
        }
        Set<String> contents = new HashSet<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(fileName)));
            String s = "";
            while ((s = br.readLine()) != null) {
                s.replace(" ", "");
                if (s.trim().equals(""))
                    continue;
                contents.add(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contents;
    }
}
