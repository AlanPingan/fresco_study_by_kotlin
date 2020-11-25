package com.project.alan.frescolearningbykotlin.kotlin.observer.chainobserver;

/**
 * Created by Alan on 2020/10/22.
 * 抽象观察者
 */

interface Observer<T> {

    //用于定订阅成功的回调
    void onSubscribe();

    //收到消息
    void onNext(T t);

    //出错的回调
    void onError(Throwable e);

    //从订阅到消息发送全部完成
    void onComplete();

}
