package com.topcom.cms.yuqing.task.sender.factory;

import com.topcom.cms.yuqing.task.sender.EmailSender;
import com.topcom.cms.yuqing.task.sender.Sender;

/**
 * Created by lism on 17-6-5.
 */
public class EmailSenderFactory implements SenderFactory {

    @Override
    public Sender create() {
        return new EmailSender();
    }
}
