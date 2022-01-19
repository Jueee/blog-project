package com.jueee.base.client;

import com.alibaba.fastjson.JSONObject;
import com.jueee.base.utils.MD5Util;
import com.jueee.base.utils.HttpClientUtil;

import java.util.*;

public class TokenClient {

    public final static String API_TOKEN = "http://localhost:8080/api/token/api_token";
    public final static String USER_TOKEN = "http://localhost:8080/api/user/user_token";

    public final static String APP_ID = "2";
    public final static String APP_KEY = "22222222222222";

    public static void addCommonParam(Map<String, String> paramMap) {
        // 获取 token 请求结果：
        String res = get_API_TOKEN();
        if (res == null) {
            return;
        }
        JSONObject jsonObject = JSONObject.parseObject(res);
        if (jsonObject.getJSONObject("data") == null) {
            return;
        }
        // 从结果中，获取token信息
        String token = jsonObject.getJSONObject("data").getString("token");
        paramMap.put("token", token);
        long timestamp = System.currentTimeMillis();
        String nonce = UUID.randomUUID().toString();
        paramMap.put("timestamp", String.valueOf(timestamp));
        paramMap.put("nonce", nonce);
        // 请求参数 + APP_KEY + token + timestamp + nonce
        String signString = concatSignString(paramMap) + APP_KEY + token + timestamp + nonce;
        String sign = MD5Util.encode(signString);
        paramMap.put("sign", sign);
    }

    public static void test1() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("username", "1");
        paramMap.put("password", "123456");
        addCommonParam(paramMap);
        System.out.println(paramMap);
        String res = HttpClientUtil.doPost(USER_TOKEN, paramMap);
        System.out.println(res);
    }

    public static String get_API_TOKEN() {
        Map<String, String> paramMap = new HashMap<>();
        long timestamp = System.currentTimeMillis();
        paramMap.put("appId", APP_ID);
        paramMap.put("timestamp", String.valueOf(timestamp));
        String signString = timestamp + APP_ID + APP_KEY;
        String sign = MD5Util.encode(signString);
        paramMap.put("sign", sign);
        String res = HttpClientUtil.doPost(API_TOKEN, paramMap);
        System.out.println(res);
        return res;
    }

    public static String concatSignString(Map<String, String> map) {
        Map<String, String> paramterMap = new HashMap<>();
        map.forEach((key, value) -> paramterMap.put(key, value));
        // 按照key升续排序，然后拼接参数
        Set<String> keySet = paramterMap.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (paramterMap.get(k).trim().length() > 0) {
                // 参数值为空，则不参与签名
                sb.append(k).append("=").append(paramterMap.get(k).trim()).append("&");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        test1();
    }
}
