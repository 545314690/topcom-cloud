package com.topcom.cms.yuqing.service.impl;

import com.topcom.cms.base.service.impl.GenericManagerImpl;
import com.topcom.cms.yuqing.dao.WordDao;
import com.topcom.cms.yuqing.domain.Word;
import com.topcom.cms.yuqing.service.WordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "wordManager")
@Transactional
public class WordManagerImpl extends GenericManagerImpl<Word, Long>
        implements WordManager {
    WordDao wordDao;

    @Autowired
    public void setWordDao(WordDao wordDao) {
        this.wordDao = wordDao;
        this.dao = this.wordDao;
    }


    @Override
    public Page<Word> findByStatus(Pageable pageable, Word.Status status) {
        return wordDao.findByStatus(pageable, status);
    }

    @Override
    public Page<Word> searchWordByStatus(Pageable pageable,Word.Status status, Long libraryId,Long userId) {
        return wordDao.findByLibraryIdAndLibraryUserIdAndStatus(pageable,libraryId,userId,status);
    }

    @Override
    public Page<Word> findByLibraryName(Pageable pageable, String name, Long userId) {
        return this.wordDao.findByLibraryNameAndLibraryUserId(pageable,name,userId);
    }

    @Override
    public Page<Word> findByWordName(Pageable pageable, String name, Long userId) {
        return this.wordDao.findByWordAndLibraryUserId(pageable,name,userId);
    }

    @Override
    public Page<Word> findByLibraryId(Pageable pageable, Long libraryId, Long id) {
        return this.wordDao.findByLibraryIdAndLibraryUserId(pageable,libraryId,id);
    }

    @Override
    public Page<Word> searchWordByFirstPinyin(Pageable pageable, char firstPinyin, Long libraryId, Long Uid) {
        return wordDao.findByFirstPinyinAndLibraryIdAndLibraryUserId(pageable,firstPinyin,libraryId,Uid);
    }

    @Override
    public Page<Word> findByUser(Pageable pageable, Long id) {
        return wordDao.findByLibraryUserId(pageable,id);
    }

    @Override
    public Page<Word> searchWordByStatusAndUser(Pageable pageable, Word.Status status, Long id) {
        return wordDao.findByLibraryUserIdAndStatus(pageable,id,status);
    }


}
