package com.topcom.yzswf.util;

import org.apache.commons.lang.StringUtils;

/**
 * Created by lsm on 2018/3/13 0013.
 */
public class QueryWord {
    private String mustWord;
    private String shouldWord;
    private String notWord;

    public String getMustWord() {
        return mustWord;
    }

    public void setMustWord(String mustWord) {
        this.mustWord = mustWord;
    }

    public String getNotWord() {
        return notWord;
    }

    public void setNotWord(String notWord) {
        this.notWord = notWord;
    }

    public String getShouldWord() {
        return shouldWord;
    }

    public void setShouldWord(String shouldWord) {
        this.shouldWord = shouldWord;
    }

    public String toQueryString() throws Exception{

        StringBuffer buffer = new StringBuffer();
        if(StringUtils.isNotBlank(shouldWord)){
            shouldWord = shouldWord.replaceAll("@","\" OR \"");
            buffer.append("(\"").append(shouldWord).append("\")");
        }else {
            throw new Exception("关键词不能为空");
        }
        if(StringUtils.isNotBlank(mustWord)){
            mustWord =  mustWord.replaceAll("@","\" AND \"");
            buffer.append(" AND (\"").append(mustWord).append("\")");
        }
        if(StringUtils.isNotBlank(notWord)){
            notWord = notWord.replaceAll("@","\" AND \"");
            buffer.append(" NOT (\"").append(notWord).append("\")");
        }
        return buffer.toString();
    }
}
