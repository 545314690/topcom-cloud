package com.topcom.cms.yuqing.vo.request;

import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 关键词
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年12月4日下午4:38:14
 */
public class KeywordRequest implements Serializable {


    /**
     * 必须出现的词，"@"分割
     */
    private String mustWord;

    /**
     * 同现词，至少出现其中一个，"@"分割
     */
    private String shouldWord;

    /**
     * 过滤词，不能出现的词，"@"分割
     */
    private String mustNotWord;

    /**
     * 表达式（AND OR NOT）
     */
    private String expression;

    public String getExpression() {

        if (StringUtils.isEmpty(this.expression)){
            if (!StringUtils.isEmpty(mustWord)){
                expression="("+"\""+mustWord.replaceAll("@"," \"AND\" ")+"\""+")";
            }
            if (!StringUtils.isEmpty(shouldWord)){
                String shouldExpretion="("+"\""+shouldWord.replaceAll("@","\"OR\"")+"\""+")";
                expression = StringUtils.isEmpty(expression) ? shouldExpretion:expression+"AND"+shouldExpretion;
            }
            if (!StringUtils.isEmpty(mustNotWord)){
                String notExpretion="("+"\""+mustNotWord.replaceAll("@"," \"OR\" ")+"\""+")";
                expression = StringUtils.isEmpty(expression) ? notExpretion:expression+"NOT"+notExpretion;
            }
        }
        return expression;
    }

    public void setExpression(String expression) {
        Set<String> wordsSet = new HashSet<>();
        String[] wordsArray = expression.replaceAll("[AND OR NOT ( )]","@").split("@");
        for (String s:wordsArray){
            if (!StringUtils.isEmpty(s)){
                wordsSet.add(s);
            }
        }
        for (String s:wordsSet){
            expression=expression.replaceAll(s,"\""+s+"\"");
        }
        this.expression = expression;
    }

    public KeywordRequest() {
    }
    public KeywordRequest(String expression) {
        this.expression = expression;
    }
    public KeywordRequest(String mustWord, String shouldWord) {
        this.mustWord = mustWord;
        this.shouldWord = shouldWord;
    }

    public KeywordRequest(String mustWord, String shouldWord, String mustNotWord) {
        this.mustWord = mustWord;
        this.shouldWord = shouldWord;
        this.mustNotWord = mustNotWord;
    }

    public String getShouldWord() {
        return shouldWord;
    }

    public void setShouldWord(String shouldWord) {
        this.shouldWord = shouldWord;
    }

    public String getMustWord() {
        return mustWord;
    }

    public void setMustWord(String mustWord) {
        this.mustWord = mustWord;
    }

    public String getMustNotWord() {
        return mustNotWord;
    }

    public void setMustNotWord(String mustNotWord) {
        this.mustNotWord = mustNotWord;
    }

    @Override
    public String toString() {
        return "Keywod{" +
                "mustWord='" + mustWord + '\'' +
                ", shouldWord='" + shouldWord + '\'' +
                ", mustNotWord='" + mustNotWord + '\'' +
                ", expression='" + expression + '\'' +
                '}';
    }
}
