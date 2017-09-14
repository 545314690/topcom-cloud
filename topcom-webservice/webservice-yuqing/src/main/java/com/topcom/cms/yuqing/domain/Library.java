package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import com.topcom.cms.base.model.BaseTreeEntityModel;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by topcom on 2017/7/4 0004.
 */
@Entity
@Table(name = "t_library")
@EntityListeners({AuditingEntityListener.class})
@NamedQueries({    @NamedQuery(
        name = "Library.getRoot",
        query = "SELECT lb  FROM Library lb  where lb.parent is null"
)})
public class Library extends BaseTreeEntityModel<Library>
{


    public Library() {}
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "t_library_word",
//            joinColumns = @JoinColumn(name = "libraryId"),
//            inverseJoinColumns = @JoinColumn(name = "wordId")
//    )
//    private Set<Word> words;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 用户ID
     */
    @CreatedBy
    @LastModifiedBy
    private Long userId;


    @Transient
    private Long parentId;

    @Column(name = "childId")
    Long childId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

//    public Set<Word> getWords() {
//        return words;
//    }
//
//    public void setWords(Set<Word> words) {
//        this.words = words;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
