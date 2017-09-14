package com.topcom.cms.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.Resource;
import com.topcom.cms.domain.User;

/**
 * 用户信息访问接口
 *
 * @author maodaqiang
 */
public interface UserManager extends GenericManager<User, Long> {
    /**
     * 模糊查询用户名为参数username的用户列表信息
     *
     * @param pageable 分页配置
     * @param username
     * @return 分页列表
     */
    Page<User> fuzzyQueryByName(Pageable pageable, String username);


    /**
     * 模糊查询用户全称为参数fullName的用户列表信息
     *
     * @param pageable
     * @param fullName
     * @return
     */
    Page<User> fuzzyQueryByFullName(Pageable pageable, String fullName);

    /**
     * 晨光要求根据 用户姓名 或者人员编码 查询用户信息
     *
     * @param pageable
     * @param fac
     * @return
     */
    Page<User> fuzzyQueryByFAC(Pageable pageable, String fac);

    /**
     * 通过用户名和密码查询用户信息
     *
     * @param username
     * @param password
     * @return 用户对象
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 通过用户组ID 获取该用户所在组所对应的菜单(权限)
     *
     * @param id 用户ID
     * @return 权限集合
     */
    List<String> findAuthsById(Long id);

    /**
     * 通过id 获取用户对象
     *
     * @param id
     * @return 用户对象
     */
    User findUserById(Long id);

    /**
     * 根据用户id获取登录用户的菜单
     *
     * @param id 用户id
     * @return 权限url集合
     */
    Set<Resource> findResourceById(Long id);

    /**
     * 根据部门id获取登录用户的菜单
     *
     * @param id 用户id
     * @return 权限url集合
     */
    Set<Resource> findResourceByGroupId(Long id);

    /**
     * 根据用户id更新密码
     *
     * @param id     用户id
     * @param newPwd 新密码
     * @param salt
     * @return
     */
    int updatePassword(Long id, String newPwd, String salt);

    /**
     * 根据组id获取该组下面的用户
     *
     * @param groupId
     * @return
     */
    List<User> getUsersByGroupId(Long groupId);

    Page<User> getPageUsersByGroupIdAndName(Pageable pageable, Long groupId, String name);

    User findById(Long id);

    User findUserByCode(String user_code);

    User findByUsername(String username);

    Set<String> findRoleNames(User user);

    Set<String> findPermissions(User user);

    List<User> findByUsernameLike(String username);

    int updateState(String username, User.State state);

    int updateState(Long userId, User.State state);
}
