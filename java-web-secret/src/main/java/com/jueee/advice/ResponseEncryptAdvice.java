package com.jueee.advice;

import com.jueee.bean.ResponseBean;
import com.jueee.utils.AesUtils;
import com.jueee.utils.NeedDecrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 对接口数据进行加密
 */
@ControllerAdvice
public class ResponseEncryptAdvice implements ResponseBodyAdvice<Object> {

    @Value("${module.boots.response.aes.key}")
    private String key;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 在写入之前更改body的值
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {
        // 判断是否需要加密
        final boolean encrypt = NeedDecrypt.needEncrypt(returnType);
        if (!encrypt) {
            return body;
        } else {
            // 如果body是属于ResponseBean类型,只需要对data里面的数据进行加密即可
            if (body instanceof ResponseBean) {
                final ResponseBean responseBean = (ResponseBean) body;
                final Object data = responseBean.getData();
                if (data == null) {
                    return body;
                } else {
                    responseBean.setData(AesUtils.encrypt(data.toString(), key));
                    return responseBean;
                }
            } else {
                return body;
            }
        }
    }

}
