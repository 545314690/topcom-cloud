package com.topcom.tcbdap.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by lism on 14:13.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
public class ShellRunner {

    static final Log logger = LogFactory.getLog(ShellRunner.class);

    public static String run(String cmd) throws Exception {
       
//        logger.info("cmd:" + cmd);
        StringBuffer buffer = new StringBuffer();
        String[] cmds;
        if (System.getProperty("os.name").indexOf("Windows")!=-1){
            cmds = new String[]{"cmd.exe", "/c", cmd};
        }else {
            cmds = new String[]{"/bin/sh", "-c", cmd};
        }
        Process pro = Runtime.getRuntime().exec(cmds);
        pro.waitFor();
        InputStream in = pro.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = read.readLine()) != null) {
            buffer.append(line);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public static String getPid(String projectName) throws Exception {
        String cmd = "ps -ef | grep " + projectName + " | grep -v grep | awk '{ print $2 }'";
        return run(cmd);
    }

    public static String kill(String pid) throws Exception {
        String cmd = "kill " + pid;
        return run(cmd);
    }

    public static String killProject(String projectName) throws Exception {
        return kill(getPid(projectName));
    }


    public static void main(String[] args) throws Exception {
//        String[] cmds = {"/bin/sh", "-c","ps -ef|grep tomcat"};
        String[] cmds = {"/bin/sh", "-c","python D:\\wokespace-ma\\git\\DataX\\target\\datax\\datax\\bin\\datax.py D:\\wokespace-ma\\git\\DataX\\target\\datax\\datax\\bin\\test"};
        logger.info(cmds);
        String result = ShellRunner.run("python D:\\wokespace-ma\\git\\DataX\\target\\datax\\datax\\bin\\datax.py D:\\wokespace-ma\\git\\DataX\\target\\datax\\datax\\bin\\test");
        logger.info(result);
    }
}