package com.topcom.cms.yuqing.vo.email;

import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.utils.FreemarkerUtil;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by topcom on 2017/5/27 0027.
 */
public class BriefingEmail extends EmailVO {
    private Briefing briefing;
    private static final String template = "briefing_html.ftl";

    private static final Map<Briefing.BriefingType, String> templateMap = new HashMap<>();

    static {
        templateMap.put(Briefing.BriefingType.MONTHLY,"briefing_monthly_html.ftl");
        templateMap.put(Briefing.BriefingType.WEEKLY,"briefing_weekly_html.ftl");
        templateMap.put(Briefing.BriefingType.DAILY,"briefing_daily_html.ftl");
    }
    public Briefing getBriefing() {
        return briefing;
    }


    public void setBriefing(Briefing briefing) {
        this.briefing = briefing;
    }


    public BriefingEmail create(String[] emails, JSONObject briefing) throws IOException, TemplateException {
        this.setTo(emails);
        this.setHtml(true);
        this.setTo(emails);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("briefing", briefing);
//        Briefing.BriefingType briefingType = (Briefing.BriefingType) briefing.get("briefingType");
//        String template = templateMap.get(briefingType);
        String htmlMessage = FreemarkerUtil.processTemplateIntoString(dataMap, template);
        this.setContent(htmlMessage);
        return this;
    }
}
