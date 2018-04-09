package com.topcom.tjs.utils;


import com.spatial4j.core.context.SpatialContext;
import com.spatial4j.core.distance.DistanceUtils;
import com.spatial4j.core.shape.Rectangle;

/**
 * 地理位置处理工具类
 * @author lism
 * @date 2018/4/8
 */
public class GeoUtil {

    /**
     * 利用点和半径，圈出矩形区域
     * @param lat 经度
     * @param lng 维度
     * @param radius 半径（单位km）
     * @return 矩形区域
     */

    public static Rectangle geoRectangle(double lat, double lng, double radius){
        SpatialContext geo = SpatialContext.GEO;
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(
                geo.makePoint(lng, lat), radius * DistanceUtils.KM_TO_DEG, geo, null);
        return rectangle;
    }

    /**
     *根据原点到指定点的距离和半径对比过滤出在圆形范围内的点，去掉矩形内圆形外的点
     * @param lat1 经度1
     * @param lng1 维度1
     * @param lat2 经度2
     * @param lng2 维度2
     * @param radius 半径
     * @return
     */

    public static boolean filter(double lat1, double lng1, double lat2, double lng2,double radius){

        SpatialContext geo = SpatialContext.GEO;
        double distance = geo.calcDistance(geo.makePoint(lng1, lat1), geo.makePoint(lng2, lat2)) * DistanceUtils.DEG_TO_KM;

        return distance <= radius;
    }
}
