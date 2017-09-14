package com.topcom.cms.yuqing.dao;

import com.topcom.cms.base.dao.GenericDao;
import com.topcom.cms.yuqing.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface WordDao extends GenericDao<Word, Long> {

    Page<Word> findByStatus(Pageable pageable, Word.Status status);

    Page<Word> findByLibraryIdAndLibraryUserIdAndStatus(Pageable pageable, Long id, Long userId, Word.Status status);

    Page<Word> findByLibraryNameAndLibraryUserId(Pageable pageable, String name, Long id);

    Page<Word> findByWordAndLibraryUserId(Pageable pageable, String name, Long userId);

    Page<Word> findByLibraryIdAndLibraryUserId(Pageable pageable, Long libraryId, Long id);

    Page<Word> findByFirstPinyinAndLibraryIdAndLibraryUserId(Pageable pageable, char firstPinyin, Long libraryId, Long uid);

    Page<Word> findByLibraryUserId(Pageable pageable, Long id);

    Page<Word> findByLibraryUserIdAndStatus(Pageable pageable, Long id, Word.Status status);
}
