package com.topcom.yzswf.vo;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.PageRequest;
import com.topcom.yzswf.util.QueryWord;

import java.util.List;

/**
 * Created by lsm on 2018/3/13 0013.
 */
public class FileQueryVO {
    private QueryWord queryWord;
    private String searchType;//全文、文件名
    private PageRequest pageRequest;
    private DateParam dateParam;
    private String type;//事故案例或者执法文书等
    private List<Object> fileTypes;//文件类型
    private String source;//来源
    private String accidentType;//事故類型
//    private String sortBy;//排序
//    private Sort.Direction direction = Sort.Direction.DESC;//排序

    public DateParam getDateParam() {
        return dateParam;
    }

    public void setDateParam(DateParam dateParam) {
        this.dateParam = dateParam;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getFileTypes() {
        return fileTypes;
    }

    public void setFileTypes(List<Object> fileTypes) {
        this.fileTypes = fileTypes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

//    public String getSortBy() {
//        return sortBy;
//    }

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public QueryWord getQueryWord() {
        return queryWord;
    }

    public void setQueryWord(QueryWord queryWord) {
        this.queryWord = queryWord;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }
    //    public void setSortBy(String sortBy) {
//        this.sortBy = sortBy;
//    }
//
//    public Sort.Direction getDirection() {
//        return direction;
//    }
//
//    public void setDirection(Sort.Direction direction) {
//        this.direction = direction;
//    }
}
