package com.topcom.cms.yuqing.utils;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by topcom on 2017/7/6 0006.
 */
public class SimHasher {

    public static final int HASH_BITS = 128;
    public static final BigInteger FNV_64_INIT = new BigInteger("144066263297769815596495629667062367629");
    public static final BigInteger FNV_64_PRIME = new BigInteger("309485009821345068724781371");
    public static final BigInteger MASK_64 = BigInteger.ONE.shiftLeft(HASH_BITS).subtract(BigInteger.ONE);
    /**
     * fnv-1 hash算法，将字符串转换为64位hash值
     *
     * @param str str
     * @return
     */
    public static BigInteger fnv1Hash64(String str) {
        BigInteger hash = FNV_64_INIT;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            hash = hash.multiply(FNV_64_PRIME);
            hash = hash.xor(BigInteger.valueOf(str.charAt(i)));
        }
        hash = hash.and(MASK_64);
        return hash;
    }

    /**
     * fnv-1a hash算法，将字符串转换为64位hash值
     *
     * @param str str
     * @return
     */
    public static BigInteger fnv1aHash64(String str) {
        BigInteger hash = FNV_64_INIT;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            hash = hash.xor(BigInteger.valueOf(str.charAt(i)));
            hash = hash.multiply(FNV_64_PRIME);
        }
        hash = hash.and(MASK_64);
        return hash;
    }

    /**
     * 返回二进制串hash距离
     *
     * @param str1 str1
     * @param str2 str2
     * @return
     */
    public static int getDistance(String str1, String str2) {
        int distance;

        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }


    //分析得到string的simhash
    public static BigInteger analysis(String content) {
        if (content==null){
            return null;
        }
        Map<String, Double> wordInfos = extract(content);
        double[] featureVector = new double[HASH_BITS];
        Set<String> words = wordInfos.keySet();
//		System.out.println(words);
        for (String word : words) {
            BigInteger wordhash = fnv1aHash64(word);
            for (int i = 0; i < HASH_BITS; i++) {
                BigInteger bitmask = BigInteger.ONE.shiftLeft(HASH_BITS - i - 1);
                if (wordhash.and(bitmask).signum() != 0) {
                    featureVector[i] += wordInfos.get(word);
                } else {
                    featureVector[i] -= wordInfos.get(word);
                }
            }
        }

        BigInteger signature = BigInteger.ZERO;
        StringBuffer hashBuffer = new StringBuffer();
        for (int i = 0; i < HASH_BITS; i++) {
            if (featureVector[i] >= 0) {
                signature = signature.add(BigInteger.ONE.shiftLeft(HASH_BITS - i - 1));
                hashBuffer.append("1");
            } else {
                hashBuffer.append("0");
            }
        }
        //二进制哈希值，临时无用
        String hash = hashBuffer.toString();
        return signature;
    }

    private static Map<String,Double> extract(String content) {
        //分词
        content = content.replaceAll("[\\p{Punct}\\p{Space}]+", "");
        List<String> words = HanLP.segment(content).stream().map(term -> term.word).collect(Collectors.toList());
        words = words.stream().filter(s -> !StringUtils.isEmpty(s)).collect(Collectors.toList());
        // 计算词频tf
        Map<String, Double> wordmap = new HashMap<String, Double>();
        for (String word : words) {
            wordmap.put(word, 1.0);
//            if (!wordmap.containsKey(word)) {
//                if (StringUtils.isEmpty(word)){
//                    continue;
//                }
//                wordmap.put(word, 1.0);
//            }else{
//                wordmap.put(word, wordmap.get(word) + 1);
//            }
        }


        // 删除停用词并计算权重
        Iterator<Map.Entry<String, Double>> it = wordmap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Double> item = (Map.Entry<String, Double>) it.next();
            String word = item.getKey();
            /**
             * 去除停用词与长度为一的关键词
             */
            if ( CoreStopWordDictionary.contains(word)||word.length()==1) {
                it.remove();
                continue;
            }

        }
        return wordmap;
    }


    /**
     * 汉明距离
     *
     * @param
     * @return
     */
    public static int getHammingDistance(BigInteger targetSignature1,BigInteger targetSignature2) {
        BigInteger x = targetSignature2.xor(targetSignature1);
        int tot = 0;

        // 统计x中二进制位数为1的个数
        // 我们想想，一个二进制数减去1，那么，从最后那个1（包括那个1）后面的数字全都反了，
        // 对吧，然后，n&(n-1)就相当于把后面的数字清0，
        // 我们看n能做多少次这样的操作就OK了。

        while (x.signum() != 0) {
            tot += 1;
            x = x.and(x.subtract(new BigInteger("1")));
        }

        return tot;
    }

    /**
     * hash距离。二进制比较
     *
     * @param targetHash 比较目标
     * @return
     */
//    public int getHashDistance(String targetHash) {
//        int distance;
//        if (this.getHash().length() != targetHash.length()) {
//            distance = -1;
//        } else {
//            distance = 0;
//            for (int i = 0; i < this.getHash().length(); i++) {
//                if (this.getHash().charAt(i) != targetHash.charAt(i)) {
//                    distance++;
//                }
//            }
//        }
//        return distance;
//    }
}
