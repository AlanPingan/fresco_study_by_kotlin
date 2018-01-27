package com.project.alan.frescolearningbykotlin.kotlin.kotlin_singleton

/**
 * Created by Administrator on 2018/1/18.
 */
/**
 * kotlin懒汉式单例模式（单例在类的使用前生成）
 */
class Lazy_Singleton private constructor() {

    fun showInfo(){
        print("This is a lazy Singleton!")
    }

    companion object {
        val instance: Lazy_Singleton by lazy { Lazy_Singleton() }
    }
}