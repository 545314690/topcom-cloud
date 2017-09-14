package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericTreeDao;
import com.topcom.cms.yuqing.domain.Library;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibraryDao extends GenericTreeDao<Library, Long>
{

    Page<Library> findByUserId(Pageable pageable, Long userId);

    Page<Library> findByNameAndUserId(Pageable pageable, String name, Long userId);

    @Query("FROM Library lb  where lb.parent is null and lb.userId=?1")
    List<Library> getRootByUserId(Long userId);

    @Query("FROM Library lb  where lb.id =?1 and lb.userId=?2")
    Library findByIdAndUserId(Long id, Long userId);
//    @Query("SELECT Word FROM Library lb JOIN Library.words word where lb.id =?1 AND word.status =?2")
//    Object findWordByLibraryIDAndStatus(Word.Status status, Long id);
}
