package com.topcom.cms.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.Group;
import com.topcom.cms.domain.SearchWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;


public interface SearchWordManager extends GenericManager<SearchWord, Long> {
    List<SearchWord> findByWordAndType(String word, Integer type);

    Page<SearchWord> findByType(Pageable page, Integer type);

    SearchWord addClickCount(Set<Group> groups,String word, Integer type);

    Page<SearchWord> findByTypeAndGroupIdIn(Pageable page, Integer type, List<String> groupIdList);

    Page<SearchWord> findByWordAndGroupIdIn(Pageable page, String word, List<String> groupIdList);
}
