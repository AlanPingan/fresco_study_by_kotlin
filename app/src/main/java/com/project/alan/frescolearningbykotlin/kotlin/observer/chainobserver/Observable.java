package com.project.alan.frescolearningbykotlin.kotlin.observer.chainobserver;

/**
 * Created by Alan on 2020/10/22.
 */

public abstract class Observable<T> implements ObservableSource {


    @Override
    public void subScribe(Observer observer) {
        //将这个订阅的功能让Observable的子类去完成
        subScribeActual(observer);
    }

    protected abstract void subScribeActual(Observer<T> observer);

    //创造被观察者实例
    public static<T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate(source);
    }
}
