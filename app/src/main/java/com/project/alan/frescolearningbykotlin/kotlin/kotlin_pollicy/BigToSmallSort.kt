package com.project.alan.frescolearningbykotlin.kotlin.kotlin_pollicy

import java.util.*

/**
 * Created by Alan on 2018/4/8.
 */
class BigToSmallSort : Sort {
    override fun sort(list: MutableList<Int>) {
        //升序排列
        list.sortDescending()
    }
}