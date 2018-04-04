package com.topcom.tjs.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import javax.persistence.*;


/**
 * 安全生产执法年度情况表
 */
@Entity
@Table(name = "t_enforcement_year")
public class TjsEnforcementYear extends BaseEntityModel {

//    @ManyToOne
//    @JoinColumn(name = "tjsOrganId",columnDefinition="bigint(20) COMMENT '填报单位'")
//    private TjsOrgan tjsOrgan;

    @Column(columnDefinition = "varchar(50) COMMENT '指标名称'")
    private String metricName;

    @Column(columnDefinition = "varchar(20) COMMENT '计量单位'")
    private String unit;

    @Column(columnDefinition = "varchar(50) COMMENT '重点行业'")
    private String keyTrades;

    @Column(columnDefinition = "int(5) COMMENT '年份'")
    private Integer year;

    @Column(columnDefinition = "varchar(20) COMMENT '负责人'")
    private String FZR;

    @Column(columnDefinition = "varchar(20) COMMENT '填表人'")
    private String TBR;
    @Column(columnDefinition = "int(7) COMMENT '数值'")
    private Integer value;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getKeyTrades() {
        return keyTrades;
    }

    public void setKeyTrades(String keyTrades) {
        this.keyTrades = keyTrades;
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

//    public TjsOrgan getTjsOrgan() {
//        return tjsOrgan;
//    }
//
//    public void setTjsOrgan(TjsOrgan tjsOrgan) {
//        this.tjsOrgan = tjsOrgan;
//    }

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

}
