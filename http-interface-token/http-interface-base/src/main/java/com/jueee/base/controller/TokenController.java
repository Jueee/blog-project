package com.jueee.base.controller;

import com.jueee.base.annotation.NotRepeatSubmit;
import com.jueee.base.bean.*;
import com.jueee.base.service.AppInfoService;
import com.jueee.base.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/token")
public class TokenController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AppInfoService appInfoService;

    /**
     * API Token
     *
     * @param sign
     * @return
     */
    @PostMapping("/api_token")
    public ApiResponse<AccessToken> apiToken(String appId, String timestamp, String sign) {
        Assert.isTrue(!StringUtils.isEmpty(appId) && !StringUtils.isEmpty(timestamp) && !StringUtils.isEmpty(sign), "参数错误");

        long reqeustInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
        Assert.isTrue(reqeustInterval < 5 * 60 * 1000, "请求过期，请重新请求");

        // 1. 根据appId查询数据库获取appSecret
        AppInfo appInfo = appInfoService.selectByAppId(appId);

        // 2. 校验签名
        String signString = timestamp + appId + appInfo.getAppKey();
        String signature = MD5Util.encode(signString);
        log.info(signature);
        Assert.isTrue(signature.equals(sign), "签名错误");

        // 3. 如果正确生成一个token保存到redis中，如果错误返回错误信息
        AccessToken accessToken = this.saveToken(0, appInfo);

        return ApiResponse.success(accessToken);
    }

    private AccessToken saveToken(int tokenType, AppInfo appInfo) {
        String token = UUID.randomUUID().toString();

        // token有效期为2小时
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND, 7200);
        Date expireTime = calendar.getTime();

        // 4. 保存token
        ValueOperations<String, TokenInfo> operations = redisTemplate.opsForValue();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setTokenType(tokenType);
        tokenInfo.setAppInfo(appInfo);

        try {
            operations.set(token, tokenInfo, 7200, TimeUnit.SECONDS);
        } catch (Exception e){
            log.error(e.getMessage(),e);
        }

        AccessToken accessToken = new AccessToken(token, expireTime);

        return accessToken;
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        String signString = timestamp + "1" + "12345678954556";
        String sign = MD5Util.encode(signString);
        System.out.println(sign);

        System.out.println("-------------------");
        signString = "password=123456&username=1&12345678954556" + "ff03e64b-427b-45a7-b78b-47d9e8597d3b1529815393153sdfsdfsfs" + timestamp + "A1scr6";
        sign = MD5Util.encode(signString);
        System.out.println(sign);
    }
}
