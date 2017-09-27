package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.Order;
import com.topcom.cms.common.page.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lism on 2017/5/24.
 */
public class BoolQueryRequest implements Serializable {
    /**
     * 发布时间
     */
    private DateParam date = new DateParam();
    /**
     * 保存时间
     */
    private DateParam dateCreated = new DateParam();
    private KeywordRequest keywordRequest = new KeywordRequest();
    private String[] type;
    private String filed;
    private String groupName;
    private List<KV> searchKv;

    public List<KV> getSearchKv() {
        return searchKv;
    }

    public void setSearchKv(List<KV> searchKv) {
        this.searchKv = searchKv;
    }

    private PageRequest page;

    public BoolQueryRequest() {
    }

    public BoolQueryRequest(KeywordRequest keywordRequest) {
        this.keywordRequest = keywordRequest;
    }

    public BoolQueryRequest(DateParam date, KeywordRequest keywordRequest) {
        this.date = date;
        this.keywordRequest = keywordRequest;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String... type) {
        this.type = type;
    }

    public DateParam getDate() {
        return date;
    }

    public void setDate(DateParam date) {
        this.date = date;
    }

    public KeywordRequest getKeywordRequest() {
        return keywordRequest;
    }

    public void setKeywordRequest(KeywordRequest keywordRequest) {
        this.keywordRequest = keywordRequest;
    }

    public PageRequest getPage() {
        return page;
    }

    public void setPage(PageRequest page) {
        this.page = page;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public DateParam getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateParam dateCreated) {
        this.dateCreated = dateCreated;
    }


    private String typeString(){
        if (type!=null&&type.length>0){
            StringBuffer typeBuffer = new StringBuffer();
            for (int i=0;i<type.length;i++){
                typeBuffer.append(type[i]);
            }
            return typeBuffer.toString();
        }
        return null;
    }

    private String pageString(){
        if (page==null){
            return null;
        }
        List<Order> orders = page.getOrders();
        String orderString = null;
        if (orders!=null&&orders.size()>0){
            orderString = orders.get(0).getOrderBy()+orders.get(0).getDirection();
        }
        return "{"+"limit="+page.getLimit()+",page="+page.getPage()+",orders="+orderString +"}" ;
    }

    @Override
    public String toString() {
        String s = "BoolQueryRequest{" +
                "keyWord='" + keywordRequest + '\'' +
                ", data='" + (date==null? "null":date.getStartDate()+"--"+date.getEndDate()) + '\'' +
                ", dateCreated='" +(dateCreated==null? "null":dateCreated.getStartDate()+"--"+dateCreated.getEndDate()) + '\'' +
                ", type='" + typeString() + '\'' +
                ", filed='" + filed + '\'' +
                ", searchKv='" + searchKv + '\'' +
                ", page='"+pageString() +  '\'' +
                ",groupName="+this.getGroupName()+'}';

        return s;
    }
}
