package com.project.alan.frescolearningbykotlin.kotlin.observer.chainobserver;

/**
 * Created by Alan on 2020/10/22.
 * 抽象被观察者
 */

interface ObservableSource<T> {
    //提供订阅功能
    void subScribe(Observer<T> observer);
}
