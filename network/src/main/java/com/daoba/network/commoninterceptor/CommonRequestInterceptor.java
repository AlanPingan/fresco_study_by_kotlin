package com.daoba.network.commoninterceptor;

import com.daoba.network.INetworkRequiredInfo;
import com.daoba.network.R;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alan on 2020/10/20.
 */

public class CommonRequestInterceptor implements Interceptor {

    private INetworkRequiredInfo iNetworkRequiredInfo;

    public CommonRequestInterceptor(INetworkRequiredInfo iNetworkRequiredInfo) {
        this.iNetworkRequiredInfo = iNetworkRequiredInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //todo 处理时间字符串
        String timeStr = "";
        Request.Builder builder = new Request.Builder();
        builder.addHeader("os", "android");
        builder.addHeader("appVersion", iNetworkRequiredInfo.getAppVersionCode());
        builder.addHeader("Source", "source");
        //todo 处理Authorization
        builder.addHeader("Authorization", "Authorization");
        builder.addHeader("Date", timeStr);

        return chain.proceed(builder.build());
    }
}
