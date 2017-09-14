//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.topcom.cms.dao;

import com.topcom.cms.base.dao.GenericTreeDao;
import com.topcom.cms.domain.Group;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupDao extends GenericTreeDao<Group, Long> {
    @Query("SELECT new com.topcom.cms.domain.Group(g.text,g.dateCreated,g.dateModified,g.deleted,g.id,g.description,g.name,g.childId,g.level) FROM User u join u.groups g where u.id =:id  ")
    List<Group> findGroupsByUserId(@Param("id") Long var1);

    @Query("SELECT new com.topcom.cms.domain.Group(g.text,g.dateCreated,g.dateModified,g.deleted,g.id,g.description,g.name,g.childId,g.level) FROM Group g where g.id =:id  ")
    Group findTreeGroupById(@Param("id") Long var1);

    @Query("SELECT new com.topcom.cms.domain.Group(g.text,g.dateCreated,g.dateModified,g.deleted,g.id,g.description,g.name,g.childId,g.level) FROM Group g where g.parent.id =:id  ")
    List<Group> findChildrenByParentId(@Param("id") Long var1);

    Group findByName(String var1);

    @Query("select m.parent.id from com.topcom.cms.domain.Group m where m.id=?1")
    Long getParentIdById(Long var1);

    @Query("select max(m.childId) from Group m where m.parent.id=?1")
    Long getMaxChildId(Long var1);

    @Modifying
    @Query("update Group m set m.childId = m.childId + 1 where m.parent.id = ?1 and m.childId >=?2")
    int childIdAddOne(Long var1, Long var2);

    @Modifying
    @Query("update Group m set m.parent.id=?2, m.childId = ?3 where m.id = ?1")
    int setChildId(Long var1, Long var2, Long var3);
}
