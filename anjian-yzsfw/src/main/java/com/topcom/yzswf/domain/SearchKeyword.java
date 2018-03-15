package com.topcom.yzswf.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 搜索关键字实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2016年7月21日11:54:12
 */
@Entity
@Table(name = "t_searchkeyword")
//@NamedNativeQueries({
//        @NamedNativeQuery(name = "SearchKeyword.getRelated",resultClass = SearchKeyword.class,
//        query = "SELECT DATECREATED,DATEMODIFED,ENTITY_NAME,deleted,id,keyword,keyword_fc,searchDate,frequency,top,pinyin,shortPinyin,synonym,MATCH (keyword_fc) AGAINST (?1) as keyword_score FROM t_searchkeyword WHERE MATCH (keyword_fc) AGAINST (?1 IN BOOLEAN MODE) order by keyword_score DESC limit 0,?2")})
public class SearchKeyword extends BaseEntityModel {
//    "dateCreated": 1476349378000,
//            "dateModified": 1476349378000,
//            "deleted": false,
//            "id": 72,
//            "keyword": "高级检索",
//            "keyword_fc": "高级 检索",
//            "keyword_score": 0,
//            "searchDate": null,
//            "frequency": 1,
//            "top": 0,
//            "pinyin": "gaojijiansuo",
//            "shortPinyin": "gjjs",
//            "synonym": null
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SearchKeyword() {
    }

    public SearchKeyword(String keyword) {
        this.keyword = keyword;
    }

    public SearchKeyword(String keyword, int frequency) {
        this.keyword = keyword;
        this.frequency = frequency;
    }

    public SearchKeyword(String keyword, int frequency, String pinyin, String shortPinyin) {
        this.keyword = keyword;
        this.frequency = frequency;
        this.pinyin = pinyin;
        this.shortPinyin = shortPinyin;
    }

    public SearchKeyword(String keyword, int frequency, String pinyin, String synonym, String shortPinyin) {
        this.keyword = keyword;
        this.frequency = frequency;
        this.pinyin = pinyin;
        this.synonym = synonym;
        this.shortPinyin = shortPinyin;
    }

    public SearchKeyword(String keyword, String keyword_fc, int frequency, String pinyin, String shortPinyin) {
        this.keyword = keyword;
        this.keyword_fc = keyword_fc;
        this.frequency = frequency;
        this.pinyin = pinyin;
        this.shortPinyin = shortPinyin;
    }

    public SearchKeyword(String keyword, String keyword_fc, double keyword_score, Date searchDate, int frequency, int top, String pinyin, String shortPinyin, String synonym) {
        this.keyword = keyword;
        this.keyword_fc = keyword_fc;
        this.keyword_score = keyword_score;
        this.searchDate = searchDate;
        this.frequency = frequency;
        this.top = top;
        this.pinyin = pinyin;
        this.shortPinyin = shortPinyin;
        this.synonym = synonym;
    }

    public SearchKeyword(String keyword, double keyword_score) {
        this.keyword = keyword;
        this.keyword_score = keyword_score;
    }

    /**
     * 搜索关键字
     */
    @Column(name = "keyword", length = 50, unique = true)
    String keyword;

    /**
     * 搜索关键字分词
     */
    @Column(name = "keyword_fc", length = 150)
    String keyword_fc;

    /**
     * 关键字匹配度得分
     */
    @Column(name = "keyword_score")
//    @Transient
    Double keyword_score = 0.0;

    /**
     * 最近搜索时间
     */
    @Column(name = "searchDate")
    Date searchDate;
    /**
     * 关键字出现频率
     */
    @Column(name = "frequency")
    int frequency;

    /**
     * 置顶 1为置顶，0为非置顶
     */
    @Column(name = "top")
    int top;

    /**
     * 关键字拼音，多音字以,分隔
     */
    @Column(name = "pinyin", length = 2000)
    String pinyin;

    /**
     * 关键字短拼音，多音字以,分隔
     */
    @Column(name = "shortPinyin")
    String shortPinyin;

    /**
     * 关键字同义词，多个用,分隔
     */
    @Column(name = "synonym")
    String synonym;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getShortPinyin() {
        return shortPinyin;
    }

    public void setShortPinyin(String shortPinyin) {
        this.shortPinyin = shortPinyin;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public String getKeyword_fc() {
        return keyword_fc;
    }

    public void setKeyword_fc(String keyword_fc) {
        this.keyword_fc = keyword_fc;
    }

    public Double getKeyword_score() {
        return keyword_score;
    }

    public void setKeyword_score(Double keyword_score) {
        this.keyword_score = keyword_score;
    }
}
