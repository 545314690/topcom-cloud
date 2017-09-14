package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.yuqing.domain.Contact;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WordManager extends GenericManager<Word, Long> {

    Page<Word> findByStatus(Pageable pageable, Word.Status status);

    Page<Word> searchWordByStatus(Pageable pageable, Word.Status status, Long libraryId, Long userId);

    Page<Word> findByLibraryName(Pageable pageable, String name, Long id);

    Page<Word> findByWordName(Pageable pageable, String name, Long id);

    Page<Word> findByLibraryId(Pageable pageable, Long libraryId, Long id);

    Page<Word> searchWordByFirstPinyin(Pageable pageable, char firstPinyin, Long libraryId, Long id);

    Page<Word> findByUser(Pageable pageable, Long id);

    Page<Word> searchWordByStatusAndUser(Pageable pageable, Word.Status status, Long id);
}
