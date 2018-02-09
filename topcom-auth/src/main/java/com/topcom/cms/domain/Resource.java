package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseTreeEntityModel;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(
        name = "t_resource"
)
@NamedQueries({@NamedQuery(
        name = "Resource.getRoot",
        query = "SELECT r  FROM com.topcom.cms.domain.Resource r  where r.parent is null"
)})
/**
 * 访问资源实体
 * @author lism
 */
public class Resource extends BaseTreeEntityModel<Resource> implements Comparable<Resource> {
    private String description;
    private String permission;
    private Boolean available;
    /**
     * 应用id
     */
    private Long appId;
    @Enumerated(EnumType.STRING)
    private Resource.Type type;

    @Enumerated(EnumType.STRING)
    private Auth auth;
    @Column(
            name = "childId"
    )
    Long childId;
    @Column(
            unique = true,
            nullable = false
    )
    private String name;
    @Column(
            unique = true
    )
    private String url;
    private String icon;
    private boolean leaf;
    @Transient
    private Long parentId;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return this.permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Resource.Type getType() {
        return this.type;
    }

    public void setType(Resource.Type type) {
        this.type = type;
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        if (this.getParent() != null) {
            return this.getParent().getId();
        }
        return null;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Resource(Long id, Resource.Type type, Long childId, String name, String url, boolean leaf, String icon, Long parentId) {
        this.setId(id);
        this.type = type;
        this.childId = childId;
        this.name = name;
        this.url = url;
        this.setLeaf(leaf);
        this.icon = icon;
        this.parentId = parentId;
    }

    @Override
    public boolean isLeaf() {
        return this.leaf;
    }

    public Resource(String text, Date dateCreated, Date dateModified, Boolean deleted, Long id, String description, String name, String url, String icon, Long childId) {
        this.setText(text);
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.deleted = deleted;
        this.setId(id);
        this.description = description;
        this.name = name;
        this.url = url;
        this.icon = icon;
        this.childId = childId;
    }

    public Resource() {
    }

    public Long getChildId() {
        return this.childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    @Override
    public int compareTo(Resource o) {
        return o.getId().equals(this.getId()) ? 0 : this.getChildId().compareTo(o.getChildId());
    }

    public void sortByChildId() {
        List<Resource> children = this.getChildren();
        if (children != null && children.size() > 0) {
            Iterator var2 = children.iterator();

            while (var2.hasNext()) {
                Resource child = (Resource) var2.next();
                child.sortByChildId();
            }

            Collections.sort(children, new ChildrenComparator());
        }
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    // 自定义比较器：按childrenid排序
    static class ChildrenComparator implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            Resource resource1 = (Resource) o1;
            Resource resource2 = (Resource) o2;
            return new Double(resource1.childId == null ? 1 : resource1.childId).compareTo(new Double(resource2.childId == null ? 1 : resource2.childId));
        }
    }

    public static enum Type {
        DIRECTORY,
        MENU,
        BUTTON;

        private Type() {
        }
    }

    public static enum Auth {
        POST, GET, DELETE, PUT, ALL
    }
}
