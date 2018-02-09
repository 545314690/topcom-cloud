package com.topcom.cms.vo;

import com.topcom.cms.common.model.Gender;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.cms.domain.Group;
import com.topcom.cms.domain.Role;
import com.topcom.cms.domain.User;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by lism on 17-12-15.
 * 用户搜索
 */
public class UserSearchVO implements Serializable{

    private String username;
    private String fullName;
    private Set<Group> groups;
    private Set<Role> roles;
    private User.State state;
    private Integer type;
    private Gender gender;
    private PageRequest pageRequest;


    public UserSearchVO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public User.State getState() {
        return state;
    }

    public void setState(User.State state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }
}
