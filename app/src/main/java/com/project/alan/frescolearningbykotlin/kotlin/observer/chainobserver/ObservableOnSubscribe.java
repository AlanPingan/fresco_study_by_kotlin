package com.project.alan.frescolearningbykotlin.kotlin.observer.chainobserver;

/**
 * Created by Alan on 2020/10/22.
 */

public interface ObservableOnSubscribe<T> {
    //为每个订阅的观察者提供一个能发消息的功能
    void subscribe(Emitter<T> emitter) ;
}
