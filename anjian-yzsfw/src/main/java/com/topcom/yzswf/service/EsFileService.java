package com.topcom.yzswf.service;

import com.topcom.yzswf.vo.EsIndexFile;
import com.topcom.yzswf.vo.FileQueryVO;

import java.util.Map;

/**
 * Created by lsm on 2018/3/13 0013.
 */
public interface EsFileService {
    public void index2Es(EsIndexFile esIndexFile) throws Exception;
    public com.topcom.yzswf.util.Page  search(FileQueryVO fileQueryVO) throws Exception;
    public Map<String, Object> findById(String id) throws Exception;

    Map downloadTimesPlusOne(String id) throws Exception;
}
