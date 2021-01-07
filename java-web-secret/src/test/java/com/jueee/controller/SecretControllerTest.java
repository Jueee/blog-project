package com.jueee.controller;

import com.jueee.utils.HttpClient4;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;

@Slf4j
class SecretControllerTest {

    @Test
    public void test() throws Exception {
        String encrypt = HttpClient4.doGet("http://127.0.0.1:8080/test/getEncrypt");
        log.info(encrypt);
        JSONObject jsonObject = new JSONObject(encrypt);
        log.info(jsonObject.getString("data"));
        String data = jsonObject.getString("data");
        String decrypt = HttpClient4.doGet("http://127.0.0.1:8080/test/getDecrypt?content="+ URLEncoder.encode(data, "UTF-8"));
        log.info(decrypt);
    }
}