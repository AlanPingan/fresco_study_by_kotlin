package com.project.alan.frescolearningbykotlin.kotlin.kotlin_singleton;

/**
 * Created by Administrator on 2018/1/26.
 */

/**
 * 静态内部类的单例模式
 */
public class StaticInternalClassSingleton {
    private StaticInternalClassSingleton() {
    }

    public StaticInternalClassSingleton getInstance() {
        return StaticInternalClassSingletonHolder.instance;
    }

    //静态内部类
    private static class StaticInternalClassSingletonHolder {
        private static final StaticInternalClassSingleton instance = new StaticInternalClassSingleton();
    }
}
