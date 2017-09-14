package com.topcom.cms.yuqing.task.briefing;

import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.domain.Contact;
import com.topcom.cms.yuqing.service.BriefingTaskManager;
import com.topcom.cms.yuqing.task.sender.Sender;
import com.topcom.cms.yuqing.task.sender.factory.EmailSenderFactory;
import com.topcom.cms.yuqing.task.sender.factory.SenderFactory;
import com.topcom.cms.yuqing.vo.email.BriefingEmail;
import freemarker.template.TemplateException;
import net.sf.json.JSONObject;
import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * Created by maxl on 17-6-9.
 */
@Component
public class BriefingJob implements Job {

    private static final Logger logger = Logger.getLogger(BriefingJob.class);

    @Autowired
    BriefingTaskManager briefingTaskManager;

    private String SUBJECT = "舆情报告";

    @Autowired
    BriefingCreator briefingCreator;

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {

        Long briefingTaskId = (Long) context.getMergedJobDataMap().get("briefingTaskId");
        BriefingTask briefingTask = briefingTaskManager.findById(briefingTaskId);
        logger.info("任务运行：" + briefingTask.getBriefingType() + ":" + briefingTaskId);

        /**
         * 生成月报
         */
        JSONObject briefing = null;
        try {
            briefing = (JSONObject) briefingCreator.create(briefingTask).get();
            logger.info("任务运行：生成报告成功");
        } catch (BusinessException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
            logger.info("任务运行：生成报告失败");
        }
        List<Contact> contacts = briefingTask.getContacts();
        //过滤出email联系人
        Set<String> emailSet = contacts.stream()
                .filter(contact -> Contact.Type.EMAIL.equals(contact.getType()))
                .map(contact -> contact.getAccount()).collect(Collectors.toSet());
        if (emailSet.size() > 0) {
            SenderFactory emailSenderFactory = new EmailSenderFactory();
            Sender emailSender = emailSenderFactory.create();

            String[] emails = {};
            emails = emailSet.toArray(emails);
            if (briefing != null) {
                try {
                    BriefingEmail briefingEmail = new BriefingEmail().create(emails, briefing);
                    briefingEmail.setSubject(SUBJECT);
                    emailSender.send(briefingEmail);
                } catch (IOException | TemplateException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
