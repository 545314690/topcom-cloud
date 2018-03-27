package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.*;
import java.util.Date;

/**
 * 生产安全事故调查处理及责任追究情况表
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2018年3月26日11:54:12
 */
@Entity
@Table(name = "t_accident_investigation")
public class TjsAccidentInvestigation extends BaseEntityModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TjsAccidentInvestigation() {
    }
    @OneToOne
    @JoinColumn(name = "accidentId",columnDefinition = "bigint(20) COMMENT '事故id'")
    private TjsAccident accident;
    /**
     * 事故名称
     */
    @Column(columnDefinition = "varchar(100) COMMENT '事故名称'")
    private String SGMC;
    /**
     * 成立调查组日期
     */
    @Column(columnDefinition = "datetime COMMENT '成立调查组日期'")
    private Date CLDCZRQ;
    /**
     * 挂牌督办
     */
    @Column(columnDefinition = "bit COMMENT '挂牌督办'")
    private Boolean GPDB;
    /**
     * 挂牌督办单位及文号
     */
    @Column(columnDefinition = "varchar(20) COMMENT '挂牌督办单位及文号'")
    private String GPDBDWJWH;
    /**
     * 提交事故调查报告日期
     */
    @Column(columnDefinition = "datetime COMMENT '提交事故调查报告日期'")
    private Date TJSGDCBGRQ;
    /**
     * 批复日期
     */
    @Column(columnDefinition = "datetime COMMENT '批复日期'")
    private Date PFRQ;
    /**
     * 公布日期
     */
    @Column(columnDefinition = "datetime COMMENT '公布日期'")
    private Date GBRQ;
    /**
     * 政纪党纪处分(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(人)省部级'")
    private Integer ZJDJCFA;
    /**
     * 政纪党纪处分(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(人)厅局级'")
    private Integer ZJDJCFB;
    /**
     * 政纪党纪处分(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(人)县处级'")
    private Integer ZJDJCFC;
    /**
     * 政纪党纪处分(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(人)乡科级'")
    private Integer ZJDJCFD;
    /**
     * 政纪党纪处分(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(人)其他'")
    private Integer ZJDJCFE;
    /**
     * 政纪党纪处分(机关)(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(机关)(人)省部级'")
    private Integer ZJDJCFJGA;
    /**
     * 政纪党纪处分(机关)(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(机关)(人)厅局级'")
    private Integer ZJDJCFJGB;
    /**
     * 政纪党纪处分(机关)(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(机关)(人)县处级'")
    private Integer ZJDJCFJGC;
    /**
     * 政纪党纪处分(机关)(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(机关)(人)乡科级'")
    private Integer ZJDJCFJGD;
    /**
     * 政纪党纪处分(机关)(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(机关)(人)其他'")
    private Integer ZJDJCFJGE;
    /**
     * 政纪党纪处分(企业)(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(企业)(人)省部级'")
    private Integer ZJDJCFQYA;
    /**
     * 政纪党纪处分(企业)(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(企业)(人)厅局级'")
    private Integer ZJDJCFQYB;
    /**
     * 政纪党纪处分(企业)(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(企业)(人)县处级'")
    private Integer ZJDJCFQYC;
    /**
     * 政纪党纪处分(企业)(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(企业)(人)乡科级'")
    private Integer ZJDJCFQYD;
    /**
     * 政纪党纪处分(企业)(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '政纪党纪处分(企业)(人)其他'")
    private Integer ZJDJCFQYE;
    /**
     * 党纪处分(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(人)省部级'")
    private Integer DJCFA;
    /**
     * 党纪处分(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(人)厅局级'")
    private Integer DJCFB;
    /**
     * 党纪处分(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(人)县处级'")
    private Integer DJCFC;
    /**
     * 党纪处分(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(人)乡科级'")
    private Integer DJCFD;
    /**
     * 党纪处分(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(人)其他'")
    private Integer DJCFE;
    /**
     * 党纪处分(机关)(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(机关)(人)省部级'")
    private Integer DJCFJGA;
    /**
     * 党纪处分(机关)(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(机关)(人)厅局级'")
    private Integer DJCFJGB;
    /**
     * 党纪处分(机关)(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(机关)(人)县处级'")
    private Integer DJCFJGC;
    /**
     * 党纪处分(机关)(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(机关)(人)乡科级'")
    private Integer DJCFJGD;
    /**
     * 党纪处分(机关)(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(机关)(人)其他'")
    private Integer DJCFJGE;
    /**
     * 党纪处分(企业)(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(企业)(人)省部级'")
    private Integer DJCFQYA;
    /**
     * 党纪处分(企业)(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(企业)(人)厅局级'")
    private Integer DJCFQYB;
    /**
     * 党纪处分(企业)(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(企业)(人)县处级'")
    private Integer DJCFQYC;
    /**
     * 党纪处分(企业)(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(企业)(人)乡科级'")
    private Integer DJCFQYD;
    /**
     * 党纪处分(企业)(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '党纪处分(企业)(人)其他'")
    private Integer DJCFQYE;

    /**
     * 追究刑事责任(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(人)省部级'")
    private Integer ZJXSZRA;
    /**
     * 追究刑事责任(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(人)厅局级'")
    private Integer ZJXSZRB;
    /**
     * 追究刑事责任(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(人)县处级'")
    private Integer ZJXSZRC;
    /**
     * 追究刑事责任(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(人)乡科级'")
    private Integer ZJXSZRD;
    /**
     * 追究刑事责任(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(人)其他'")
    private Integer ZJXSZRE;
    /**
     * 追究刑事责任(机关)(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(机关)(人)省部级'")
    private Integer ZJXSZRJGA;
    /**
     * 追究刑事责任(机关)(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(机关)(人)厅局级'")
    private Integer ZJXSZRJGB;
    /**
     * 追究刑事责任(机关)(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(机关)(人)县处级'")
    private Integer ZJXSZRJGC;
    /**
     * 追究刑事责任(机关)(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(机关)(人)乡科级'")
    private Integer ZJXSZRJGD;
    /**
     * 追究刑事责任(机关)(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(机关)(人)其他'")
    private Integer ZJXSZRJGE;
    /**
     * 追究刑事责任(企业)(人)省部级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(企业)(人)省部级'")
    private Integer ZJXSZRQYA;
    /**
     * 追究刑事责任(企业)(人)厅局级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(企业)(人)厅局级'")
    private Integer ZJXSZRQYB;
    /**
     * 追究刑事责任(企业)(人)县处级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(企业)(人)县处级'")
    private Integer ZJXSZRQYC;
    /**
     * 追究刑事责任(企业)(人)乡科级
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(企业)(人)乡科级'")
    private Integer ZJXSZRQYD;
    /**
     * 追究刑事责任(企业)(人)其他
     */
    @Column(columnDefinition = "int(5) COMMENT '追究刑事责任(企业)(人)其他'")
    private Integer ZJXSZRQYE;
    /**
     * 建议罚款额(元)
     */
    @Column(columnDefinition = "double COMMENT '建议罚款额(元)'")
    private Double JYFKE;
    /**
     * 举报奖励金额(元)
     */
    @Column(columnDefinition = "double COMMENT '举报奖励金额(元)'")
    private Double JBJLJE;
    /**
     * 统计审核人
     */
    @Column(columnDefinition = "varchar(20) COMMENT '统计审核人'")
    private String TJSHR;

    /**
     * 负责人
     */
    @Column(columnDefinition = "varchar(20) COMMENT '负责人'")
    private String FZR;
    /**
     * 填表人
     */
    @Column(columnDefinition = "varchar(20) COMMENT '填表人'")
    private String TBR;
    /**
     * 填表日期
     */
    @Column(columnDefinition = "datetime COMMENT '填表日期'")
    private Date TBRQ;

    public TjsAccident getAccident() {
        return accident;
    }

    public void setAccident(TjsAccident accident) {
        this.accident = accident;
    }

    public String getSGMC() {
        return SGMC;
    }

    public void setSGMC(String SGMC) {
        this.SGMC = SGMC;
    }

    public Date getCLDCZRQ() {
        return CLDCZRQ;
    }

    public void setCLDCZRQ(Date CLDCZRQ) {
        this.CLDCZRQ = CLDCZRQ;
    }

    public Boolean getGPDB() {
        return GPDB;
    }

    public void setGPDB(Boolean GPDB) {
        this.GPDB = GPDB;
    }

    public String getGPDBDWJWH() {
        return GPDBDWJWH;
    }

    public void setGPDBDWJWH(String GPDBDWJWH) {
        this.GPDBDWJWH = GPDBDWJWH;
    }

    public Date getTJSGDCBGRQ() {
        return TJSGDCBGRQ;
    }

    public void setTJSGDCBGRQ(Date TJSGDCBGRQ) {
        this.TJSGDCBGRQ = TJSGDCBGRQ;
    }

    public Date getPFRQ() {
        return PFRQ;
    }

    public void setPFRQ(Date PFRQ) {
        this.PFRQ = PFRQ;
    }

    public Date getGBRQ() {
        return GBRQ;
    }

    public void setGBRQ(Date GBRQ) {
        this.GBRQ = GBRQ;
    }

    public Integer getZJDJCFA() {
        return ZJDJCFA;
    }

    public void setZJDJCFA(Integer ZJDJCFA) {
        this.ZJDJCFA = ZJDJCFA;
    }

    public Integer getZJDJCFB() {
        return ZJDJCFB;
    }

    public void setZJDJCFB(Integer ZJDJCFB) {
        this.ZJDJCFB = ZJDJCFB;
    }

    public Integer getZJDJCFC() {
        return ZJDJCFC;
    }

    public void setZJDJCFC(Integer ZJDJCFC) {
        this.ZJDJCFC = ZJDJCFC;
    }

    public Integer getZJDJCFD() {
        return ZJDJCFD;
    }

    public void setZJDJCFD(Integer ZJDJCFD) {
        this.ZJDJCFD = ZJDJCFD;
    }

    public Integer getZJDJCFE() {
        return ZJDJCFE;
    }

    public void setZJDJCFE(Integer ZJDJCFE) {
        this.ZJDJCFE = ZJDJCFE;
    }

    public Integer getZJDJCFJGA() {
        return ZJDJCFJGA;
    }

    public void setZJDJCFJGA(Integer ZJDJCFJGA) {
        this.ZJDJCFJGA = ZJDJCFJGA;
    }

    public Integer getZJDJCFJGB() {
        return ZJDJCFJGB;
    }

    public void setZJDJCFJGB(Integer ZJDJCFJGB) {
        this.ZJDJCFJGB = ZJDJCFJGB;
    }

    public Integer getZJDJCFJGC() {
        return ZJDJCFJGC;
    }

    public void setZJDJCFJGC(Integer ZJDJCFJGC) {
        this.ZJDJCFJGC = ZJDJCFJGC;
    }

    public Integer getZJDJCFJGD() {
        return ZJDJCFJGD;
    }

    public void setZJDJCFJGD(Integer ZJDJCFJGD) {
        this.ZJDJCFJGD = ZJDJCFJGD;
    }

    public Integer getZJDJCFJGE() {
        return ZJDJCFJGE;
    }

    public void setZJDJCFJGE(Integer ZJDJCFJGE) {
        this.ZJDJCFJGE = ZJDJCFJGE;
    }

    public Integer getZJDJCFQYA() {
        return ZJDJCFQYA;
    }

    public void setZJDJCFQYA(Integer ZJDJCFQYA) {
        this.ZJDJCFQYA = ZJDJCFQYA;
    }

    public Integer getZJDJCFQYB() {
        return ZJDJCFQYB;
    }

    public void setZJDJCFQYB(Integer ZJDJCFQYB) {
        this.ZJDJCFQYB = ZJDJCFQYB;
    }

    public Integer getZJDJCFQYC() {
        return ZJDJCFQYC;
    }

    public void setZJDJCFQYC(Integer ZJDJCFQYC) {
        this.ZJDJCFQYC = ZJDJCFQYC;
    }

    public Integer getZJDJCFQYD() {
        return ZJDJCFQYD;
    }

    public void setZJDJCFQYD(Integer ZJDJCFQYD) {
        this.ZJDJCFQYD = ZJDJCFQYD;
    }

    public Integer getZJDJCFQYE() {
        return ZJDJCFQYE;
    }

    public void setZJDJCFQYE(Integer ZJDJCFQYE) {
        this.ZJDJCFQYE = ZJDJCFQYE;
    }

    public Integer getDJCFA() {
        return DJCFA;
    }

    public void setDJCFA(Integer DJCFA) {
        this.DJCFA = DJCFA;
    }

    public Integer getDJCFB() {
        return DJCFB;
    }

    public void setDJCFB(Integer DJCFB) {
        this.DJCFB = DJCFB;
    }

    public Integer getDJCFC() {
        return DJCFC;
    }

    public void setDJCFC(Integer DJCFC) {
        this.DJCFC = DJCFC;
    }

    public Integer getDJCFD() {
        return DJCFD;
    }

    public void setDJCFD(Integer DJCFD) {
        this.DJCFD = DJCFD;
    }

    public Integer getDJCFE() {
        return DJCFE;
    }

    public void setDJCFE(Integer DJCFE) {
        this.DJCFE = DJCFE;
    }

    public Integer getDJCFJGA() {
        return DJCFJGA;
    }

    public void setDJCFJGA(Integer DJCFJGA) {
        this.DJCFJGA = DJCFJGA;
    }

    public Integer getDJCFJGB() {
        return DJCFJGB;
    }

    public void setDJCFJGB(Integer DJCFJGB) {
        this.DJCFJGB = DJCFJGB;
    }

    public Integer getDJCFJGC() {
        return DJCFJGC;
    }

    public void setDJCFJGC(Integer DJCFJGC) {
        this.DJCFJGC = DJCFJGC;
    }

    public Integer getDJCFJGD() {
        return DJCFJGD;
    }

    public void setDJCFJGD(Integer DJCFJGD) {
        this.DJCFJGD = DJCFJGD;
    }

    public Integer getDJCFJGE() {
        return DJCFJGE;
    }

    public void setDJCFJGE(Integer DJCFJGE) {
        this.DJCFJGE = DJCFJGE;
    }

    public Integer getDJCFQYA() {
        return DJCFQYA;
    }

    public void setDJCFQYA(Integer DJCFQYA) {
        this.DJCFQYA = DJCFQYA;
    }

    public Integer getDJCFQYB() {
        return DJCFQYB;
    }

    public void setDJCFQYB(Integer DJCFQYB) {
        this.DJCFQYB = DJCFQYB;
    }

    public Integer getDJCFQYC() {
        return DJCFQYC;
    }

    public void setDJCFQYC(Integer DJCFQYC) {
        this.DJCFQYC = DJCFQYC;
    }

    public Integer getDJCFQYD() {
        return DJCFQYD;
    }

    public void setDJCFQYD(Integer DJCFQYD) {
        this.DJCFQYD = DJCFQYD;
    }

    public Integer getDJCFQYE() {
        return DJCFQYE;
    }

    public void setDJCFQYE(Integer DJCFQYE) {
        this.DJCFQYE = DJCFQYE;
    }

    public Integer getZJXSZRA() {
        return ZJXSZRA;
    }

    public void setZJXSZRA(Integer ZJXSZRA) {
        this.ZJXSZRA = ZJXSZRA;
    }

    public Integer getZJXSZRB() {
        return ZJXSZRB;
    }

    public void setZJXSZRB(Integer ZJXSZRB) {
        this.ZJXSZRB = ZJXSZRB;
    }

    public Integer getZJXSZRC() {
        return ZJXSZRC;
    }

    public void setZJXSZRC(Integer ZJXSZRC) {
        this.ZJXSZRC = ZJXSZRC;
    }

    public Integer getZJXSZRD() {
        return ZJXSZRD;
    }

    public void setZJXSZRD(Integer ZJXSZRD) {
        this.ZJXSZRD = ZJXSZRD;
    }

    public Integer getZJXSZRE() {
        return ZJXSZRE;
    }

    public void setZJXSZRE(Integer ZJXSZRE) {
        this.ZJXSZRE = ZJXSZRE;
    }

    public Integer getZJXSZRJGA() {
        return ZJXSZRJGA;
    }

    public void setZJXSZRJGA(Integer ZJXSZRJGA) {
        this.ZJXSZRJGA = ZJXSZRJGA;
    }

    public Integer getZJXSZRJGB() {
        return ZJXSZRJGB;
    }

    public void setZJXSZRJGB(Integer ZJXSZRJGB) {
        this.ZJXSZRJGB = ZJXSZRJGB;
    }

    public Integer getZJXSZRJGC() {
        return ZJXSZRJGC;
    }

    public void setZJXSZRJGC(Integer ZJXSZRJGC) {
        this.ZJXSZRJGC = ZJXSZRJGC;
    }

    public Integer getZJXSZRJGD() {
        return ZJXSZRJGD;
    }

    public void setZJXSZRJGD(Integer ZJXSZRJGD) {
        this.ZJXSZRJGD = ZJXSZRJGD;
    }

    public Integer getZJXSZRJGE() {
        return ZJXSZRJGE;
    }

    public void setZJXSZRJGE(Integer ZJXSZRJGE) {
        this.ZJXSZRJGE = ZJXSZRJGE;
    }

    public Integer getZJXSZRQYA() {
        return ZJXSZRQYA;
    }

    public void setZJXSZRQYA(Integer ZJXSZRQYA) {
        this.ZJXSZRQYA = ZJXSZRQYA;
    }

    public Integer getZJXSZRQYB() {
        return ZJXSZRQYB;
    }

    public void setZJXSZRQYB(Integer ZJXSZRQYB) {
        this.ZJXSZRQYB = ZJXSZRQYB;
    }

    public Integer getZJXSZRQYC() {
        return ZJXSZRQYC;
    }

    public void setZJXSZRQYC(Integer ZJXSZRQYC) {
        this.ZJXSZRQYC = ZJXSZRQYC;
    }

    public Integer getZJXSZRQYD() {
        return ZJXSZRQYD;
    }

    public void setZJXSZRQYD(Integer ZJXSZRQYD) {
        this.ZJXSZRQYD = ZJXSZRQYD;
    }

    public Integer getZJXSZRQYE() {
        return ZJXSZRQYE;
    }

    public void setZJXSZRQYE(Integer ZJXSZRQYE) {
        this.ZJXSZRQYE = ZJXSZRQYE;
    }

    public Double getJYFKE() {
        return JYFKE;
    }

    public void setJYFKE(Double JYFKE) {
        this.JYFKE = JYFKE;
    }

    public Double getJBJLJE() {
        return JBJLJE;
    }

    public void setJBJLJE(Double JBJLJE) {
        this.JBJLJE = JBJLJE;
    }

    public String getTJSHR() {
        return TJSHR;
    }

    public void setTJSHR(String TJSHR) {
        this.TJSHR = TJSHR;
    }

    public String getFZR() {
        return FZR;
    }

    public void setFZR(String FZR) {
        this.FZR = FZR;
    }

    public String getTBR() {
        return TBR;
    }

    public void setTBR(String TBR) {
        this.TBR = TBR;
    }

    public Date getTBRQ() {
        return TBRQ;
    }

    public void setTBRQ(Date TBRQ) {
        this.TBRQ = TBRQ;
    }
}
