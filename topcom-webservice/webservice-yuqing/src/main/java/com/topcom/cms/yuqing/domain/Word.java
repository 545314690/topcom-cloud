package com.topcom.cms.yuqing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;
import com.topcom.cms.base.model.BaseEntityModel;
import net.sf.json.JSONObject;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.connection.convert.ListConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by topcom on 2017/7/4 0004.
 */
@Entity
@Table(name = "t_word")
@EntityListeners({AuditingEntityListener.class})
public class Word extends BaseEntityModel {

    public Word(String word, Status status) {
        this.word = word;
        this.status = status;
    }

    public Word(String word) {
        this.word = word;
    }
    public Word() {}

    @Column(name = "word")
    private String word;

    /**
     * 拼音
     */
    @Column(name = "pinyin")
    private String pinyin;


    @Column(name = "firstPinyin")
    private char firstPinyin='.';
    /**
     * 词性
     */
    @Column(name = "feature")
    private String feature;

    /**
     * 权重
     */
    @Column(name="weight")
    private float weight;

    public enum Status {
        MUST, SHOULD, MUSTNOT
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status status;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_library_word",
            joinColumns = @JoinColumn(name = "wordId"),
            inverseJoinColumns = @JoinColumn(name = "libraryId")
    )
    private Set<Library> library;

    @Column(name="libraryNames")
    private String[] libraryNames;

    public Set<Library> getLibrary() {
        return library;
    }

    public void setLibrary(Set<Library> library) {
        this.library = library;
    }

    public Set<String> getLibraryNames() {
        Set<Library> librarySet = this.library;
        if (librarySet!=null){
            return librarySet.stream().map(lb->lb.getName()).collect(Collectors.toSet());
        }
        return null;
    }

    public void setLibraryNames(String[] libraryNames) {
        this.libraryNames = libraryNames;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }


    public char getFirstPinyin() {
        return firstPinyin;
    }

    public void setFirstPinyin(char firstPinyin) {
        this.firstPinyin = firstPinyin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
        List<Pinyin> pinyins = HanLP.convertToPinyinList(word);
        String py="";
        if (pinyins!=null&&pinyins.size()>0){
            this.firstPinyin = pinyins.get(0).getFirstChar();
            py=pinyins.get(0).getPinyinWithoutTone();
        }
        for (int i=1;i<pinyins.size();i++){
            py= py+pinyins.get(i).getPinyinWithoutTone();
        }
        this.pinyin =py;
        if (this.firstPinyin==' '){
            this.firstPinyin='.';
        }
        if (this.pinyin.indexOf("none")!=-1){
            this.pinyin=this.word;
        }
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

   public void addLibrary(Library library){
       if (!this.library.contains(library)){
           Set<Library> librarySet = new HashSet<>();
           librarySet.addAll(this.library);
           librarySet.add(library);
           this.setLibrary(librarySet);
       }
   }

    public void removeLibrary(Library library){
        if (this.library.contains(library)){
            Set<Library> librarySet = new HashSet<>();
            librarySet.addAll(this.library);
            librarySet.remove(library);
            this.setLibrary(librarySet);
        }
    }

    public static void main(String[] args) {
        Word word = new Word();
        word.setWord("ssa");
        word.setPinyin("saqwq");
        char firstPinyin = word.getFirstPinyin();
        JSONObject jsonObject = JSONObject.fromObject(word);
        System.out.println(firstPinyin);
    }
}
