package com.topcom.cms.yuqing.utils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.List;

public class CleanHtmlMethodModel implements TemplateMethodModelEx {


    @Override
    public Object exec(List args) throws TemplateModelException {
        String result = "";
        if ((null != args) && (args.size() > 0)) {
            String str = args.get(0).toString();
            if (null != str) {
                return Jsoup.clean(str, Whitelist.none());
            }
        }
        return result;
    }


} 