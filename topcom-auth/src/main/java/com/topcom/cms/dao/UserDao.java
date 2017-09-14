package com.topcom.cms.dao;

import java.util.List;
import java.util.Set;

import com.topcom.cms.domain.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.domain.User;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * 用户信息访问接口
 *
 * @author lism
 */
public interface UserDao extends GenericDao<User, Long> {


    /**
     * 获得用户ID为参数id的用户信息
     *
     * @param id
     * @return 用户对象
     */
    @Query("select u from User u where u.id=:id")
    public User findById(@Param("id") long id);

    /**
     * 模糊查询用户名为参数username的用户列表信息
     *
     * @param pageable 分页配置
     * @param username
     * @return 分页列表
     */
    @Query("from User U where U.username like %?1%")
    public Page<User> fuzzyQueryByName(Pageable pageable, String username);

    /**
     * @param pageable 分页配置
     * @param fullName 用户全称
     * @return 分页列表
     */
    @Query("from User U where U.fullName like %?1%")
    public Page<User> fuzzyQueryByFullName(Pageable pageable, String fullName);

    /**
     * 晨光要求根据 用户姓名 或者人员编码,用户名 所属部门 查询用户信息
     *
     * @param pageable
     * @param fac
     * @return
     */

    @Query("select U from User U join U.groups G where U.fullName like %?1% or U.code like %?1% or U.username like %?1% or G.name like %?1%")
    public Page<User> fuzzyQueryByFAC(Pageable pageable, String fac);

    /**
     * 通过用户名和密码查询用户信息
     *
     * @param username
     * @param password
     * @return 用户对象
     */
    public User findByUsernameAndPassword(String username, String password);

    /**
     * 通过用户组ID 获取该用户所在组所对应的菜单(权限)
     *
     * @param id 用户ID
     * @return 权限集合
     */
    @Query("select distinct s.permission from User u  join u.groups g join g.roles r join r.resources s where u.id=:id")
    public List<String> findAuthsByIdWithGroup(@Param("id") Long id);

    /**
     * 通过用户组ID 获取该用户拥有的角色所对应的菜单(权限)
     *
     * @param id 用户ID
     * @return 权限集合
     */
    @Query("select distinct s.permission from User u  join u.roles r join r.resources s where u.id=:id ")
    public List<String> findAuthByIdNoGroup(@Param("id") Long id);

    /**
     * 通过用户组ID 获取该用户拥有的角色所对应的菜单(权限)
     *
     * @param id 用户ID
     * @return 权限url集合
     */
    @Query("select distinct new Resource(s.id,s.type, s.childId,s.name, s.url, s.leaf,s.icon,s.parent.id) from User u  join u.roles r join r.resources s where u.id=:id ")
    public Set<Resource> findResourceById(@Param("id") Long id);

    /**
     * 通过部门ID 获取该部门所拥有的角色所对应的菜单(权限)
     *
     * @param id 部门ID
     * @return 权限url集合
     */
//    @Query("select distinct r.p from Group g join g.roles r join r.resources m where g.id=:id ")
    @Query("select distinct new Resource(s.id, s.type, s.childId,s.name, s.url, s.leaf,s.icon,s.parent.id) from Group g join g.roles r join r.resources s where g.id=:id ")
    public Set<Resource> findResourceByGroupsId(@Param("id") Long id);

    /**
     * 更新密码
     *
     * @param id
     * @param newPwd
     * @return
     */
    @Transactional
    @Modifying
    @Query("update User u set u.password = ?2,u.salt=?3 where u.id = ?1")
    public int updatePassWord(Long id, String newPwd, String salt);

    //    @Query("select u from User u  join u.groups g where g.id = ?1 ")
    public List<User> getUsersByGroups(Long groupId);

    //    @Query("select u from User u  join u.groups g where g.id = ?1 and u.username like %?2% ")
    public Page<User> getPageUsersByGroupsAndUsername(Pageable pageable, Long groupId, String username);

    public User findUserByCode(String code);

    public User findByUsername(String username);

    public List<User> findByUsernameLike(String username);

    /**
     * 需要加上事物
     *
     * @param username
     * @param state
     * @return
     */
    @Transactional
    @Modifying
    @Query("update User u set u.state = ?2 where u.username = ?1")
    int updateState(String username, User.State state);

    /**
     * 需要加上事物
     *
     * @param userId
     * @param state
     * @return
     */
    @Transactional
    @Modifying
    @Query("update User u set u.state = ?2 where u.id = ?1")
    int updateState(Long userId, User.State state);
}
