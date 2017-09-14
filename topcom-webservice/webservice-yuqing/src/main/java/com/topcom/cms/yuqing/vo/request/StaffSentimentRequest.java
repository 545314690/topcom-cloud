package com.topcom.cms.yuqing.vo.request;

import com.topcom.cms.common.page.DateParam;
import com.topcom.cms.common.page.Order;
import com.topcom.cms.common.page.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by topcom on 2017/9/4 0004.
 */
public class StaffSentimentRequest implements Serializable {
    private Long organizationalStructureId;
    private DateParam date;
    private PageRequest page;
    private String[] articleType;
    private String filed;

    private List<Order> sortBy;

    public List<Order> getSortBy() {
        return sortBy;
    }

    public void setSortBy(List<Order> sortBy) {
        this.sortBy = sortBy;
    }

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }

    public Long getOrganizationalStructureId() {
        return organizationalStructureId;
    }

    public void setOrganizationalStructureId(Long organizationalStructureId) {
        this.organizationalStructureId = organizationalStructureId;
    }

    public DateParam getDate() {
        return date;
    }

    public void setDate(DateParam date) {
        this.date = date;
    }

    public PageRequest getPage() {
        return page;
    }

    public void setPage(PageRequest page) {
        this.page = page;
    }

    public String[] getArticleType() {
        return articleType;
    }

    public void setArticleType(String[] articleType) {
        this.articleType = articleType;
    }

    private String typeString(){
        if (articleType!=null&&articleType.length>0){
            StringBuffer typeBuffer = new StringBuffer();
            for (int i=0;i<articleType.length;i++){
                typeBuffer.append(articleType[i]);
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

        String s = "StaffSentimentRequest{" +
                "organizationalStructureId='" + organizationalStructureId + '\'' +
                ", data='" + (date==null? "null":date.getStartDate()+"--"+date.getEndDate()) + '\'' +
                ", type='" + typeString() + '\'' +
                ", filed='" + filed + '\'' +
                ", page='"+pageString() +  '\'' +'}';

        return s;
    }
}
