package com.jueee.base.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author hzweiyongqiang
 */
@Data
@TableName("AppInfo")
public class AppInfo implements Serializable {
    private Integer id;
    /**
     * App id
     */
    private String appId;
    /**
     * API 秘钥
     */
    private String appKey;

    public AppInfo(String appId, String appKey) {
        this.appId = appId;
        this.appKey = appKey;
    }
}
