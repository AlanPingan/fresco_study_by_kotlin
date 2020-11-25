package com.project.alan.frescolearningbykotlin.kotlin.observer.eazyobserver;

/**
 * Created by Alan on 2020/10/22.
 * 抽象被观察者
 */

interface Observable {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

}
