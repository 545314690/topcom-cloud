package com.topcom.cms.yuqing.utils;

import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingCell;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class FreemarkerUtil {

    private static Configuration configuration = null;
    private static final String basePackagePath = "/com/topcom/cms/yuqing/template";

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
//        configuration.setDefaultEncoding("utf-8");
        //增加自定义函数，去除html标签
        configuration.setSharedVariable("cleanHtml", new CleanHtmlMethodModel());
    }

    public FreemarkerUtil() {
    }

    public static void main(String[] args) throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("D:\\data\\briefing.json"));
        Object object = HttpUtil.doGet("http://192.168.0.12:8081/briefingJson", "");

        JSONObject jsonObject = JSONObject.fromObject(lines.get(0));
        Briefing briefing = (Briefing) JSONObject.toBean(jsonObject, Briefing.class);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("briefing", jsonObject);

        FreemarkerUtil.processTemplateIntoFile(dataMap, new File("E:/outFile002.doc"), "brietNew.ftl");


//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("title","安全生产舆情");
//		dataMap.put("briefingType","月报");
//		dataMap.put("subTitle","2017年第2期");
//		dataMap.put("author","通信信息中心");
//		dataMap.put("pubTime","2017年2月");
//		dataMap.put("outline","事故发生少，没啥要说的，凑合着看吧！");
//		List<Map<String,Object>> briefOneList = new ArrayList<>();
//		for (int i=0;i<3;i++){
//			Map<String, Object> briefOneMap = new HashMap<String, Object>();
//			briefOneMap.put("title",i+"子标题");
//			briefOneMap.put("description","一级标题美图");
//			List<Map<String,Object>> brirfTwoList = new ArrayList<>();
//			for (int j=0;j<5;j++){
//				Map<String, Object> briefTwoMap = new HashMap<String, Object>();
//				briefTwoMap.put("title","图"+i+"-"+j);
//				briefTwoMap.put("img",img);
//				briefTwoMap.put("description","图片"+j+"的描述，假装有描述");
//				brirfTwoList.add(briefTwoMap);
//			}
//			briefOneMap.put("briefList",brirfTwoList);
//			briefOneList.add(briefOneMap);
//		}
//		dataMap.put("briefList",briefOneList);
//
//		FreemarkerUtil.processTemplateIntoFile(dataMap, "E:/outFile001.doc", "brietNew.ftl");
    }

    public static void processTemplateIntoFile(Briefing briefing, String filename,
                                               String templateName) throws Exception {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("title", briefing.getTitle());
        dataMap.put("subTitle", briefing.getIssue());
        dataMap.put("author", briefing.getAuthor());
        dataMap.put("pubTime", briefing.getCreateTime());
        dataMap.put("outline", briefing.getOutline());
        List<Map<String, Object>> briefOneList = new ArrayList<>();
        List briefingCellList = briefing.getBriefingBody();
//		if (briefingCellList.size()>0){
//			briefOneList = setBriefingCell(briefingCellList);
//			dataMap.put("briefList",briefOneList);
//		}
        for (int i = 0; i < briefingCellList.size(); i++) {
            JSONObject briefingCell = JSONObject.fromObject(briefingCellList.get(i));
            Map<String, Object> briefOneMap = new HashMap<String, Object>();
            briefOneMap.put("title", briefingCell.get("title"));
            briefOneMap.put("description", briefingCell.get("description"));
            briefOneMap.put("img", briefingCell.get("imageUrl"));
            List<Map<String, Object>> brirfTwoList = new ArrayList<>();
            List<BriefingCell> briefingCellList2 = (List<BriefingCell>) briefingCell.get("children");
            for (int j = 0; j < briefingCellList2.size(); j++) {
                BriefingCell briefingCell2 = briefingCellList2.get(j);
                Map<String, Object> briefTwoMap = new HashMap<String, Object>();
                briefTwoMap.put("title", briefingCell2.getTitle());
                briefTwoMap.put("img", briefingCell2.getImageUrl());
                briefTwoMap.put("description", briefingCell2.getDescription());
                brirfTwoList.add(briefTwoMap);
            }
            briefOneMap.put("briefList", brirfTwoList);
            briefOneList.add(briefOneMap);
        }
        dataMap.put("briefList", briefOneList);

        FreemarkerUtil.processTemplateIntoFile(dataMap, new File(filename), templateName);
    }

    /**
     * 把模板转换为字符串
     *
     * @param dataMap      数据map
     * @param templateName 模板名
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public static String processTemplateIntoString(Map<String, Object> dataMap,
                                                   String templateName)
            throws IOException, TemplateException {
        configuration.setClassForTemplateLoading(FreemarkerUtil.class,
                basePackagePath);
        Template t = null;
        try {
            t = configuration.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return FreeMarkerTemplateUtils.processTemplateIntoString(t, dataMap);
    }

    /**
     * @param dataMap      数据map
     * @param outFile     生成的文件
     * @param templateName 模板名
     * @throws UnsupportedEncodingException
     */
    public static void processTemplateIntoFile(Map<String, Object> dataMap, File outFile,
                                               String templateName) throws Exception {
        // dataMap 要填入模本的数据文件
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setClassForTemplateLoading(FreemarkerUtil.class,
                basePackagePath);
        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Writer out = null;
        FileOutputStream fos = null;
        fos = new FileOutputStream(outFile);
        OutputStreamWriter oWriter = new OutputStreamWriter(fos, "UTF-8");
        // 这个地方对流的编码不可或缺，使用main（）单独调用时，应该可以，但是如果是web请求导出时导出后word文档就会打不开，并且包XML文件错误。主要是编码格式不正确，无法解析。
        // out = new BufferedWriter(new OutputStreamWriter(new
        // FileOutputStream(outFile)));
        out = new BufferedWriter(oWriter);
        t.process(dataMap, out);
        out.close();
        fos.close();
    }

    public static void exportDoc(HttpServletRequest request,
                                 HttpServletResponse response, Map<String, Object> dataMap,
                                 String filename, String templateName)
            throws UnsupportedEncodingException {
        // dataMap 要填入模本的数据文件
        // 设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，
        // 这里我们的模板是放在com.havenliu.document.template包下面
        configuration.setClassForTemplateLoading(FreemarkerUtil.class,
                basePackagePath);
        Template t = null;
        try {
            // test.ftl为要装载的模板
            t = configuration.getTemplate(templateName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获得浏览器代理信息
        final String userAgent = request.getHeader("User-Agent").toLowerCase();
        if (StringUtils.contains(userAgent, "MSIE") || StringUtils.contains(userAgent, "Trident")) {//IE浏览器
            filename = URLEncoder.encode(filename, "UTF8");
        } else if (StringUtils.contains(userAgent, "Mozilla")) {//google
            filename = new String(filename.getBytes(), "ISO8859-1");
        } else if (StringUtils.contains(userAgent, "firefox")) {//火狐浏览器
            filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
        } else {
            filename = URLEncoder.encode(filename, "UTF8");//其他浏览器
        }
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\""
                + filename + "\"");
        // 向浏览器发送一个响应头，设置浏览器的解码方式为UTF-8
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        // 前台页面不使用缓存 因为各个浏览器可能不同，所以写了三种
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Controll", "no-cache");
        response.setHeader("pragma", "no-cache");
        PrintWriter out;
        try {
            out = response.getWriter();
            t.process(dataMap, out);
            out.close();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        } // 向客户端写入内容
    }
}
