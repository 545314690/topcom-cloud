package com.topcom.cms.yuqing.utils;

import net.sf.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by lisen on 2016/1/8.
 */
public class BaiduApiUtil {

    private static String GEO_URL = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=yDk89eO6KvHZ7cGF8MTqblZn&address=";
    public static class Geo{
        String lng;
        String lat;
        String level;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        @Override
        public String toString() {
            return "Geo{" +
                    "lng='" + lng + '\'' +
                    ", lat='" + lat + '\'' +
                    ", level='" + level + '\'' +
                    '}';
        }
    }
    public static Geo getGeo(String address) {
        Geo geo = new Geo();
        String url = GEO_URL;
        String res;
        try {
            address = URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = url + address;
        res = HttpUtil.doGet(url);
        if(res.contains("Error")){
            return null;
        }
        if (res != null) {
            JSONObject json = JSONObject.fromObject(res);
            JSONObject result = json.getJSONObject("result");
            JSONObject location = result.getJSONObject("location");
            geo.setLng(location.getString("lng"));
            geo.setLat(location.getString("lat"));
            geo.setLevel(result.getString("level"));
        }
        return geo;
    }

    public static void main(String[] args) {
        Geo geo = getGeo("新疆阿克苏阿克苏");
        System.out.println(geo);
    }
}
