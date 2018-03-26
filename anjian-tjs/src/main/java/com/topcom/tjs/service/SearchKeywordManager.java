package com.topcom.tjs.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.tjs.domain.SearchKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * 搜索关键字访问接口
 */
public interface SearchKeywordManager extends GenericManager<SearchKeyword, Long> {

    public SearchKeyword findByKeyword(String keyword);

    public Page<SearchKeyword> findByKeywordLike(Pageable pageable, String keyword);

    public int frequencyAdd(String keyword, int num, Date dateModified);

    public void updateFrequency(String kw);

    public int setFrequencyMax(String kw);

    int goTop(Long id, int top);

    List<SearchKeyword> getRelated(String keyword, int limit);

    List<Object[]> getRelatedToArray(String keyword, int limit);

    Page<SearchKeyword> findByMaxWordLength(Pageable pageable, int maxLength);
}