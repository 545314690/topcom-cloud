package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

/**
 * 关键词
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2014年12月4日下午4:38:14
 */
@Entity
@Table(name = "t_keywords_weight")
@EntityListeners({AuditingEntityListener.class})
public class KeywordsWeight extends BaseEntityModel {

    /**
     * 关键词类型：基础、领导、本地、焦点
     */
    public enum Type {
        BASIC, LEADER, LOCAL, FOCUS
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
    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "must_id")
    private Set<WordWeight> mustWord;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "should_id")
    private Set<WordWeight> shouldWord;

    @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "mustnot_id")
    private Set<WordWeight> mustNotWord;


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<WordWeight> getMustWord() {
        return mustWord;
    }

    public void setMustWord(Set<WordWeight> mustWord) {
        this.mustWord = mustWord;
    }

    public Set<WordWeight> getShouldWord() {
        return shouldWord;
    }

    public void setShouldWord(Set<WordWeight> shouldWord) {
        this.shouldWord = shouldWord;
    }

    public Set<WordWeight> getMustNotWord() {
        return mustNotWord;
    }

    public void setMustNotWord(Set<WordWeight> mustNotWord) {
        this.mustNotWord = mustNotWord;
    }

    public KeywordsWeight() {
    }

    public KeywordsWeight(Type type, Long userId) {
        this.type = type;
        this.userId = userId;
    }

    public KeywordsWeight(Long userId) {
        this.userId = userId;
    }

    public KeywordsWeight(Type type) {
        this.type = type;
    }

    public KeywordsWeight(Set<WordWeight> mustWord) {
        this.mustWord = mustWord;
    }

    public KeywordsWeight(Set<WordWeight> mustWord, Set<WordWeight> shouldWord) {
        this.mustWord = mustWord;
        this.shouldWord = shouldWord;
    }

    public KeywordsWeight(Set<WordWeight> mustWord, Set<WordWeight> shouldWord, Set<WordWeight> mustNotWord) {
        this.mustWord = mustWord;
        this.shouldWord = shouldWord;
        this.mustNotWord = mustNotWord;
    }
}
