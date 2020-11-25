package com.project.alan.frescolearningbykotlin.kotlin.observer.eazyobserver;

/**
 * Created by Alan on 2020/10/22.
 */

class User implements Observer {
    private String name;
    private String message;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Object msg) {
        this.message = message;
    }

    void read() {
        System.out.println("收到了这个消息" + name);
    }
}
