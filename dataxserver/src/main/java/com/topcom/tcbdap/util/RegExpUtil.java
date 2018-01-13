package com.topcom.tcbdap.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lism on 18-1-10.
 */
public class RegExpUtil {
    public static String extractByReg(String reg, String string, int group) {

//        String regex = "address:(.*\")(.*)(\",)";//别忘了使用非贪婪模式！

        Matcher matcher = Pattern.compile(reg).matcher(string);

        if (matcher.find()) {
            String ret = matcher.group(group);
            return ret;
        }
        return "";
    }

}
