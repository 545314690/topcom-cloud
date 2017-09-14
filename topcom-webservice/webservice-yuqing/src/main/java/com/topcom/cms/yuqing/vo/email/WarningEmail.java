package com.topcom.cms.yuqing.vo.email;

import com.topcom.cms.yuqing.utils.FreemarkerUtil;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by topcom on 2017/5/27 0027.
 */
public class WarningEmail extends EmailVO {
    private List data;
    private static final String template = "warning_html.ftl";



    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public WarningEmail create(String[] emails, WarningEmailBody warningEmailBody) throws IOException, TemplateException {
        //this.setTo("807507917@qq.com");
        this.setHtml(true);
        this.setTo(emails);
        this.setSubject(warningEmailBody.getSubject());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("warning", warningEmailBody);
        String htmlMessage = FreemarkerUtil.processTemplateIntoString(dataMap, template);
        this.setContent(htmlMessage);
        return this;
    }
}
