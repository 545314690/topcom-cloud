//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topcom.cms.base.model.BaseEntityModel;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(
    name = "t_role"
)
public class Role extends BaseEntityModel {
    private static final long serialVersionUID = -8983502095738993626L;
    @ManyToMany(
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "t_role_t_resource",
        joinColumns = {@JoinColumn(
    name = "roles_ID"
)},
        inverseJoinColumns = {@JoinColumn(
    name = "resources_ID"
)}
    )
    Set<Resource> resources;
    @Column(
        unique = true,
        nullable = false
    )
    String name;
    String description;
    Boolean available;
    Boolean admin;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Set<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Role(Date dateCreated, Date dateModified, Boolean deleted, Long id, String name, String description, boolean available) {
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.deleted = deleted;
        this.setId(id);
        this.name = name;
        this.description = description;
        this.available = Boolean.valueOf(available);
    }

    @JsonIgnore
    public Set<String> getPermissionNames() {
        Set<String> permissionNames = new HashSet();
        Set<Resource> resources = this.getResources();
        if(resources != null) {
            Iterator var3 = resources.iterator();

            while(var3.hasNext()) {
                Resource resource = (Resource)var3.next();
                permissionNames.add(resource.getName());
            }
        }

        return permissionNames;
    }

    public Role() {
    }

    @JsonIgnore
    public Set<String> getPermissions() {
        Set<String> permissions = new HashSet();
        Set<Resource> resources = this.getResources();
        if(resources != null) {
            Iterator var3 = resources.iterator();

            while(var3.hasNext()) {
                Resource resource = (Resource)var3.next();
                permissions.add(resource.getPermission());
            }
        }

        return permissions;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
