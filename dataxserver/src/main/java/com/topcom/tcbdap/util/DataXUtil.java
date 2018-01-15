package com.topcom.tcbdap.util;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
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
@Component
public class DataXUtil implements InitializingBean{

    private static final ExecutorService executorService = Executors.newFixedThreadPool(100);
    private final static String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static String dataxPath = "datax.py";
    private static String dataxLogPath = "";
    private static String pythonPath = "/usr/bin/python";
    private static String GET_TEMPLATE = "";
    private static String JOB = "";
//    private static String JOB = DataXUtil.pythonPath + " " + DataXUtil.dataxPath+ " {param} "  + " {JSON_FILE}";

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


    @Value("${pythonPath:/usr/bin/python }")
    public void setPythonPath(String pythonPath) {
        DataXUtil.pythonPath = pythonPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        GET_TEMPLATE = DataXUtil.pythonPath + " " + DataXUtil.dataxPath + " -r {YOUR_READER} -w {YOUR_WRITER}";
        JOB = DataXUtil.pythonPath + " "  + DataXUtil.dataxPath + " {param} " + " {JSON_FILE}";
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
        String folderPath = DataXUtil.dataxLogPath + File.separatorChar + date;
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String logfile = folderPath + File.separatorChar + UUID.randomUUID().toString() + ".log";
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                File tmp = new File(TEMP_DIR, String.valueOf(UUID.randomUUID()));
                FileUtils.writeStringToFile(tmp, jobJson, "utf-8", false);
                String jobCmd = JOB.replace("{JSON_FILE}", tmp.getAbsolutePath());
                if(null!=param && "".equals(param)){

                    jobCmd = jobCmd.replace("{param}", param);
                }else{
                    jobCmd = jobCmd.replace("{param}", "-p \""+  param + "\"");
                }
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

        Map map = new HashMap();
        Map json = new HashMap();

        json.put("a","123");
        json.put("b","1234");
        JSONObject jsonObject = JSONObject.fromObject(json);
        DataXUtil.runJob(jsonObject,json);
    }

}
