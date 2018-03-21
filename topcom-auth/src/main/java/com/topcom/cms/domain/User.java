//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.common.model.Gender;
import com.topcom.cms.utils.MD5Utils;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(
        name = "t_user"
)
public class User extends BaseEntityModel {
    private static final long serialVersionUID = -7028200423890274993L;
    @Column(
            unique = true
    )
    private String code;
    private String fullName;
    private Gender gender;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "t_user_t_group",
            joinColumns = {@JoinColumn(
                    name = "users_ID"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "groups_ID"
            )}
    )
    private Set<Group> groups;
    @Column(
            unique = true,
            nullable = false
    )
    private String username;
    private String password;
    @JsonIgnore
    private String salt;
    private User.State state;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "t_user_t_role",
            joinColumns = {@JoinColumn(
                    name = "users_ID"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "roles_ID"
            )}
    )
    private Set<Role> roles;
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(
            name = "userinfo_id"
    )
    private UserInfo userInfo;


    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL},
            orphanRemoval = true
    )
    @JoinColumn(
            name = "companyinfo_id"
    )
    private CompanyInfo companyInfo;

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    @Transient
    private String[] groupNames;
    @Transient
    private String[] roleNames;

    @JsonIgnore
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getCode() {
        return this.code;
    }

    public String getFullName() {
        return this.fullName;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    public String getPassword() {
        return this.password;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public String getUsername() {
        return this.username;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User.State getState() {
        return this.state;
    }

    public void setState(User.State state) {
        this.state = state;
    }

    public User(Date dateCreated, Date dateModified, Boolean deleted, Long id, String code, String fullName, Gender gender, String password, String username, User.State state, Set<Group> groups) {
        this.dateCreated = dateCreated;
        this.dateModified = dateModified;
        this.deleted = deleted;
        this.setId(id);
        this.code = code;
        this.fullName = fullName;
        this.gender = gender;
        this.password = password;
        this.state = state;
        this.username = username;
        this.groups = groups;
    }

    public static String encodePassword(String password, String username) {
        String md5Pwd = MD5Utils.md5(password + username);
        return md5Pwd;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public User() {
    }

    public User(Long id) {
        this.setId(id);
    }

    public void setGroupNames(String[] groupNames) {
        this.groupNames = groupNames;
    }

    public void setRoleNames(String[] roleNames) {
        this.roleNames = roleNames;
    }

    @JsonIgnore
    @Transient
    public Set<Role> getAllRoles() {
        Set<Role> roles = this.getRoles();
        if(roles == null){
            roles = new HashSet<>();
        }
        Set<Group> groups = this.getGroups();
        if (groups != null) {
            Iterator var3 = groups.iterator();

            while (var3.hasNext()) {
                Group group = (Group) var3.next();
                if(group.getRoles() != null){
                    roles.addAll(group.getRoles());
                }
            }
        }

        return roles;
    }

    @JsonIgnore
    public Set<String> getGroupNames() {
        Set<Group> groups = this.getGroups();
        return groups == null ? null : (Set) groups.stream().map((group) -> {
            return group.getName();
        }).collect(Collectors.toSet());
    }
    //禁止序列化
    @JsonIgnore
    public Set<String> getRoleNames() {
        Set<Role> roles = this.getAllRoles();
        return roles == null ? null : (Set) roles.stream().map((role) -> {
            return role.getName();
        }).collect(Collectors.toSet());
    }

    @Transient
    @JsonIgnore
    public Set<String> getPermissions() {
        Set<String> permissions = new HashSet();
        Set<Role> roles = this.getAllRoles();
        if (roles != null) {
            Iterator var3 = roles.iterator();

            while (var3.hasNext()) {
                Role role = (Role) var3.next();
                Set<String> names = role.getPermissions();
                Iterator var6 = names.iterator();

                while (var6.hasNext()) {
                    String name = (String) var6.next();
                    permissions.add(name);
                }
            }
        }

        return permissions;
    }
    @Transient
    @JsonIgnore
    public Boolean isAdmin() {
        Set<Role> roles = this.getAllRoles();
        if (roles != null) {
            Iterator iterator = roles.iterator();
            while (iterator.hasNext()) {
                Role role = (Role) iterator.next();
                if(role != null && role.getAdmin() != null && role.getAdmin() == true){
                    return true;
                }
            }
        }
        return false;
    }

    @JsonIgnore
    public Set<String> getPermissionNames() {
        Set<String> permissionNames = new HashSet();
        Set<Role> roles = this.getAllRoles();
        if (roles != null) {
            Iterator var3 = roles.iterator();

            while (var3.hasNext()) {
                Role role = (Role) var3.next();
                Set<String> names = role.getPermissionNames();
                Iterator var6 = names.iterator();

                while (var6.hasNext()) {
                    String name = (String) var6.next();
                    permissionNames.add(name);
                }
            }
        }

        return permissionNames;
    }

    /**
     * 获取用户所有的resource
     *
     * @return
     */
    @Transient
    @JsonIgnore
    public Set<Resource> getResource() {
        Set<Resource> resourceSet = new LinkedHashSet<>();
        Set<Role> roles = this.getAllRoles();
        if (roles != null) {
            Iterator var3 = roles.iterator();

            while (var3.hasNext()) {
                Role role = (Role) var3.next();
                resourceSet.addAll(role.getResources());
            }
        }

        return resourceSet;
    }

    public static enum State {
        AVAILABLE,
        UNAVAILABLE,
        LOCKED,
        ONLINE,
        OFFLINE;

        private State() {
        }
    }
}
