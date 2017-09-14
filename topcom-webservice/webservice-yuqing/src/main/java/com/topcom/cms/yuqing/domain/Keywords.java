package com.topcom.cms.yuqing.domain;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 关键词
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年12月4日下午4:38:14
 */
@Entity
@Table(name = "t_keywords")
@EntityListeners({AuditingEntityListener.class})
public class Keywords extends BaseEntityModel {

    /**
     * 关键词类型：基础、领导、本地、焦点,煤矿，金属非金属，危化品，工商贸，烟花爆竹，建筑，交通运输，其他
     */
    public enum Type {
        BASIC, LEADER, LOCAL, FOCUS,COLLIERY,NONCOAL,CHEMICALS,BUSINESS,FIREWORKS,BUILDINGS,TRANSPORTATION,OTHERS
    }

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     * 关联的用户id
     */
    @CreatedBy
    @LastModifiedBy
    private Long userId;


    /**
     * 必须出现的词，"@"分割
     */
    @Column(name = "mustWord")
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
     * 表达式
     */
    @Lob
    @Column(name = "expression")
    private String expression;

    /**
     * 模式（普通、高级、专家、智能）
     */
    public enum Mode {
        NORMAL, ADVANCED, EXPERT, SMART
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private Mode mode = Mode.NORMAL;

    public String getExpression() {
        if (StringUtils.isEmpty(this.expression)) {
            if (StringUtils.isEmpty(this.mustWord)) {
                if (!StringUtils.isEmpty(shouldWord)) {
                    expression = "(" + shouldWord.replaceAll("@", "OR") + ")";
                }
                if (!StringUtils.isEmpty(mustNotWord)) {
                    expression = expression + " NOT (" + mustNotWord.replaceAll("@", " AND ") + ")";
                }
            }else {
                expression = mustWord.replaceAll("@", " AND ");
                if (!StringUtils.isEmpty(shouldWord)) {
                    expression = expression + " AND (" + shouldWord.replaceAll("@", "OR") + ")";
                }
                if (!StringUtils.isEmpty(mustNotWord)) {
                    expression = expression + " NOT (" + mustNotWord.replaceAll("@", " AND ") + ")";
                }
            }
        }
        return expression;
    }

    /**
     * 全词匹配，需要给关键词加引号
     *
     * @param expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Keywords() {
    }

    public Keywords(Type type, Long userId) {
        this.type = type;
        this.userId = userId;
    }

    public Keywords(Long userId) {
        this.userId = userId;
    }

    public Keywords(Type type) {
        this.type = type;
    }

    public Keywords(String mustWord) {
        this.mustWord = mustWord;
    }

    public Keywords(String mustWord, String shouldWord) {
        this.mustWord = mustWord;
        this.shouldWord = shouldWord;
    }

    public Keywords(String mustWord, String shouldWord, String mustNotWord) {
        this.mustWord = mustWord;
        this.shouldWord = shouldWord;
        this.mustNotWord = mustNotWord;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public static void main(String[] args) {
        Keywords keywords = new Keywords();
        String s = "安徽AND(事故OR安全)NOT(煤监OR安监)";
        keywords.setExpression(s);
    }
}
