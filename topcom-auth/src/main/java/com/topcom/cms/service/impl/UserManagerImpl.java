package com.topcom.cms.service.impl;

import java.util.*;

import com.topcom.cms.common.model.Gender;
import com.topcom.cms.dao.RoleDao;
import com.topcom.cms.domain.*;
import com.topcom.cms.vo.UserSearchVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.dao.UserDao;
import com.topcom.cms.service.UserManager;

import javax.persistence.criteria.*;

/**
 * 用户信息访问接口
 *
 * @author lism
 */
@Service(value = "userManager")
@Transactional
public class UserManagerImpl extends GenericManagerImpl<User, Long> implements
        UserManager {

    UserDao userDao;

//    @Autowired
//    RoleDao roleDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        this.dao = this.userDao;
    }

    @Autowired
    private UserManager userManager;

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Page<User> fuzzyQueryByName(Pageable pageable, String username) {
        return userDao.fuzzyQueryByName(pageable, username);
    }


    @Override
    public Page<User> fuzzyQueryByFullName(Pageable pageable, String fullName) {
        return userDao.fuzzyQueryByFullName(pageable, fullName);
    }

    @Override
    public Page<User> fuzzyQueryByFAC(Pageable pageable, String fac) {
        return userDao.fuzzyQueryByFAC(pageable, fac);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userDao.findByUsernameAndPassword(username, password);
    }

    @Override
    public List<String> findAuthsById(Long id) {
        // findAuthsByIdWithGroup 获取用户所属组所对应的权限(菜单)
        List<String> list = userDao.findAuthsByIdWithGroup(id);
        // findAuthsByIdWithGroup 获取用户角色所对应的权限(菜单)
        List<String> list2 = userDao.findAuthByIdNoGroup(id);
        // 获取用户权限
        list.addAll(list2);
        return list;
    }

    @Override
    public Set<Resource> findResourceById(Long id) {
        // 获取用户所属组所对应的权限(菜单)
        return userDao.findResourceById(id);
    }

    @Override
    public Set<Resource> findResourceByGroupId(Long id) {
        // 获取用户部门所属组所对应的权限(菜单)
        return userDao.findResourceByGroupsId(id);
    }

    @Override
    // 将缓存保存进andCache，并使用参数中的id加上一个字符串(这里使用方法名称)作为缓存的key
    @Cacheable(value = "userCache", key = "#page + 'findAll'")
    public Page<User> findAll(Pageable page) {
        return super.findAll(page);
    }

    @Override
    @CacheEvict(value = "userCache", allEntries = true)
    // 清除掉全部缓存
    public void delete(Long id) {
        super.delete(id);

    }

    @Override
    @CacheEvict(value = "userCache", allEntries = true)
    public User save(User model) {
        return super.save(model);
    }

    @Override
    @Cacheable(value = "userCache", key = "#id + 'findById'")
    // 将缓存保存进userCache，并使用参数中的id加上一个字符串(这里使用方法名称)作为缓存的key
    public User findUserById(Long id) {
        return super.findById(id);
    }

    /**
     * 根据用户id更新密码
     *
     * @param id     用户id
     * @param newPwd 新密码
     * @param salt
     * @return
     */
    @Override
    public int updatePassword(Long id, String newPwd, String salt) {
        return userDao.updatePassWord(id, newPwd, salt);
    }

    @Override
    public List<User> getUsersByGroupId(Long groupId) {
        return userDao.getUsersByGroups(groupId);
    }

    @Override
    public Page<User> getPageUsersByGroupIdAndName(Pageable pageable, Long groupId, String name) {
        return userDao.getPageUsersByGroupsAndUsername(pageable, groupId, name);
    }

    @Override
    public User findUserByCode(String user_code) {
        return userDao.findUserByCode(user_code);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public Set<String> findRoleNames(User user) {
        Set<String> roleNames = user.getRoleNames();
        return roleNames;
    }

    @Override
    public Set<String> findPermissions(User user) {
        return user.getPermissions();
    }

    @Override
    public List<User> findByUsernameLike(String username) {
        return userDao.findByUsernameLike(username);
    }

    @Override
    public int updateState(String username, User.State state) {
        return userDao.updateState(username, state);
    }

    @Override
    public int updateState(Long userId, User.State state) {
        return userDao.updateState(userId, state);
    }

    @Override
    public User register(User model) {
        return userDao.save(model);
    }

    @Override
    public Page<User> findByCriteriaQuery(Pageable pageable, UserSearchVO userSearchVO) {
        return this.findAll(getWhereClause(userSearchVO),pageable);
    }

    /**
     * 动态生成where语句
     *
     * @param userSearchVO
     * @return
     */
    private Specification<User> getWhereClause(final UserSearchVO userSearchVO) {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicate = new ArrayList<>();
                if (userSearchVO.getUsername() != null) {
                    predicate.add(cb.like(root.get("username").as(String.class), "%" + userSearchVO.getUsername() + "%"));
                }
                if (userSearchVO.getFullName() != null) {
                    predicate.add(cb.like(root.get("fullName").as(String.class), "%" + userSearchVO.getFullName() + "%"));
                }
                if (userSearchVO.getType() != null) {
                    //两张表关联查询
                    Join<User, UserInfo> userInfoJoin = root.join(root.getModel().getSingularAttribute("userInfo", UserInfo.class), JoinType.LEFT);
                    predicate.add(cb.equal(userInfoJoin.get("type").as(Integer.class), userSearchVO.getType()));
                }
                if (userSearchVO.getState() != null) {
                    predicate.add(cb.equal(root.get("state").as(User.State.class), userSearchVO.getState()));
                }
                if (userSearchVO.getGender() != null) {
                    predicate.add(cb.equal(root.get("gender").as(Gender.class), userSearchVO.getGender()));
                }
                Set<Group> groups = userSearchVO.getGroups();
                if (groups != null) {
                    Set<Long> groupIds = new HashSet<>();
                    for (Group group: groups) {
                        groupIds.add(group.getId());
                    }
                    //两张表关联查询
                    Join<User, Group> groupJoin = root.join(root.getModel().getSet("groups", Group.class), JoinType.LEFT);
                    CriteriaBuilder.In inGroup = cb.in(groupJoin.get("id").as(Long.class));
                    inGroup.value(groupIds);
                    predicate.add(inGroup);
                }
                Set<Role> roles = userSearchVO.getRoles();
                if (roles != null) {
                    Set<Long> roleIds = new HashSet<>();
                    for (Role role: roles) {
                        roleIds.add(role.getId());
                    }
                    //两张表关联查询
                    Join<User, Role> roleJoin = root.join(root.getModel().getSet("roles", Role.class), JoinType.LEFT);
                    CriteriaBuilder.In inRole = cb.in(roleJoin.get("id").as(Long.class));
                    inRole.value(roleIds);
                    predicate.add(inRole);
                }
                Predicate[] pre = new Predicate[predicate.size()];
                return query.where(predicate.toArray(pre)).distinct(true).getRestriction();
            }
        };
    }
}
