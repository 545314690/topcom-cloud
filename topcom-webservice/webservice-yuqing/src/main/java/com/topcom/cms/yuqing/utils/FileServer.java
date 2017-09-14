package com.topcom.cms.yuqing.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

/**
 * Created by lism on 2017/5/27.
 */
@Component
public class FileServer {

    public static String filepath = "/home/yuqing/briefingFile/";

    @Value(value = "${briefing.path}")
    private void setFilepath(String filepath) {
        FileServer.filepath = filepath;
    }

    private FileServer() {

    }

    @PostConstruct
    private void mkdir(){
        File file = new File(filepath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
