package com.topcom.cms.dao;

import java.util.List;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 角色信息访问接口
 *
 * @author lism
 */
public interface RoleDao extends GenericDao<Role, Long> {

    /**
     * 通过用户ID 获取该用户拥有的角色集合
     *
     * @param id 用户ID
     * @return 角色集合
     */
    @Query("SELECT new Role(r.dateCreated,r.dateModified,r.deleted,r.id,r.name, r.description,r.available) FROM User u join u.roles r where u.id =:id  ")
    List<Role> findRolesByUserId(@Param("id") Long id);

    /**
     * 根据用户组ID集合查找用户组的角色信息集合
     *
     * @param ids
     * @return 角色集合
     */
    @Query("SELECT new Role(r.dateCreated,r.dateModified,r.deleted,r.id,r.name, r.description,r.available) FROM Group g join g.roles r where g.id in (:ids)  ")
    List<Role> findRolesByGroupIds(@Param("ids") Long[] ids);

    /**
     * 根据用户ID查找用户角色信息集合
     *
     * @param id
     * @return 角色集合
     */
    @Query("SELECT new Role(r.dateCreated,r.dateModified,r.deleted,r.id,r.name, r.description,r.available) FROM Group g join g.roles r where g.id = :id and r.available=1 ")
    List<Role> findRolesByGroupId(@Param("id") Long id);

    /**
     * 获取角色列表信息，重写父类 findAll 方法 去掉关联查询
     *
     * @param page 分页信息
     * @return 分页列表信息
     */
    @Query("SELECT new Role(r.dateCreated,r.dateModified,r.deleted,r.id,r.name, r.description,r.available) FROM Role r ")
    Page<Role> findAllRole(Pageable page);

    /**
     * 获取所有可用(未被禁用)的角色列表信息
     *
     * @return 角色集合
     */
    @Query("SELECT new Role(r.dateCreated,r.dateModified,r.deleted,r.id,r.name, r.description,r.available) FROM Role r where r.available=1")
    List<Role> findAllUsableRoles();

    /**
     * 根据name模糊查询角色列表
     *
     * @return 角色集合
     */
    @Query("SELECT new Role(r.dateCreated,r.dateModified,r.deleted,r.id,r.name, r.description,r.available) FROM Role r where r.name like %?1% ")
    Page<Role> findByNameLike(Pageable pageable, String name);

    Role findByName(String name);

}
