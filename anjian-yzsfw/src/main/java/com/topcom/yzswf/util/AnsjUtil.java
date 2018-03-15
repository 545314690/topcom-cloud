package com.topcom.yzswf.util;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by lism on 15:29.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 * <a href="http://nlpchina.github.io/ansj_seg/">基于Ansj的分词，查看文档</a>
 */
public class AnsjUtil {
    /**
     * 基于搜索引擎的分词
     * @param sentence
     * @return
     */
    public static String indexAnalysis(String sentence){
        if(StringUtils.isBlank(sentence)){
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        List<Term> termList = IndexAnalysis.parse(sentence);
        for(Term term:termList){
            stringBuffer.append(term.getName());
            //以空格分割每个分词结果
            stringBuffer.append(" ");
        }
        //去掉最后一个空格
        stringBuffer.deleteCharAt(stringBuffer.length()-1);
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String sentence = "全文检索是利用查询关键字和查询列内容之间的相关度进行检索";
        System.out.println(AnsjUtil.indexAnalysis(sentence));
    }
}
