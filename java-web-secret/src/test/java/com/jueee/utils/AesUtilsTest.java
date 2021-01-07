package com.jueee.utils;

import org.junit.jupiter.api.Test;

class AesUtilsTest {

    private String aesKey = "6162382d323fb399fc202a5aca55336a";

    @Test // 生成 AES Key
    public void test(){
        System.out.println(AesUtils.getKeyByPass("Jueee"));
    }

    @Test // AES 解密
    public void testDecrypt(){
        final String str = "FJscqvyWFReJftsv9WxkIFpHq8Y/GLBAi4tbv/qyAqLMeCAr8fpaJBQ83F4Owe1S";
        System.out.println(AesUtils.decrypt(str, aesKey));
    }
}