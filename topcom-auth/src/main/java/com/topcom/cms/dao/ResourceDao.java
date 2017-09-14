//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.dao;

import com.topcom.cms.base.dao.GenericTreeDao;
import com.topcom.cms.domain.Resource;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResourceDao extends GenericTreeDao<Resource, Long> {
    @Query("from Resource m where m.leaf = true")
    List<Resource> getLeafs();

    @Query("SELECT new Resource(m.text,m.dateCreated,m.dateModified,m.deleted,m.id,m.description,m.name,m.url,m.icon,m.childId) FROM Role r join r.resources m where r.id = :id  order by m.parent.id asc, m.childId asc")
    List<Resource> findResourcesByRoleId(@Param("id") Long var1);

    @Query("SELECT new Resource(m.text,m.dateCreated,m.dateModified,m.deleted,m.id,m.description,m.name,m.url,m.icon,m.childId) FROM Role r join r.resources m where r.id in (:ids)  order by m.parent.id asc, m.childId asc")
    List<Resource> findResourcesByRoleIds(@Param("ids") Long[] var1);

    @Query("SELECT new Resource(m.text,m.dateCreated,m.dateModified,m.deleted,m.id,m.description,m.name,m.url,m.icon,m.childId) FROM Resource m where m.id =:id  order by m.parent.id asc, m.childId asc")
    Resource findResourcesWithoutById(@Param("id") Long var1);

    @Query("SELECT new Resource(m.text,m.dateCreated,m.dateModified,m.deleted,m.id,m.description,m.name,m.url,m.icon,m.childId) FROM Resource m where m.name like %?1%  order by m.parent.id asc, m.childId asc")
    Page<Resource> fuzzyQueryByName(Pageable var1, String var2);

    @Query("select m.parent.id from Resource m where m.id=?1")
    Long getParentIdById(Long var1);

    @Query("select max(m.childId) from Resource m where m.parent.id=?1")
    Long getMaxChildId(Long var1);

    @Modifying
    @Query("update Resource m set m.childId = m.childId + 1 where m.parent.id = ?1 and m.childId >=?2")
    int childIdAddOne(Long var1, Long var2);

    @Modifying
    @Query("update Resource m set m.parent.id=?2, m.childId = ?3 where m.id = ?1")
    int setChildId(Long var1, Long var2, Long var3);

    Resource findByName(String var1);
}
