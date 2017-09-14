package com.topcom.cms.yuqing.task.warning;

import com.topcom.cms.data.domain.WeChat;
import com.topcom.cms.domain.User;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.yuqing.domain.*;
import com.topcom.cms.yuqing.service.CustomSubjectManager;
import com.topcom.cms.yuqing.service.SubscriptionFollowerManager;
import com.topcom.cms.yuqing.service.WarningLogManager;
import com.topcom.cms.yuqing.service.WarningManager;
import com.topcom.cms.yuqing.task.sender.factory.EmailSenderFactory;
import com.topcom.cms.yuqing.task.sender.Sender;
import com.topcom.cms.yuqing.task.sender.factory.SenderFactory;
import com.topcom.cms.yuqing.utils.WechatUtil;
import com.topcom.cms.yuqing.vo.email.WarningEmailSetting;
import com.topcom.cms.yuqing.vo.email.WarningEmail;
import com.topcom.cms.yuqing.vo.email.WarningEmailBody;
import com.topcom.cms.yuqing.vo.request.WechatSendRequest;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;
import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: WarningJob
 * @Description: 预警任务
 */
@Component
public class WarningJob implements Job {
    private static final Logger logger = Logger.getLogger(WarningJob.class);

    @Autowired
    CustomSubjectManager subjectManager;
    @Autowired
    WarningManager warningManager;
    @Autowired
    WarningLogManager warningLogManager;
    @Autowired
    SubscriptionFollowerManager subscriptionFollowerManager;
    @Autowired
    UserManager userManager;

    @Autowired
    WarningEmailSetting warningEmailSetting;

    @Autowired
    WechatUtil wechatUtil;

    private final String TEMPLATE_ID="2g48oMh168kJyXMpzaL_cg-q3j-r6FWTfnqzKgtOyIQ";
    private final String VALUE="value";
    private final String CONTENT_COLOR = "#66B3FF";
    private final String TITLE_COLOR = "#111111";
    private final String COLOR="color";
    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        Long subjectId = (Long) context.getMergedJobDataMap().get("subjectId");
        CustomSubject subject = subjectManager.findById(subjectId);
        WarningTask warningTask = subject.getWarning();
        logger.info("任务运行：" + subject.getName());
        /**
         * 生成预警日志
         */
        WarningLog warningLog = warningLogManager.formSubject(subject);
        Page page = warningLogManager.getWarningInfo(warningLog, 1, 10);
        /**
         * 只有当预警内容大于等于minWarningNum条的时候才发送预警！！
         */
        int minWarningNum = warningTask.getMinWarningNum();
        if (minWarningNum<=0){
            minWarningNum=1;
        }
        if (page.getContent().size() >= minWarningNum) {
            sendEmail(page,subject,warningLog.getId());
            sendWechat(page,subject,warningLog.getId());

        }else {
            logger.info("任务获取数据"+page.getContent().size()+"条，不发送预警：" + subject.getName());
        }
    }
    private void sendEmail(Page page,CustomSubject subject,Long wangningLogId){
        WarningTask warningTask = subject.getWarning();
        //logger.info("开始推送Email消息：" + subject.getName());
        List<Contact> contacts = warningTask.getContacts();
        /**
         * 获取所有联系人
         */
        Set<String> emailSet = contacts.stream()
                .filter(contact -> Contact.Type.EMAIL.equals(contact.getType()))
                .map(contact -> contact.getAccount()).collect(Collectors.toSet());
        logger.info("开始推送Email消息：" + subject.getName()+emailSet.toString());
        if (emailSet.size() > 0) {
            /**
             *先保存预警日志，否则下面warningLog.getId()取到的日志Id总为0，保存日志的时候，不要调用save
             */

            //发送邮件
            SenderFactory emailSenderFactory = new EmailSenderFactory();
            Sender emailSender = emailSenderFactory.create();

            String[] emails = {};
            emails = emailSet.toArray(emails);
            try {
                WarningEmailBody warningEmailBody = new WarningEmailBody();
                warningEmailBody.setContent(page.getContent());
                warningEmailBody.setSpecialNumber(String.valueOf(page.getTotalElements()));
                warningEmailBody.setSpecialName(subject.getName());
                User user = userManager.findById(subject.getUserId());
                warningEmailBody.setUser(user.getFullName() == null ? user.getUsername() : user.getFullName());
//                    warningEmailBody.setSubject("");
                warningEmailBody.setSpecialUrl(warningEmailSetting.getSubjectUrl() + subject.getId());
                warningEmailBody.setWarningLogUrl(warningEmailSetting.getLogUrl() + wangningLogId);
                WarningEmail warningEmail = new WarningEmail().create(emails, warningEmailBody);
                emailSender.send(warningEmail);
                //更新上次预警时间
                warningTask.setLastWarningDate(new Date());
                warningManager.save(warningTask);
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送微信
     * @param page
     * @param subject
     * @param wangningLogId
     */
    private void sendWechat(Page page,CustomSubject subject,Long wangningLogId){
        WarningTask warningTask = subject.getWarning();
        logger.info("开始推送Wechat消息：" + subject.getName());
        List<Contact> contacts = warningTask.getContacts();
        Set<String> wechatSet = contacts.stream()
                .filter(contact -> Contact.Type.WECHAT.equals(contact.getType()))
                .map(contact -> contact.getAccount()).collect(Collectors.toSet());
        WechatSendRequest wechatSendRequest = new WechatSendRequest();
        JSONObject data = getData(page.getContent());
        User user = userManager.findById(subject.getUserId());

        setData(data,"name",user.getFullName() == null ? user.getUsername() : user.getFullName());//userName
        setData(data,"subjectName",subject.getName());
        setData(data,"num",page.getTotalElements());
        wechatSendRequest.setData(data);
        wechatSendRequest.setTemplate_id(TEMPLATE_ID);
        wechatSendRequest.setUrl(warningEmailSetting.getLogUrl());
        if (wechatSet.size() > 0) {
            for (String s:wechatSet){
                wechatSendRequest.setTouser(s);
                wechatUtil.sendTemplate(JSONObject.fromObject(wechatSendRequest));
            }
            warningTask.setLastWarningDate(new Date());
            warningManager.save(warningTask);
        }
    }

    private void setData(JSONObject jsonObject,String name,Object value){
        JSONObject conJson = new JSONObject();
        conJson.put(VALUE,value);
        conJson.put(COLOR,TITLE_COLOR);
        jsonObject.put(name,conJson);
    }
    private JSONObject getData(List<Map> content){
        JSONObject data = new JSONObject();
        if (content!=null&&content.size()>0){
            for (int i=0;i<content.size()&&i<4;i++){
                JSONObject conJson = new JSONObject();
                String title = content.get(i).get("title").toString();
                int endSub = title.length()>18?18:(title.length()-1);
                title = title.substring(0,endSub)+"...";
                conJson.put(VALUE,title);
                conJson.put(COLOR,CONTENT_COLOR);
                data.put("content"+(i+1),conJson);
            }
        }
        return data;
    }
}