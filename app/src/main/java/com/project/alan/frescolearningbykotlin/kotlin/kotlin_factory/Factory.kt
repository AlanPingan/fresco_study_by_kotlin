package com.project.alan.frescolearningbykotlin.kotlin.kotlin_factory

/**
 * Created by Alan on 2018/4/4.
 * 工厂模式一般提供一组创建一个对象的方法，方法没有具体的实现
 */
abstract class Factory {

    abstract fun createProductA()

    abstract fun createProductB()

    abstract fun createProductC()

}