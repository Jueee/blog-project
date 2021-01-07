package com.jueee.controller;

import com.jueee.annotations.ResponseEncrypt;
import com.jueee.bean.GetEncryptVO;
import com.jueee.bean.ResponseBean;
import com.jueee.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加密数据接口
 */
@Slf4j
@SuppressWarnings("deprecation")
@RestController
@RequestMapping("test")
public class SecretController {

    @Value("${module.boots.response.aes.key}")
    private String key;

    /**
     * 获取加密数据
     */
    @GetMapping(value = "/getEncrypt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseEncrypt
    public ResponseBean getEncrypt() {
        final GetEncryptVO vo = new GetEncryptVO();
        vo.setId("123456");
        vo.setUserName("Jueee");
        return ResponseBean.success(vo);
    }

    /**
     * 获取解密数据
     */
    @GetMapping(value = "/getDecrypt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseBean getDecrypt(@RequestParam(value = "content") String content) {
        log.info("content:"+content);
        final String str = AesUtils.decrypt(content, key);
        log.info("decrypt:"+str);
        return ResponseBean.success(str);
    }

}
