package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.*;
import java.util.Date;

/**
 * 执法情况实体
 *
 * @author <a href="mailto:545314690@qq.om">lisenmiao</a>
 * @date 2018年3月26日11:54:12
 */
@Entity
@Table(name = "t_enforcement")
public class TjsEnforcement extends BaseEntityModel {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TjsEnforcement() {
    }
    @ManyToOne
    @JoinColumn(name = "companyId",columnDefinition="bigint(20) COMMENT '企业id'")
    private TjsSpecialCompany company;
    /**
     * 执法检查起始时间
     */
    @Column(columnDefinition = "datetime COMMENT '执法检查起始时间'")
    private Date ZFJCKSSS;
    /**
     * 执法检查截止时间
     */
    @Column(columnDefinition = "datetime COMMENT '执法检查截止时间'")
    private Date ZFJCJZSJ;
    /**
     * 执法检查性质
     */
    @Column(columnDefinition = "varchar(20) COMMENT '执法检查性质'")
    private String ZFJCXZ;
    /**
     * 被检查单位名称
     */
    @Column(columnDefinition = "varchar(20) COMMENT '被检查单位名称'")
    private String BJCDWMC;
    /**
     * 企业类别
     */
    @Column(columnDefinition = "varchar(20) COMMENT '企业类别'")
    private String QYLB;
    /**
     * 社会信用代码
     */
    @Column(columnDefinition = "varchar(30) COMMENT '社会信用代码'")
    private String SHXYDM;
    /**
     * 管理分类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '管理分类'")
    private String GLFL;
    /**
     * 企业注册地址(省)
     */
    @Column(columnDefinition = "varchar(20) COMMENT '企业注册地址(省)'")
    private String QYZCDZP;
    /**
     * 企业注册地址(市)
     */
    @Column(columnDefinition = "varchar(20) COMMENT '企业注册地址(市)'")
    private String QYZCDZS;
    /**
     * 企业注册地址(县)
     */
    @Column(columnDefinition = "varchar(20) COMMENT '企业注册地址(县)'")
    private String QYZCDZX;
    /**
     * 生产经营地址(省)
     */
    @Column(columnDefinition = "varchar(20) COMMENT '生产经营地址(省)'")
    private String SCJJDZP;
    /**
     * 生产经营地址(市)
     */
    @Column(columnDefinition = "varchar(20) COMMENT '生产经营地址(市)'")
    private String SCJJDZS;
    /**
     * 生产经营地址(县)
     */
    @Column(columnDefinition = "varchar(20) COMMENT '生产经营地址(县)'")
    private String SCJJDZX;
    /**
     * 所属行业
     */
    @Column(columnDefinition = "varchar(20) COMMENT '所属行业'")
    private String SSHY;
    /**
     * 门类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '门类'")
    private String ML;
    /**
     * 大类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '大类'")
    private String DL;
    /**
     * 中类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '中类'")
    private String SL;
    /**
     * 小类
     */
    @Column(columnDefinition = "varchar(20) COMMENT '小类'")
    private String XL;

    /**
     * 是否为随机抽取检查单位
     */
    @Column(columnDefinition = "bit COMMENT '是否为随机抽取检查单位'")
    private Boolean SFWSJCQJCDW;
    /**
     * 执法检查类别：计划执法、专项执法、其他执法
     */
    @Column(columnDefinition = "varchar(10) COMMENT '执法检查类别'")
    private String JFJCLB;
    /**
     * 是否整改复查：计划执法、专项执法、其他执法
     */
    @Column(columnDefinition = "bit COMMENT '是否整改复查'")
    private Boolean SFZGFC;
    /**
     * 是否含职业卫生执法检查
     */
    @Column(columnDefinition = "bit COMMENT '是否含职业卫生执法检查'")
    private Boolean SFHZYWSZFJC;
    /**
     * 是否举报核实执法检查
     */
    @Column(columnDefinition = "bit COMMENT '是否举报核实执法检查'")
    private Boolean SFJBHSZFJC;
    /**
     * 查处安全生产违法违规行为（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '查处安全生产违法违规行为（项）'")
    private Integer CCAQSCWFWGXWX;
    /**
     * 查处一般事故隐患（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '查处一般事故隐患（项）'")
    private Integer CCYBSGYHX;
    /**
     * 查处重大事故隐患（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '查处重大事故隐患（项）'")
    private Integer CCZDSGYHX;
    /**
     * 其中：挂牌督办项（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '其中：挂牌督办项（项）'")
    private Integer GPDBX;
    /**
     * 执法文书（份）
     */
    @Column(columnDefinition = "int(5) COMMENT '执法文书（份）'")
    private Integer ZFWF;
    /**
     * 现场检查记录
     */
    @Column(columnDefinition = "bit COMMENT '现场检查记录'")
    private Boolean XCJCJL;
    /**
     * 现场处理措施决定书
     */
    @Column(columnDefinition = "bit COMMENT '现场处理措施决定书'")
    private Boolean XCCLCSJDS;
    /**
     * 责令限期整改指令书
     */
    @Column(columnDefinition = "bit COMMENT '责令限期整改指令书'")
    private Boolean ZLXQZGZLS;
    /**
     * 强制措施决定书
     */
    @Column(columnDefinition = "bit COMMENT '强制措施决定书'")
    private Boolean QZCZJDS;
    /**
     * 整改复查意见书
     */
    @Column(columnDefinition = "bit COMMENT '整改复查意见书'")
    private Boolean ZGFCYJS;
    /**
     * 立案审批表
     */
    @Column(columnDefinition = "bit COMMENT '立案审批表'")
    private Boolean LASPB;
    /**
     * 询问笔录
     */
    @Column(columnDefinition = "bit COMMENT '询问笔录'")
    private Boolean XWBL;
    /**
     * 行政（当场）处罚决定书（单位）（份）
     */
    @Column(columnDefinition = "int(5) COMMENT '行政（当场）处罚决定书（单位）（份）'")
    private Integer XZDCCFJDSDW;
    /**
     * 行政（当场）处罚决定书（个人）（份）
     */
    @Column(columnDefinition = "int(5) COMMENT '行政（当场）处罚决定书（个人）（份）'")
    private Integer XZDCCFJDSGR;
    /**
     * 行政处罚决定书（单位）（份）
     */
    @Column(columnDefinition = "int(6) COMMENT '行政处罚决定书（单位）（份）'")
    private Integer XZCFJDSDW;
    /**
     * 行政处罚决定书（个人）（份）
     */
    @Column(columnDefinition = "int(6) COMMENT '行政处罚决定书（个人）（份）'")
    private Integer XZCFJDSGR;
    /**
     * 其他文书
     */
    @Column(columnDefinition = "varchar(100) COMMENT '其他文书'")
    private String QTWS;
    /**
     * 已整改安全生产违法违规行为（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '已整改安全生产违法违规行为（项）'")
    private Integer YZGAQSCWFWGXW;
    /**
     * 已整改一般事故隐患（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '已整改一般事故隐患（项）'")
    private Integer YZGYBSGYH;
    /**
     * 已整改重大事故隐患（项）
     */
    @Column(columnDefinition = "int(5) COMMENT '已整改重大事故隐患（项）'")
    private Integer YZGZDSGYH;
    /**
     * 对生产经营单位行政处罚
     */
    @Column(columnDefinition = "bit COMMENT '对生产经营单位行政处罚'")
    private Boolean DSCJYDWXZCF;
    /**
     * 对生产经营单位主要负责人行政处罚
     */
    @Column(columnDefinition = "bit COMMENT '对生产经营单位主要负责人行政处罚'")
    private Boolean DSCJYDWZYFZRXZCF;
    /**
     * 责令停产整顿
     */
    @Column(columnDefinition = "bit COMMENT '责令停产整顿'")
    private Boolean ZLTCZD;
    /**
     * 提请关闭
     */
    @Column(columnDefinition = "bit COMMENT '提请关闭'")
    private Boolean TQGB;
    /**
     * 实际关闭
     */
    @Column(columnDefinition = "bit COMMENT '实际关闭'")
    private Boolean SJGB;
    /**
     * 经济处罚
     */
    @Column(columnDefinition = "bit COMMENT '经济处罚'")
    private Boolean JJCF;
    /**
     * 罚款额（万元）
     */
    @Column(columnDefinition = "double COMMENT '罚款额（万元）'")
    private Double FKE;
    /**
     * 实际收缴罚款（万元）
     */
    @Column(columnDefinition = "double COMMENT '实际收缴罚款（万元）'")
    private Double SJSJFK;
    /**
     * 企业负责人
     */
    @Column(columnDefinition = "varchar(20) COMMENT '企业负责人'")
    private String QYFZR;
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

    public Date getZFJCKSSS() {
        return ZFJCKSSS;
    }

    public void setZFJCKSSS(Date ZFJCKSSS) {
        this.ZFJCKSSS = ZFJCKSSS;
    }

    public Date getZFJCJZSJ() {
        return ZFJCJZSJ;
    }

    public void setZFJCJZSJ(Date ZFJCJZSJ) {
        this.ZFJCJZSJ = ZFJCJZSJ;
    }

    public String getZFJCXZ() {
        return ZFJCXZ;
    }

    public void setZFJCXZ(String ZFJCXZ) {
        this.ZFJCXZ = ZFJCXZ;
    }

    public String getBJCDWMC() {
        return BJCDWMC;
    }

    public void setBJCDWMC(String BJCDWMC) {
        this.BJCDWMC = BJCDWMC;
    }

    public String getQYLB() {
        return QYLB;
    }

    public void setQYLB(String QYLB) {
        this.QYLB = QYLB;
    }

    public String getSHXYDM() {
        return SHXYDM;
    }

    public void setSHXYDM(String SHXYDM) {
        this.SHXYDM = SHXYDM;
    }

    public String getGLFL() {
        return GLFL;
    }

    public void setGLFL(String GLFL) {
        this.GLFL = GLFL;
    }

    public String getQYZCDZP() {
        return QYZCDZP;
    }

    public void setQYZCDZP(String QYZCDZP) {
        this.QYZCDZP = QYZCDZP;
    }

    public String getQYZCDZS() {
        return QYZCDZS;
    }

    public void setQYZCDZS(String QYZCDZS) {
        this.QYZCDZS = QYZCDZS;
    }

    public String getQYZCDZX() {
        return QYZCDZX;
    }

    public void setQYZCDZX(String QYZCDZX) {
        this.QYZCDZX = QYZCDZX;
    }

    public String getSCJJDZP() {
        return SCJJDZP;
    }

    public void setSCJJDZP(String SCJJDZP) {
        this.SCJJDZP = SCJJDZP;
    }

    public String getSCJJDZS() {
        return SCJJDZS;
    }

    public void setSCJJDZS(String SCJJDZS) {
        this.SCJJDZS = SCJJDZS;
    }

    public String getSCJJDZX() {
        return SCJJDZX;
    }

    public void setSCJJDZX(String SCJJDZX) {
        this.SCJJDZX = SCJJDZX;
    }

    public String getSSHY() {
        return SSHY;
    }

    public void setSSHY(String SSHY) {
        this.SSHY = SSHY;
    }

    public String getML() {
        return ML;
    }

    public void setML(String ML) {
        this.ML = ML;
    }

    public String getDL() {
        return DL;
    }

    public void setDL(String DL) {
        this.DL = DL;
    }

    public String getSL() {
        return SL;
    }

    public void setSL(String SL) {
        this.SL = SL;
    }

    public String getXL() {
        return XL;
    }

    public void setXL(String XL) {
        this.XL = XL;
    }

    public Boolean getSFWSJCQJCDW() {
        return SFWSJCQJCDW;
    }

    public void setSFWSJCQJCDW(Boolean SFWSJCQJCDW) {
        this.SFWSJCQJCDW = SFWSJCQJCDW;
    }

    public String getJFJCLB() {
        return JFJCLB;
    }

    public void setJFJCLB(String JFJCLB) {
        this.JFJCLB = JFJCLB;
    }

    public Boolean getSFZGFC() {
        return SFZGFC;
    }

    public void setSFZGFC(Boolean SFZGFC) {
        this.SFZGFC = SFZGFC;
    }

    public Boolean getSFHZYWSZFJC() {
        return SFHZYWSZFJC;
    }

    public void setSFHZYWSZFJC(Boolean SFHZYWSZFJC) {
        this.SFHZYWSZFJC = SFHZYWSZFJC;
    }

    public Boolean getSFJBHSZFJC() {
        return SFJBHSZFJC;
    }

    public void setSFJBHSZFJC(Boolean SFJBHSZFJC) {
        this.SFJBHSZFJC = SFJBHSZFJC;
    }

    public Integer getCCAQSCWFWGXWX() {
        return CCAQSCWFWGXWX;
    }

    public void setCCAQSCWFWGXWX(Integer CCAQSCWFWGXWX) {
        this.CCAQSCWFWGXWX = CCAQSCWFWGXWX;
    }

    public Integer getCCYBSGYHX() {
        return CCYBSGYHX;
    }

    public void setCCYBSGYHX(Integer CCYBSGYHX) {
        this.CCYBSGYHX = CCYBSGYHX;
    }

    public Integer getCCZDSGYHX() {
        return CCZDSGYHX;
    }

    public void setCCZDSGYHX(Integer CCZDSGYHX) {
        this.CCZDSGYHX = CCZDSGYHX;
    }

    public Integer getGPDBX() {
        return GPDBX;
    }

    public void setGPDBX(Integer GPDBX) {
        this.GPDBX = GPDBX;
    }

    public Integer getZFWF() {
        return ZFWF;
    }

    public void setZFWF(Integer ZFWF) {
        this.ZFWF = ZFWF;
    }

    public Boolean getXCJCJL() {
        return XCJCJL;
    }

    public void setXCJCJL(Boolean XCJCJL) {
        this.XCJCJL = XCJCJL;
    }

    public Boolean getXCCLCSJDS() {
        return XCCLCSJDS;
    }

    public void setXCCLCSJDS(Boolean XCCLCSJDS) {
        this.XCCLCSJDS = XCCLCSJDS;
    }

    public Boolean getZLXQZGZLS() {
        return ZLXQZGZLS;
    }

    public void setZLXQZGZLS(Boolean ZLXQZGZLS) {
        this.ZLXQZGZLS = ZLXQZGZLS;
    }

    public Boolean getQZCZJDS() {
        return QZCZJDS;
    }

    public void setQZCZJDS(Boolean QZCZJDS) {
        this.QZCZJDS = QZCZJDS;
    }

    public Boolean getZGFCYJS() {
        return ZGFCYJS;
    }

    public void setZGFCYJS(Boolean ZGFCYJS) {
        this.ZGFCYJS = ZGFCYJS;
    }

    public Boolean getLASPB() {
        return LASPB;
    }

    public void setLASPB(Boolean LASPB) {
        this.LASPB = LASPB;
    }

    public Boolean getXWBL() {
        return XWBL;
    }

    public void setXWBL(Boolean XWBL) {
        this.XWBL = XWBL;
    }

    public Integer getXZDCCFJDSDW() {
        return XZDCCFJDSDW;
    }

    public void setXZDCCFJDSDW(Integer XZDCCFJDSDW) {
        this.XZDCCFJDSDW = XZDCCFJDSDW;
    }

    public Integer getXZDCCFJDSGR() {
        return XZDCCFJDSGR;
    }

    public void setXZDCCFJDSGR(Integer XZDCCFJDSGR) {
        this.XZDCCFJDSGR = XZDCCFJDSGR;
    }

    public Integer getXZCFJDSDW() {
        return XZCFJDSDW;
    }

    public void setXZCFJDSDW(Integer XZCFJDSDW) {
        this.XZCFJDSDW = XZCFJDSDW;
    }

    public Integer getXZCFJDSGR() {
        return XZCFJDSGR;
    }

    public void setXZCFJDSGR(Integer XZCFJDSGR) {
        this.XZCFJDSGR = XZCFJDSGR;
    }

    public String getQTWS() {
        return QTWS;
    }

    public void setQTWS(String QTWS) {
        this.QTWS = QTWS;
    }

    public Integer getYZGAQSCWFWGXW() {
        return YZGAQSCWFWGXW;
    }

    public void setYZGAQSCWFWGXW(Integer YZGAQSCWFWGXW) {
        this.YZGAQSCWFWGXW = YZGAQSCWFWGXW;
    }

    public Integer getYZGYBSGYH() {
        return YZGYBSGYH;
    }

    public void setYZGYBSGYH(Integer YZGYBSGYH) {
        this.YZGYBSGYH = YZGYBSGYH;
    }

    public Integer getYZGZDSGYH() {
        return YZGZDSGYH;
    }

    public void setYZGZDSGYH(Integer YZGZDSGYH) {
        this.YZGZDSGYH = YZGZDSGYH;
    }

    public Boolean getDSCJYDWXZCF() {
        return DSCJYDWXZCF;
    }

    public void setDSCJYDWXZCF(Boolean DSCJYDWXZCF) {
        this.DSCJYDWXZCF = DSCJYDWXZCF;
    }

    public Boolean getDSCJYDWZYFZRXZCF() {
        return DSCJYDWZYFZRXZCF;
    }

    public void setDSCJYDWZYFZRXZCF(Boolean DSCJYDWZYFZRXZCF) {
        this.DSCJYDWZYFZRXZCF = DSCJYDWZYFZRXZCF;
    }

    public Boolean getZLTCZD() {
        return ZLTCZD;
    }

    public void setZLTCZD(Boolean ZLTCZD) {
        this.ZLTCZD = ZLTCZD;
    }

    public Boolean getTQGB() {
        return TQGB;
    }

    public void setTQGB(Boolean TQGB) {
        this.TQGB = TQGB;
    }

    public Boolean getSJGB() {
        return SJGB;
    }

    public void setSJGB(Boolean SJGB) {
        this.SJGB = SJGB;
    }

    public Boolean getJJCF() {
        return JJCF;
    }

    public void setJJCF(Boolean JJCF) {
        this.JJCF = JJCF;
    }

    public Double getFKE() {
        return FKE;
    }

    public void setFKE(Double FKE) {
        this.FKE = FKE;
    }

    public Double getSJSJFK() {
        return SJSJFK;
    }

    public void setSJSJFK(Double SJSJFK) {
        this.SJSJFK = SJSJFK;
    }

    public String getQYFZR() {
        return QYFZR;
    }

    public void setQYFZR(String QYFZR) {
        this.QYFZR = QYFZR;
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

    public TjsSpecialCompany getCompany() {
        return company;
    }

    public void setCompany(TjsSpecialCompany company) {
        this.company = company;
    }
}
