package com.project.alan.frescolearningbykotlin.kotlin.kotlin_operaional_character

/**
 * Created by Alan on 2018/4/24.
 * kotlin 总数操作符
 */


class OperationalCharacter {
    //any 有任意一个元素满足条件就返回true否则返回为false
    fun useAny(): Boolean {
        return valueList.any { it % 2 == 1 }
    }

    //all 当所有的元素满足条件时返回true否则返回false
    fun useAll(): Boolean {
        return valueList.all { it > 0 }
    }

    //count 返回满足条件的item数量
    fun useCount(): Int {
        return valueList.count { it > 6 }
    }

    //fold 返回从一个初始值开始从第一项累计到最后一项的值
    fun useFold(): Int {
        return valueList.fold(100, { tatol, next -> tatol + next })
    }

    //fold 返回从一个初始值开始从最后一项累计到第一项的值
    fun useFoldRight(): Int {
        return valueList.foldRight(100, { tatol, next -> tatol + next })
    }

    //forEach 遍历所有的元素
    fun useForEach() {
        valueList.forEach { print("正在使用forEach遍历，当前是：$it") }
    }

    //forEachIndexed 遍历所有的元素
    fun useForEachIndexed() {
        valueList.forEachIndexed { index, value -> print("正在使用forEachIndexed遍历，当前是$index 元素value：$value") }
    }

    //max 返回最大的一项，没有则返回为空 ;min类似
    fun useMax(): Int? {
        return valueList.max()
    }

    //maxBy 返回最大的一项，没有则返回为空 ;minBy类似
    fun useMaxBy(): Int? {
        return valueList.maxBy { it * 2 - 5 }
    }

    //none 当所有的元素都与给定的函数匹配时返回true，否则返回false
    fun useNone(): Boolean {
        return valueList.none { it > 5 }
    }

    //reduce 和fold一样但是没有一个初始值,同时reduceRight是从最后一项到第一项
    fun useReduce(): Int {
        return valueList.reduce { total, i -> total + i }
    }

    //sumBy 返回每一项通过函数转换后的值
    fun useSumBy(): Int {
        return valueList.sumBy { it * 2 }
    }


    companion object {
        var valueList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }

}