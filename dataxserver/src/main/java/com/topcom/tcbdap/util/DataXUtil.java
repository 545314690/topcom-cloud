package com.topcom.tcbdap.util;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by lism on 17-11-1.
 *
 * @author lism
 */
@Controller
public class DataXUtil {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final static String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static String dataxPath = "";
    private static String dataxLogPath = "";
    private static String pythonPath = "";
    private static String GET_TEMPLATE = "";
    private static String JOB = "";

    @Value("${dataxPath:/home/topcom/datax/bin/datax.py }")
    public void setDataxPath(String dataxPath) {
        DataXUtil.dataxPath = dataxPath;
    }

    @Value("${dataxLogPath:/home/topcom/dataxlog/ }")
    public void setDataxLogPath(String dataxLogPath) {
        DataXUtil.dataxLogPath = dataxLogPath;
        File parentLogPath = new File(DataXUtil.dataxLogPath);
        if (!parentLogPath.exists()) {
            parentLogPath.mkdirs();
        }
    }


    @Value("${pythonPath:python }")
    public void setPythonPath(String pythonPath) {
        DataXUtil.pythonPath = pythonPath;
        GET_TEMPLATE = DataXUtil.pythonPath + " " + DataXUtil.dataxPath + " -r {YOUR_READER} -w {YOUR_WRITER}";
        JOB = DataXUtil.pythonPath + " " + " {param} " + DataXUtil.dataxPath + " {JSON_FILE}";
    }

    private static String run(String cmd) throws Exception {
        return ShellRunner.run(cmd);
    }

    public static String runJob(JSONObject jobJson, Map paramMap) throws Exception {
        String param = "";
        if (paramMap != null && paramMap.size()>0) {
            for (Object key : paramMap.keySet()) {
                param = param + "-D" + key.toString() + "=" + paramMap.get(key).toString() + " ";
            }
        }
        return DataXUtil.runJob(jobJson.toString(), param);
    }

    /***
     * 返回日志的路径
     * @param jobJson
     * @param param
     * @return
     * @throws Exception
     */
    public static String runJob(String jobJson, String param) throws Exception {
        LocalDate localDate = LocalDate.now();
        String date = localDate.toString();
        String logfile = DataXUtil.dataxLogPath + File.separatorChar + date + File.separatorChar + UUID.randomUUID().toString() + ".log";
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                File tmp = new File(TEMP_DIR, String.valueOf(UUID.randomUUID()));
                FileUtils.writeStringToFile(tmp, jobJson, "utf-8", false);
                String jobCmd = JOB.replace("{JSON_FILE}", tmp.getAbsolutePath()).replace("{param}", param);
                jobCmd += " > " + logfile;
                String result = ShellRunner.run(jobCmd);
                tmp.delete();
                return logfile;
            }
        });
        return logfile;
    }

    public static String getTemplate(String reader, String writer) throws Exception {
        return ShellRunner.run(GET_TEMPLATE.replace("{YOUR_READER}", reader).replace("{YOUR_WRITER}", writer));
    }

    public static void main(String[] args) throws Exception {
        LocalDate localDate = LocalDate.now();

        System.out.println(localDate.toString());
        System.out.println(DataXUtil.getTemplate("mysqlreader", "oraclewriter"));
        String jobJson = "{\n" +
                "    \"job\": {\n" +
                "        \"content\": [\n" +
                "            {\n" +
                "                \"reader\": {\n" +
                "                    \"name\": \"mysqlreader\",\n" +
                "                    \"parameter\": {\n" +
                "                        \"column\": [\n" +
                "                            \"id\",\n" +
                "                            \"username\",\n" +
                "                            \"password\",\n" +
                "                            \"gender\"\n" +
                "                        ],\n" +
                "                        \"connection\": [\n" +
                "                            {\n" +
                "                                \"jdbcUrl\": [\n" +
                "                                    \"jdbc:mysql://192.168.0.151:3306/yuqing-back\"\n" +
                "                                ],\n" +
                "                                \"table\": [\n" +
                "                                    \"t_user\"\n" +
                "                                ]\n" +
                "                            }\n" +
                "                        ],\n" +
                "                        \"password\": \"topcom123\",\n" +
                "                        \"username\": \"anjian\",\n" +
                "                        \"where\": \"id > 1\"\n" +
                "                    }\n" +
                "                },\"writer\": {\n" +
                "\t\t                    \"name\": \"streamwriter\", \n" +
                "\t\t\t\t                        \"parameter\": {\n" +
                "\t\t\t\t\t\t\t\t                        \"encoding\": \"utf-8\", \n" +
                "\t\t\t\t\t\t\t\t\t\t\t                        \"print\": true\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t                    }\n" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t                    }\n" +
                "\n" +
                "            }\n" +
                "        ],\n" +
                "        \"setting\": {\n" +
                "            \"speed\": {\n" +
                "                \"channel\": \"1\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}\n";
//        System.out.println(DataXUtil.runJob(jobJson));
        System.exit(0);
    }
}
