package com.project.alan.frescolearningbykotlin.kotlin.observer.chainobserver;

/**
 * Created by Alan on 2020/10/22.
 * 定义了发送消息的API
 */

public interface Emitter<T> {
    //发送消息
    void onNext(T t);
    //发送异常
    void onError(Throwable e);
    //发送完成
    void onComplete();
}
