package com.topcom.tjs.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.tjs.domain.SearchKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by lism on 12:08.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
public interface SearchKeywordDao extends GenericDao<SearchKeyword,Long>{


    SearchKeyword findByKeyword(String keyword);

    @Query("from SearchKeyword kw where kw.keyword like ?1% or kw.pinyin like ?1% or kw.shortPinyin like ?1%")
    Page<SearchKeyword> findByKeywordLike(Pageable pageable, String keyword);

    @Modifying
    @Query("update SearchKeyword kw set kw.frequency = (kw.frequency + ?2),kw.searchDate = ?3 where kw.keyword = ?1")
    int frequencyAdd(String keyword, int num, Date searchDate);

    @Query("select max(kw.frequency) from SearchKeyword kw")
    int findMaxFrequency();

    @Modifying
    @Query("update SearchKeyword kw set kw.frequency = ?2,kw.dateModified = ?3 where kw.keyword = ?1")
    int setFrequency(String kw, int maxFrequency, Date date);

    @Modifying
    @Query("update SearchKeyword kw set kw.top = ?2,kw.dateModified = ?3 where kw.id = ?1")
    int goTop(Long id, int top, Date date);

    /**
     * 必须把SearchKeyword的每个字段都写上，跟NamedNativeQuery效果一致（写在实体里的）
     * @param keyword
     * @param limit
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT DATECREATED,DATEMODIFED,ENTITY_NAME,deleted,id,keyword,keyword_fc,searchDate,frequency,top,pinyin,shortPinyin,synonym,MATCH (keyword_fc) AGAINST (?1) as keyword_score FROM t_searchkeyword WHERE MATCH (keyword_fc) AGAINST (?1 IN BOOLEAN MODE) order by keyword_score DESC limit 0,?2")
    List<SearchKeyword> getRelated(String keyword, int limit);


    /**
     * 可自定义返回结果，但只能返回Object数组，没有字段名
     * @param keyword
     * @param limit
     * @return
     */
    @Query(nativeQuery = true,
            value = "SELECT keyword,MATCH (keyword_fc) AGAINST (?1) as keyword_score FROM t_searchkeyword WHERE MATCH (keyword_fc) AGAINST (?1 IN BOOLEAN MODE) order by keyword_score DESC limit 0,?2")
    List<Object[]> getRelatedToArray(String keyword, int limit);

    /**
     *
     * @param pageable
     * @param maxLength
     * @return
     */
    @Query("from SearchKeyword kw where length(kw.keyword) <= ?1")
    Page<SearchKeyword> findByMaxWordLength(Pageable pageable, int maxLength);
}
