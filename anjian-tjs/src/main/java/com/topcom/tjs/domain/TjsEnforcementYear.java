package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import javax.persistence.*;


/**
 * 安全生产执法年度情况表
 */
@Entity
@Table(name = "t_enforcement_year")
public class TjsEnforcementYear extends BaseEntityModel {

    @ManyToOne
    @JoinColumn(name = "tjsOrganId",columnDefinition="bigint(20) COMMENT '填报单位'")
    private TjsOrgan tjsOrgan;

    @Column(columnDefinition = "varchar(50) COMMENT '指标名称'")
    private String metricName;

    @Column(columnDefinition = "varchar(20) COMMENT '计量单位'")
    private String unit;

    @Column(columnDefinition = "int(5) COMMENT '煤矿'")
    private Integer coalMine;

    @Column(columnDefinition = "int(5) COMMENT '金属非金属矿山'")
    private Integer metalMine;

    @Column(columnDefinition = "int(5) COMMENT '化工'")
    private Integer chemical;

    @Column(columnDefinition = "int(5) COMMENT '烟花爆竹'")
    private Integer firecrackers;

    @Column(columnDefinition = "int(5) COMMENT '烟花爆竹'")
    private Integer industryMetallurgy;

    @Column(columnDefinition = "int(5) COMMENT '冶金'")
    private Integer metallurgy;

    @Column(columnDefinition = "int(5) COMMENT '规模以上其他工贸生产经营单位'")
    private Integer tradeOther;

    @Column(columnDefinition = "int(5) COMMENT '规模以上其他商贸制造业'")
    private Integer manufactOther;

    @Column(columnDefinition = "int(5) COMMENT '其他'")
    private Integer other;


    public TjsOrgan getTjsOrgan() {
        return tjsOrgan;
    }

    public void setTjsOrgan(TjsOrgan tjsOrgan) {
        this.tjsOrgan = tjsOrgan;
    }

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getCoalMine() {
        return coalMine;
    }

    public void setCoalMine(Integer coalMine) {
        this.coalMine = coalMine;
    }

    public Integer getMetalMine() {
        return metalMine;
    }

    public void setMetalMine(Integer metalMine) {
        this.metalMine = metalMine;
    }

    public Integer getChemical() {
        return chemical;
    }

    public void setChemical(Integer chemical) {
        this.chemical = chemical;
    }

    public Integer getFirecrackers() {
        return firecrackers;
    }

    public void setFirecrackers(Integer firecrackers) {
        this.firecrackers = firecrackers;
    }

    public Integer getIndustryMetallurgy() {
        return industryMetallurgy;
    }

    public void setIndustryMetallurgy(Integer industryMetallurgy) {
        this.industryMetallurgy = industryMetallurgy;
    }

    public Integer getMetallurgy() {
        return metallurgy;
    }

    public void setMetallurgy(Integer metallurgy) {
        this.metallurgy = metallurgy;
    }

    public Integer getTradeOther() {
        return tradeOther;
    }

    public void setTradeOther(Integer tradeOther) {
        this.tradeOther = tradeOther;
    }

    public Integer getManufactOther() {
        return manufactOther;
    }

    public void setManufactOther(Integer manufactOther) {
        this.manufactOther = manufactOther;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }
}
