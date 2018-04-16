package com.project.alan.frescolearningbykotlin.kotlin.kotlin_pollicy

/**
 * Created by Alan on 2018/4/8.
 */
class PolicyTest {

    companion object {
        @JvmStatic fun test() {
            var number = mutableListOf(1,2,3,4,5,8,7)
            BigToSmallSort().sort(number)
            println("降序排列："+number)
            SmallToBigSort().sort(number)
            println("升序排列："+number)
        }
    }
}