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
    name = "T_NOTICE"
)
public class Notice extends BaseEntityModel {
    private static final long serialVersionUID = -7728256029970557576L;
    @Column(
        name = "TITLE"
    )
    String title;
    @Column(
        name = "FLAG"
    )
    int flag;
    @Column(
        name = "CONTENT"
    )
    String content;

    public Notice() {
    }

    public int getFlag() {
        return this.flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
