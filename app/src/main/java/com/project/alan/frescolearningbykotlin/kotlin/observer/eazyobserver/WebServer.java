package com.project.alan.frescolearningbykotlin.kotlin.observer.eazyobserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2020/10/22.
 */

class WebServer implements Observable {
    //观察者清单
    private List<Observer> observers;
    //发给用户的消息
    private String message;


    public WebServer() {
        if (observers == null) {
            observers = new ArrayList<>();
        }
    }

    @Override
    public void addObserver(Observer observer) {

        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void publishMessage(String message) {
        this.message = message;
        System.out.println("服务更新消息" + message);
        //通知所有的观察者
        notifyObservers();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
