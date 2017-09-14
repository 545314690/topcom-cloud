package com.topcom.cms.yuqing.utils;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronTest {
  
    public static void main(String[] args) {  
        cronTest();  
    }  

    private static void cronTest() {
        try {
            org.quartz.CronExpression exp = new org.quartz.CronExpression("0 35 12 ? * 4");
            //
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            Date d = new Date();
            int i = 0;  
            // 循环得到接下来n此的触发时间点，供验证  
            while (i < 10) {  
                d = exp.getNextValidTimeAfter(d);  
                System.out.println(df.format(d));  
                ++i;  
            }  
        } catch (ParseException e) {
            e.printStackTrace();  
        }  
    }  
  
}  
