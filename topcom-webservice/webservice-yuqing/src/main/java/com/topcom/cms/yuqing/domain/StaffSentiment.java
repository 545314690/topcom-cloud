package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.nosql.BaseEntityModel;
import com.topcom.cms.data.domain.ESBaseDomain;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;

/**
 * 缓存领导舆情信息
 * 一条新闻 情感值占比
 * Created by topcom on 2017/9/1 0001.
 */
@Document(collection = "staff_sentiment")
public class StaffSentiment extends BaseEntityModel {


    private Map staff;

    private Map article;
    /**
     * 负面占比
     */
    private double negPer;

    private Long POS;

    private Long NEG;

    private Long NEU;

    private String staffName;
    private Long staffId;
    private Long total;

    private int commentNum;

    private Date newsDate;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Map getStaff() {
        return staff;
    }

    public void setStaff(Map staff) {
        this.staff = staff;
    }

    public Map getArticle() {
        return article;
    }

    public void setArticle(Map article) {
        this.article = article;
    }

    public double getNegPer() {
        if (total==null||total==0){
            return 0;
        }
        return (double)this.NEG/total;
    }

    public void setNegPer(double negPer) {
        this.negPer = negPer;
    }

    public Long getPOS() {
        return POS;
    }

    public void setPOS(Long POS) {
        this.POS = POS;
    }

    public Long getNEG() {
        return NEG;
    }

    public void setNEG(Long NEG) {
        this.NEG = NEG;
    }

    public Long getNEU() {
        return NEU;
    }

    public void setNEU(Long NEU) {
        this.NEU = NEU;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }
}
