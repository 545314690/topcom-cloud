//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topcom.cms.base.model.BaseTreeEntityModel;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(
        name = "t_group"
)
@NamedQueries({@NamedQuery(
        name = "Group.getRoot",
        query = "select  g  from Group g  where g.parent is null"
)})
public class Group extends BaseTreeEntityModel<Group> implements Comparable<Group> {
    private static final long serialVersionUID = -3266293653834546223L;
    @Column(
            unique = true,
            nullable = false
    )
    private String name;
    private String description;
    private int level;
    private Boolean available;
    @Column(
            name = "childId"
    )
    private Long childId;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "t_group_t_role",
            joinColumns = {@JoinColumn(
                    name = "groups_ID"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "roles_ID"
            )}
    )
    @JsonIgnore
    private Set<Role> roles;
    @Transient
    @JsonBackReference
    private Long parentId;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Group(String text, Date dateCreated, Date dateModified, Boolean deleted, Long id, String description, String name, Long childId, int level) {
        this.setText(text);
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.deleted = deleted;
        this.setId(id);
        this.description = description;
        this.name = name;
        this.childId = childId;
        this.level = level;
    }

    public Group() {
    }

    public Long getChildId() {
        return this.childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public int compareTo(Group o) {
        return o.getId() == this.getId() ? 0 : this.getChildId().compareTo(o.getChildId());
    }

    public void sortByChildId() {
        List<Group> children = this.getChildren();
        if (children != null) {
            Iterator var2 = children.iterator();

            while (var2.hasNext()) {
                Group child = (Group) var2.next();
                child.sortByChildId();
            }

            Collections.sort(children);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @JsonIgnore
    public Set<String> getRoleNames() {
        Set<String> roleNames = new HashSet();
        Set<Role> roles = this.getRoles();
        if (roles != null) {
            Iterator var3 = roles.iterator();

            while (var3.hasNext()) {
                Role role = (Role) var3.next();
                roleNames.add(role.getName());
            }
        }

        return roleNames;
    }
}
