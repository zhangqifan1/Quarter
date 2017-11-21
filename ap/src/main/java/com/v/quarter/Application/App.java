package com.v.quarter.Application;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

import cn.sharesdk.framework.ShareSDK;

/**
 * Created by Administrator on 2017/11/10.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
    }
}
