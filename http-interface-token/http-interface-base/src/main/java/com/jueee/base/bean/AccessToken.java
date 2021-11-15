package com.jueee.base.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class AccessToken implements Serializable {
    /** token */
    private String token;

    /** 失效时间 */
    private Date expireTime;
}
