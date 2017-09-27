package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.PageRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by topcom on 2017/7/7 0007.
 */

/**
 *
 * @param <T>
 */
//@JsonIgnoreProperties(value={"content","sort"})
public class AggPageImpl<T> implements Page<T> ,Serializable{
    private List<T> content;
    private int totalPages;
    private long totalElements;
    private Boolean last;
    private int number;
    private int size;
    private Sort sort;
    private int numberOfElements;
    private Boolean first;

    public AggPageImpl(List<T> content, int totalPages, long totalElements, Pageable pageable) {
        this.sort = pageable.getSort();
        this.number = pageable.getPageNumber();
        this.size = pageable.getPageSize();
        this.numberOfElements = pageable.getPageSize();
        this.totalElements = totalElements;
        this.content = content;
        this.totalPages = totalPages;
        this.first=number==0 ? true : false;
        this.last = number==totalPages ? true : false;
    }

    public AggPageImpl() {
    }

    public List<T> getContent() {
        return content;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return null;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public boolean isFirst() {
        return first;
    }

    @Override
    public boolean isLast() {
        return last;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }


    public AggPageImpl getPageFromList(List list, PageRequest pageRequest){
        if (list==null||list.size()==0){
            return new AggPageImpl();
        }
        int limit = pageRequest.getLimit()==null?10:pageRequest.getLimit();
        int page = pageRequest.getPage()==null?1:pageRequest.getPage();
        int from = (page-1)*limit;
        List content = new ArrayList<>();
        content.addAll(list.subList(from, (page * limit)>list.size()?list.size():(page * limit)));
        int totalPages = (list.size()%limit)==0?list.size()/limit:(list.size()/limit+1);
        AggPageImpl aggPage = new AggPageImpl(content,totalPages,list.size(),pageRequest.pageable());
        return aggPage;
    }
}
