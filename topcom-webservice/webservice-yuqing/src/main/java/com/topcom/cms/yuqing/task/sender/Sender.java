package com.topcom.cms.yuqing.task.sender;

import javax.mail.MessagingException;

/**
 * Created by lism on 17-6-5.
 */
public interface Sender {

    void send(Sendable sendable) throws Exception;

}
