package com.project.alan.frescolearningbykotlin.kotlin.kotlin_singleton;

/**
 * Created by Administrator on 2018/1/18.
 */

public class PerfectSingleton {
    private PerfectSingleton instance;

    private PerfectSingleton() {
    }

    /**
     * 双重判断加锁的懒汉式单例模式
     * @return
     */
    public PerfectSingleton getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new PerfectSingleton();
                }
            }
        }
        return instance;
    }

}
