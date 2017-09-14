package com.topcom.cms.yuqing.domain;


import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 预警联系人
 */
@Entity
@Table(name = "t_word_weight")
@EntityListeners({AuditingEntityListener.class})
public class WordWeight extends BaseEntityModel {


    @Column(name = "word",nullable = false)
    private String word;

    @Column(name = "weight",nullable = false)
    private float weight;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
