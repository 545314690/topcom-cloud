package com.topcom.yzswf.util;

import java.util.List;

/**
 * Created by lsm on 2018/3/13 0013.
 */
public class Page<T> {
    private List<T> content;
    private long totalElements;

    public Page() {
    }

    public Page(List<T> content, long totalElements) {
        this.content = content;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
