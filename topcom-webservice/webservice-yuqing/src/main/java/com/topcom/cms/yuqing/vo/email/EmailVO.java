package com.topcom.cms.yuqing.vo.email;

import com.topcom.cms.yuqing.task.sender.Sendable;

/**
 * Created by topcom on 2017/5/27 0027.
 */
public class EmailVO implements Sendable {

    private String from;
    private String[] to;
    private String[] cc;
    private String subject;
    private String content;
    private boolean html;
    private boolean multipart;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String... to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtml() {
        return html;
    }

    public void setHtml(boolean html) {
        this.html = html;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public boolean isMultipart() {
        return multipart;
    }

    public void setMultipart(boolean multipart) {
        this.multipart = multipart;
    }
}
