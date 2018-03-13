package com.topcom.cms.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.domain.SearchWord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SearchWordManager extends GenericManager<SearchWord, Long> {
    List<SearchWord> findByWordAndType(String word, Integer type);

    Page<SearchWord> findByType(Pageable page, Integer type);

    SearchWord addClickCount(String word, Integer type);
}
