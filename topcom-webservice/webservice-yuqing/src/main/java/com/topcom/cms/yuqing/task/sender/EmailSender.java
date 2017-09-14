package com.topcom.cms.yuqing.task.sender;

import com.topcom.cms.yuqing.utils.EmailUtil;
import com.topcom.cms.yuqing.vo.email.EmailVO;

import javax.mail.MessagingException;

/**
 * Created by lism on 17-6-5.
 */
public class EmailSender implements Sender {

    @Override
    public void send(Sendable sendable) throws Exception {
        EmailUtil.send((EmailVO) sendable);
    }
}
