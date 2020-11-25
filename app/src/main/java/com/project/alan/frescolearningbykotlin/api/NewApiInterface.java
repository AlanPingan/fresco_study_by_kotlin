package com.project.alan.frescolearningbykotlin.api;


import io.reactivex.Observable;
import retrofit2.http.GET;


/**
 * Created by Alan on 2020/10/20.
 */

public interface NewApiInterface {
    @GET("release/channel")
    Observable<NewsChannelBean> getNewsChannels();
}
