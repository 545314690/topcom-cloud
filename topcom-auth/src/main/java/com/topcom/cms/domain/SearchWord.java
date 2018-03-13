package com.topcom.cms.domain;

import com.topcom.cms.base.model.BaseEntityModel;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * 查询词语实体类
 * 热词统计
 * @author maxl
 * @date 2018/3/12 0012
 */
@Entity
@Table(
        name = "t_searchWord"
)
public class SearchWord  extends BaseEntityModel {

    private String word;

    private String groupId;
    /**
     * 搜索类型
     * 功能搜索 1    资源搜索 2
     */
    private int type;

    private long wordCount;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getWordCount() {
        return wordCount;
    }

    public void setWordCount(long wordCount) {
        this.wordCount = wordCount;
    }

    public static String  groupIdBySet(Set<Group> groups){
        String groupId = "null";
        if (groups!=null&&groups.size()>0){
            groupId="";
            for (Group g:groups){
                groupId = groupId+","+g.getId();
            }
            groupId = groupId.substring(1,groupId.length());
        }
        return groupId;
    }

    public static void main(String[] args) {
        String s = "123456";
        System.out.println(s.substring(1,s.length()-1));
    }
}
