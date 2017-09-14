package com.topcom.cms.yuqing.service;

import com.topcom.cms.base.service.GenericManager;
import com.topcom.cms.base.service.GenericTreeManager;
import com.topcom.cms.yuqing.domain.Library;
import com.topcom.cms.yuqing.domain.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LibraryManager extends GenericTreeManager<Library, Long> {

    Page<Library> findByUserId(Pageable pageable, Long userId);

    Object findByName(Pageable pageable, String name, Long userId);

    Library findByIdAndUserId(Long id, Long userId);

    List<Library> getRootByUserId(Long userId);

}
