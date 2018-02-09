//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.base.model.BaseModel;
import com.topcom.cms.base.model.BaseTreeEntityModel;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "t_application"
)
/**
 * 应用类
 * @author lism
 */
public class Application extends BaseEntityModel {
    private static final long serialVersionUID = -7028200423890274993L;
    @Column
    private String name;

    @Lob
    @Column
    private String description;

    @OneToMany
    @JoinColumn(name = "appId")
    private Set<Resource> resources = new LinkedHashSet<>();
    public Application() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
