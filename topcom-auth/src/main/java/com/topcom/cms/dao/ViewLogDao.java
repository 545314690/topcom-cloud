package com.topcom.cms.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.domain.ViewLog;
import com.topcom.cms.vo.ViewLogDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ViewLogDao extends GenericDao<ViewLog, Long> {



    @Query("SELECT new com.topcom.cms.vo.ViewLogDto(count(v.id),v.resourceId,v.resourceName,v.url) FROM ViewLog v where v.resourceId in :ids group by  v.resourceId,v.resourceName,v.url")
    List<ViewLogDto> findByResourceIdInAndGroupByResourceNameAndResourceId(@Param("ids") List resourceId);
}
