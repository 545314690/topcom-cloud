package com.topcom.cms.yuqing.task.sender.factory;

import com.topcom.cms.yuqing.task.sender.SMSSender;
import com.topcom.cms.yuqing.task.sender.Sender;

/**
 * Created by lism on 17-6-5.
 */
public class SMSSenderFactory implements SenderFactory {

    @Override
    public Sender create() {
        return new SMSSender();
    }
}
