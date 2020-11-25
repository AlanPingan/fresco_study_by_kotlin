package com.project.alan.frescolearningbykotlin.system;


import com.daoba.network.INetworkRequiredInfo;
import com.project.alan.frescolearningbykotlin.BuildConfig;

/**
 * Created by Alan on 2020/10/20.
 */

class Network implements INetworkRequiredInfo {
    @Override
    public String getAppVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    @Override
    public String getAppVersionCode() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
