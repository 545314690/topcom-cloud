package com.topcom.cms.yuqing.domain;

import com.topcom.cms.base.model.BaseEntityModel;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 人员（领导或者员工）
 * Created by topcom on 2017/8/23 0023.
 */
@Entity
@Table(name = "t_staff")
@EntityListeners({AuditingEntityListener.class})
public class Staff extends BaseEntityModel {


    private String name;
    private String age;
    /**
     * 1 男
     * 0 女
     * 3 未知
     */
    private int gender;
    /**
     * 职位（局长、主任什么的）
     */
    private String position;

    private String mustWord;
    private String shouldWord;
    private String mustNotWord;
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_organizationalStructure_staff",
            joinColumns = @JoinColumn(name = "staffId"),
            inverseJoinColumns = @JoinColumn(name = "organizationalStructureId")
    )
    private Set<OrganizationalStructure> organizationalStructures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }


    public Set<OrganizationalStructure> getOrganizationalStructures() {
        return organizationalStructures;
    }

    public void setOrganizationalStructures(Set<OrganizationalStructure> organizationalStructures) {
        this.organizationalStructures = organizationalStructures;
    }

    public String getMustWord() {
        return mustWord;
    }

    public void setMustWord(String mustWord) {
        this.mustWord = mustWord;
    }

    public String getShouldWord() {
        return shouldWord;
    }

    public void setShouldWord(String shouldWord) {
        this.shouldWord = shouldWord;
    }

    public String getMustNotWord() {
        return mustNotWord;
    }

    public void setMustNotWord(String mustNotWord) {
        this.mustNotWord = mustNotWord;
    }

    public void addOrganizationalStructure(OrganizationalStructure os) {
        if (!this.organizationalStructures.contains(os)) {
            Set<OrganizationalStructure> osSet = new HashSet<>();
            osSet.addAll(this.organizationalStructures);
            osSet.add(os);
            this.setOrganizationalStructures(osSet);
        }
    }

    public void removeOrganizationalStructure(OrganizationalStructure os) {
        if (this.organizationalStructures.contains(os)) {
            Set<OrganizationalStructure> osSet = new HashSet<>();
            osSet.addAll(this.organizationalStructures);
            osSet.remove(os);
            this.setOrganizationalStructures(osSet);
        }
    }

    public String getExpression() {
        if (!StringUtils.isEmpty(this.expression)) {
            return expression;
        }
        if (StringUtils.isEmpty(this.mustWord)) {
            if (!StringUtils.isEmpty(shouldWord)) {
                expression = "(" + shouldWord.replaceAll("@", "OR") + ")";
            }
            if (!StringUtils.isEmpty(mustNotWord)) {
                expression = expression + " NOT (" + mustNotWord.replaceAll("@", " AND ") + ")";
            }
        } else {
            expression = mustWord.replaceAll("@", " AND ");
            if (!StringUtils.isEmpty(shouldWord)) {
                expression = expression + " AND (" + shouldWord.replaceAll("@", "OR") + ")";
            }
            if (!StringUtils.isEmpty(mustNotWord)) {
                expression = expression + " NOT (" + mustNotWord.replaceAll("@", " AND ") + ")";
            }
        }
        return expression;
    }

    public Map toMap() {
        Map staffMap = new HashMap<>();
        staffMap.put("name", name);
        staffMap.put("age", age);
        staffMap.put("gender", gender);
        staffMap.put("position", gender);
        staffMap.put("mustWord", mustWord);
        staffMap.put("shouldWord", shouldWord);
        staffMap.put("mustNotWord", mustNotWord);
        staffMap.put("expression", expression);
        staffMap.put("id", getId());
        return staffMap;
    }

    public static void main(String[] args) {
        Staff staff = new Staff();
        staff.setMustWord("must");
        staff.setShouldWord("should");
        staff.setMustNotWord("mustNot");
        System.out.println(staff.getExpression());
    }
}
