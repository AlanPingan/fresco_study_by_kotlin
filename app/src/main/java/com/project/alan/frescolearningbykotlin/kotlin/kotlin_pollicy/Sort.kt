package com.project.alan.frescolearningbykotlin.kotlin.kotlin_pollicy

import java.util.*

/**
 * Created by Alan on 2018/4/8.
 * 优点：
 * 策略模式的有点就是将算法与接口分离，
 * 调用者可以通过实现接口添加不同的实现策略算法，符合开闭原则
 *
 * 缺点：不同的实现策略会导致类的数量增加
 */
interface Sort {

    fun sort(list: MutableList<Int>)
}