package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 查询词语实体类
 * 热词统计
 * @author maxl
 * @date 2018/3/12 0012
 */
@Entity
@Table(
        name = "t_searchWord"
)
public class SearchWord  extends BaseEntityModel {

    private String word;

    /**
     * 搜索类型
     * 功能搜索 1    资源搜索 2
     */
    private int type;

    private long wordCount;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }
}
