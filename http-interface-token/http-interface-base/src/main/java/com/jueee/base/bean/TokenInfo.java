package com.jueee.base.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzweiyongqiang
 */
@Data
public class TokenInfo implements Serializable {

    /** token类型: api:0 、user:1 */
    private Integer tokenType;

    /** App 信息 */
    private AppInfo appInfo;

}
