package com.topcom.cms.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.domain.SearchWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SearchWordDao extends GenericDao<SearchWord, Long> {

    List<SearchWord> findByWordAndType(String word, Integer type);

    Page<SearchWord> findByType(Pageable page, Integer type);

    List<SearchWord> findByWordAndTypeAndGroupId(String word, Integer type, String groupId);

    Page<SearchWord> findByTypeAndGroupIdIn(Pageable page, Integer type, List<String> groupIdList);

    Page<SearchWord> findByWordLikeAndGroupIdIn(Pageable page, String word, List<String> groupIdList);
}
