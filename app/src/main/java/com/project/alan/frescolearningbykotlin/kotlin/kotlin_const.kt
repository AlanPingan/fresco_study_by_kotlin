package com.project.alan.frescolearningbykotlin.kotlin

/**
 * Created by Administrator on 2018/1/18.
 */
//val const所声明的不变的变量可以在 top-level级别
const val Tag = "ss"

class kotlin_const {
    //使用@JvmField 注解声明的val 类型的变量（内部作用就是抑制编辑器生成getter方法），无法重写其get方法
    @JvmField val TAG_THERE = "ss"
    companion object {
        //val const所声明的不变的变量可以类中
        const val TAG_TWO = "ss"
    }
}