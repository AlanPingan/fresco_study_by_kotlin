package com.daoba.network;


import com.daoba.network.beans.TecentBaseResponse;
import com.daoba.network.commoninterceptor.CommonRequestInterceptor;
import com.daoba.network.commoninterceptor.CommonResponseInterceptor;
import com.daoba.network.errorhandler.ExceptionHandle;
import com.daoba.network.errorhandler.HttpErrorHandler;



import java.util.HashMap;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Alan on 2020/10/20.
 */

public class NetWorkApi {
    private static final String TENCENT_BASE_URL = "http://service-o5ikp40z-1255468759.ap-shanghai.apigateway.myqcloud.com/";
    public static final String TAG = "NetWorkApi";
    private static final HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();

    private static INetworkRequiredInfo iNetworkRequiredInfo;

    //在Application里面初始化
    public static void init(INetworkRequiredInfo networkRequiredInfo) {
        iNetworkRequiredInfo = networkRequiredInfo;
    }

    public static Retrofit getRetrofit(Class service) {
        if (retrofitHashMap.get(TENCENT_BASE_URL + service.getName()) != null) {
            return retrofitHashMap.get(TENCENT_BASE_URL + service.getName());
        }
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(TENCENT_BASE_URL);
        retrofitBuilder.client(getOkHttpClient());
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        retrofitHashMap.put(TENCENT_BASE_URL, retrofit);
        return retrofit;

    }

    //保证同一个类名只能创建一个retrofit
    public static <T> T getService(Class<T> service) {
        return getRetrofit(service).create(service);
    }


    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new CommonRequestInterceptor(iNetworkRequiredInfo));
        builder.addInterceptor(new CommonResponseInterceptor());
        if (iNetworkRequiredInfo != null && iNetworkRequiredInfo.isDebug()) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        return builder.build();
    }

    public static <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return (upstream) -> {
            Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).map(NetWorkApi.<T>getAppErrorHandler()).onErrorResumeNext(new HttpErrorHandler<T>());
            observable.subscribe(observer);
            return observable;
        };
    }

    public static <T> Function<T, T> getAppErrorHandler() {
        return (response) -> {
            //response 中的code码不为0 出现错误
            if (response instanceof TecentBaseResponse && ((TecentBaseResponse) response).showapiResCode != 0) {
                ExceptionHandle.ServerException exception = new ExceptionHandle.ServerException();
                exception.code = ((TecentBaseResponse) response).showapiResCode;
                exception.message = ((TecentBaseResponse) response).showapiResError != null ? ((TecentBaseResponse) response).showapiResError : "";
                throw exception;
            }
            return response;
        };
    }

}
