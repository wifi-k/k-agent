//package com.witown.location.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.witown.location.entity.BaiduResponse;
//import com.witown.location.entity.GaodeCoordinate;
//import com.witown.location.entity.GaodeResponse;
//import org.springframework.http.*;
//import org.springframework.util.StringUtils;
//import org.springframework.web.client.RestTemplate;
//
///**
// * 网络请求工具
// *
// * @author ding
// *
// * Created by ding on 2019/3/8 19:41
// */
//public class NetUtil {
//
//    /**
//     * 百度逆地理编码
//     */
//    private static final String BAIDU_URl = "http://api.map.baidu.com/geocoder/v2/?ak=mLa4HZH3bVyXG0zXbKoLu98ZUjBfEB5l&location={}&output=json&pois=1";
//
//    /**
//     * 高德周边搜索API服务地址
//     */
//    private static final String GAODE_URl = "http://restapi.amap.com/v3/place/around?key=9f137f96e4ce65c1bad2258f5f4fbba8&location=[]&keywords={}&types=&radius=10000&offset=4&page=1&extensions=all&sortrule=weight";
//
//    private static final String GAODE_URl1 = "http://restapi.amap.com/v3/place/around?key=9f137f96e4ce65c1bad2258f5f4fbba8&location=[]&keywords={}&types=<>&radius=1000&offset=4&page=1&extensions=all&sortrule=weight";
//
//    /**
//     * 百度坐标转高德坐标
//     */
//    private static final String GAODE_URl2 = "https://restapi.amap.com/v3/assistant/coordinate/convert?locations={}&coordsys=baidu&output=json&key=9f137f96e4ce65c1bad2258f5f4fbba8";
//    /**
//     * 高德逆地理编码
//     */
//    private static final String GAODE_URl3 = "https://restapi.amap.com/v3/geocode/regeo?output=json&location={}&key=9f137f96e4ce65c1bad2258f5f4fbba8&radius=1000&extensions=all";
//
//    /**
//     * baidu
//     *
//     * @param location 经纬度字符串(小大)
//     * @return
//     */
//    public static BaiduResponse baidu(String location) {
//        String url = StringUtils.replace(BAIDU_URl, "{}", location);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        String response  = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
//        System.out.println("百度周边:" + url);
//        System.out.println("baidu response:" + response);
//        return JSONObject.parseObject(response, BaiduResponse.class);
//    }
//
//    /**
//     * 高德周边搜索API
//     *
//     * @param location 经纬度字符串（大小）
//     * @return
//     */
//    public static GaodeResponse gaode(String location, String keywords) {
//        String url = StringUtils.replace(GAODE_URl, "[]", location).replace("{}", keywords);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        String response  = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
//        System.out.println("高德周边API：" + url);
//        return JSONObject.parseObject(response, GaodeResponse.class);
//    }
//
//    /**
//     * 高德周边搜索API
//     *
//     * @param location 经纬度字符串（大小）
//     * @return
//     */
//    public static GaodeResponse gaode2(String location, String type, String keywords) {
//        String url = StringUtils.replace(GAODE_URl1, "[]", location).replace("{}", keywords).replace("<>", type);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        String response  = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
//        System.out.println("高德周边API：" + url);
//        return JSONObject.parseObject(response, GaodeResponse.class);
//    }
//
//    /**
//     * 百度坐标转高德坐标
//     *
//     * @param location 百度坐标
//     * @return 高德坐标
//     */
//    public static String getGaodeCoordinate(String location) {
//        String url = StringUtils.replace(GAODE_URl2, "{}", location);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        String response  = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
//        System.out.println("高德坐标转换：" + url);
//        return JSONObject.parseObject(response, GaodeCoordinate.class).getLocations();
//
//    }
//
//    public static String getGaodeBuniness(String location) {
//        String url = StringUtils.replace(GAODE_URl3, "{}", location);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        String response  = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(headers), String.class).getBody();
//        System.out.println("高德逆地址解析：" + url);
//
//        return null;
//    }
//}
