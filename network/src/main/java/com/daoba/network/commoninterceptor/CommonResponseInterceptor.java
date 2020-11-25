package com.daoba.network.commoninterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Alan on 2020/10/20.
 */

public class CommonResponseInterceptor implements Interceptor {
    private static final String TAG = "CommonResponseInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        System.out.println(TAG + "requestTime = " + (System.currentTimeMillis() - requestTime));
        return response;
    }
}
