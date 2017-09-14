package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseTreeEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * 组织机构
 * Created by topcom on 2017/8/23 0023.
 */

@Entity
@Table(name = "t_organizational_structure")
@EntityListeners({AuditingEntityListener.class})
@NamedQueries({    @NamedQuery(
        name = "OrganizationalStructure.getRoot",
        query = "SELECT os  FROM OrganizationalStructure os  where os.parent is null"
)})
public class OrganizationalStructure extends BaseTreeEntityModel<OrganizationalStructure> {

    @Column(name = "name")
    private String name;

    /**
     * 用户ID
     */
    @CreatedBy
    @LastModifiedBy
    private Long userId;


    @Transient
    private Long parentId;

    @Column(name = "childId")
    Long childId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public OrganizationalStructure(String name,Long id) {
        this.name = name;
        this.setId(id);
    }
    public OrganizationalStructure() {
    }
}
