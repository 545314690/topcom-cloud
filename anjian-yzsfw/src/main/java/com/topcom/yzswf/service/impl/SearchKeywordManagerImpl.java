package com.topcom.yzswf.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.yzswf.dao.SearchKeywordDao;
import com.topcom.yzswf.domain.SearchKeyword;
import com.topcom.yzswf.service.SearchKeywordManager;
import com.topcom.yzswf.util.AnsjUtil;
import com.topcom.yzswf.util.Pinyin4jUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by lism on 12:03.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
@Service("searchKeywordManager")
@CacheConfig(cacheNames = "searchKeyword")
public class SearchKeywordManagerImpl extends GenericManagerImpl<SearchKeyword, Long> implements SearchKeywordManager {

    SearchKeywordDao searchKeywordDao;

    @Autowired
    public void setSearchKeywordDao(SearchKeywordDao searchKeywordDao) {
        this.searchKeywordDao = searchKeywordDao;
        this.dao = searchKeywordDao;
    }

    @Cacheable
    @Override
    public SearchKeyword findByKeyword(String keyword) {
        return searchKeywordDao.findByKeyword(keyword);
    }

    @Override
    public Page<SearchKeyword> findByKeywordLike(Pageable pageable, String keyword) {
        return searchKeywordDao.findByKeywordLike(pageable, keyword);
    }

    @Override
    public int frequencyAdd(String keyword, int num, Date searchDate) {
        return searchKeywordDao.frequencyAdd(keyword, num, searchDate);
    }

    /**
     * 更新关键词频率，没有则创建关键词，频率为1
     * 不要用save方法，会被OperationLogService拦截到
     *
     * @param kw 关键词，多个用“,”隔开
     */
    @Override
    public void updateFrequency(String kw) {
        if (StringUtils.isNotBlank(kw)) {
            String[] keywords = kw.split(",|@");
            for (String keyword : keywords) {
                SearchKeyword searchKeyword = searchKeywordDao.findByKeyword(keyword);
                if (searchKeyword == null) {
                    String pinyin = Pinyin4jUtil.converterToSpell(keyword);
                    String shortPinyin = Pinyin4jUtil.converterToFirstSpell(keyword);
                    String keyword_fc = AnsjUtil.indexAnalysis(keyword);
                    searchKeyword = new SearchKeyword(keyword,keyword_fc, 1, pinyin, shortPinyin);
                    searchKeywordDao.save(searchKeyword);
                } else {
                    this.frequencyAdd(keyword, 1, new Date());
                }
            }

        }
    }


    @Override
    public int setFrequencyMax(String kw) {
        int maxFrequency = searchKeywordDao.findMaxFrequency();
        return searchKeywordDao.setFrequency(kw,maxFrequency, new Date());
    }

    @Override
    public int goTop(Long id, int top) {
        return searchKeywordDao.goTop(id, top ,new Date());
    }

    @Override
    public List<SearchKeyword> getRelated(String keyword, int limit) {
        //将关键词分词
        keyword = AnsjUtil.indexAnalysis(keyword);
        return searchKeywordDao.getRelated(keyword,limit);
    }
    @Override
    public  List<Object[]> getRelatedToArray(String keyword, int limit) {
        //将关键词分词
        keyword = AnsjUtil.indexAnalysis(keyword);
        return searchKeywordDao.getRelatedToArray(keyword,limit);
    }

    @Override
    public Page<SearchKeyword> findByMaxWordLength(Pageable pageable, int maxLength) {
        return searchKeywordDao.findByMaxWordLength(pageable, maxLength);
    }
}
