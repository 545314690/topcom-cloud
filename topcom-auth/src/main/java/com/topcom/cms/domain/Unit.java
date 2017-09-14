//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(
    name = "T_UNIT"
)
public class Unit extends BaseEntityModel {
    private static final long serialVersionUID = 5461376035896856333L;
    @Column(
        name = "UNITNAME"
    )
    String unitName;

    public Unit() {
    }

    public String getUnitName() {
        return this.unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
