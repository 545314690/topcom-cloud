//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseTreeEntityModel;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(
    name = "T_ABBREVIATION"
)
@NamedQueries({@NamedQuery(
    name = "Abbreviation.getRoot",
    query = "SELECT a FROM Abbreviation a where a.parent is null"
)})
public class Abbreviation extends BaseTreeEntityModel<Abbreviation> {
    private static final long serialVersionUID = -7028200423890274993L;
    @Column(
        name = "SHORTNAME",
        length = 20
    )
    String shortName;
    @Column(
        name = "FLAG"
    )
    int flag;
    @OneToOne(
        cascade = {CascadeType.ALL},
        fetch = FetchType.EAGER
    )
    @JoinColumn(
        name = "U_ID"
    )
    Unit unit;

    public Abbreviation() {
    }

    public String getShortName() {
        return this.shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Unit getUnit() {
        return this.unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
