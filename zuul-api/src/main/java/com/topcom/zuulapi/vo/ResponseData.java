package com.topcom.zuulapi.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lism on 2017/5/25.
 */
public class ResponseData implements Serializable {
    private Long timestamp = new Date().getTime();
    private String message;
    private String data;
    private boolean status;
    private int code;

    public ResponseData() {
    }

    public ResponseData(boolean status) {
        this.status = status;
    }

    public ResponseData(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("timestamp:").append(timestamp);
        sb.append(", message:'").append(message).append('\'');
        sb.append(", data:'").append(data).append('\'');
        sb.append(", status:").append(status);
        sb.append(", code:").append(code);
        sb.append('}');
        return sb.toString();
    }
}