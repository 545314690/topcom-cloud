package com.topcom.tjs.utils;

import com.topcom.cms.common.page.DateParam;
import org.apache.commons.lang.math.RandomUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 创建测试数据工具类
 * @author maxl
 * @date 2018/3/27 0027
 */
public class CreateDataUtil {
    public static Random random = new Random();

    /**
     * 根据最小值最大值得到一个正态分布的随机数
     * @param a 最小值
     * @param b 最大值
     * @return
     */
    public  static int getGaussianRandom(int a,int b){
        int u = (a+b)/2;
        int o = b-a;
        double value = Math.sqrt(o) * random.nextGaussian() + u;
        if (value==0){return 0;}
        if (value<a||value>b) {
            return getGaussianRandom(a,b);
        }
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数
        return Integer.parseInt(df.format(value));
    }

    /**
     * 根据最小值最大值得到一个正态分布的随机数 左边权重大，重心在a
     * @param a 最小值
     * @param b 最大值
     * @return
     */
    public  static int getGaussianRandomBigLeft(int a,int b){
        int u = a;
        int o = (b-a);
        double value = Math.sqrt(o) * random.nextGaussian() + u;
        if (value<a||value>b) {
            return getGaussianRandom(a,b);
        }
        DecimalFormat df = new DecimalFormat("######0"); //四色五入转换成整数
        return Integer.parseInt(df.format(value));
    }

    /**
     * 随机得到一列 人名
     * @param limit 个数
     * @return
     */
    public static List<String> getRandomName(int limit){
        File file = new File("D:\\wokespace-ma\\git\\topcom-cloud\\anjian-tjs\\Chinese_Names.txt");
        List<String> read = FileUtil.read(file);
        List<String> result = new ArrayList<>();
        for (int i=0;i<limit;i++){
            result.add(read.get(random.nextInt(read.size()-1)));
        }
        return result;
    }

    public static List<String> getRandomName(){
        File file = new File("D:\\wokespace-ma\\git\\topcom-cloud\\anjian-tjs\\Chinese_Names.txt");
        return FileUtil.read(file);
    }

    /**
     * 根据起止时间得到随机时间
     * @param date
     * @return
     */
    public static Date getRandomDate (DateParam date){
        long s = date.startDate().getTime();
        long e = date.endDate().getTime();
        return new Date((long) (random.nextDouble() * (e - s))+s);
    }

    public static void main(String[] args) {
        List<String> randomName = getRandomName(20);
        for (String name:randomName){
            System.out.println(name);
        }


//        Map<Integer,Integer> result = new HashMap<>();
//        for (int i =0;i<100000;i++){
//            int a = getGaussianRandomBigLeft(0,10);
//            if (result.containsKey(a)){
//                Integer integer = result.get(a);
//                result.put(a,++integer);
//            }else {
//                result.put(a,1);
//            }
//        }
//        for (Integer i: result.keySet()){
//            System.out.println(i+":"+result.get(i));
//        }
    }
}
