package com.topcom.cms.yuqing.utils;

import com.topcom.cms.yuqing.vo.email.EmailVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * Created by topcom on 2017/5/26 0026.
 */
@Component
public class EmailUtil {

    private static String PERSONAL;
    private static JavaMailSender mailSender;
    private static String EMAIL_FROM;

    @Value("${spring.mail.username}")
    public void setEmailFrom(String emailFrom) {
        EMAIL_FROM = emailFrom;
    }

    @Value("${spring.mail.from}")
    public void setPersonal(String personal) {
        EmailUtil.PERSONAL = personal;
    }

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        EmailUtil.mailSender = mailSender;
    }

    public static void send(EmailVO emailVO) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, emailVO.isMultipart(), "utf-8");
        mimeMessageHelper.setTo(emailVO.getTo());
        mimeMessageHelper.setFrom(EMAIL_FROM,PERSONAL);
        mimeMessageHelper.setSubject(emailVO.getSubject());
        if (StringUtils.isNotBlank(emailVO.getContent())) {
            mimeMessageHelper.setText(emailVO.getContent(), emailVO.isHtml());
        }
        String[] cc = emailVO.getCc();
        if (cc != null && cc.length > 0) {
            mimeMessageHelper.setCc(cc);
        }
        mailSender.send(mimeMessage);//发送邮件

    }

}
