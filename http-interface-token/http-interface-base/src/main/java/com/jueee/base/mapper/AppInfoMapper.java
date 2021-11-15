package com.jueee.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jueee.base.bean.AppInfo;

public interface AppInfoMapper extends BaseMapper<AppInfo> {

    AppInfo selectByAppId(String appId);
}
