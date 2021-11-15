package com.jueee.base.service;

import com.jueee.base.bean.AppInfo;
import com.jueee.base.mapper.AppInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AppInfoService {

    @Autowired
    AppInfoMapper appInfoMapper;

    public AppInfo selectByAppId(String appId){
        return appInfoMapper.selectByAppId(appId);
    }
}
