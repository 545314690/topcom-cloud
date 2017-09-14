package com.topcom.cms.yuqing.utils;

import com.topcom.cms.yuqing.vo.email.BriefingEmail;
import com.topcom.cms.yuqing.vo.email.EmailVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lism on 17-6-6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailUtilTest {
    @Test
    public void sendSimpleEmail() throws Exception {

        EmailVO emailVO = new EmailVO();
        emailVO.setSubject("测试邮件");
        emailVO.setContent("这是测试");
        emailVO.setTo("545314690@qq.com");
        EmailUtil.send(emailVO);
    }

}