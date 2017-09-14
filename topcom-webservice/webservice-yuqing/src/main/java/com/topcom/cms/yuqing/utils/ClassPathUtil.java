package com.topcom.cms.yuqing.utils;

/**
 * Created by lism on 15:18.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
public class ClassPathUtil {
    public static String getClassPath() {
        return Thread.currentThread().getContextClassLoader().getResource("").getPath();
    }
    public static String getRealPath(String path) {
        return getClassPath().replace("classes",path);
    }

    public static void main(String[] args) {
        System.out.println(getClassPath());
        System.out.println(getRealPath("resources/data/brief"));
    }
}
