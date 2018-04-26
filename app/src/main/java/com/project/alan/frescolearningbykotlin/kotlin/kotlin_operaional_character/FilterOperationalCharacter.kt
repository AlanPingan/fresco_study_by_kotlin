package com.project.alan.frescolearningbykotlin.kotlin.kotlin_operaional_character

/**
 * Created by Alan on 2018/4/25.
 */
class FilterOperationalCharacter {
    //drop 返回去掉前n个元素的集合
    fun useDrop(): List<Int> {
        return valueList.drop(2)
    }

    //dropWhile 返回根据给定函数去掉从第一项开始的集合
    //dropWhileLast 从最后一项开始
    fun useDropWhile(): List<Int> {
        return valueList.dropWhile { it < 3 }
    }

    //filter 过滤掉所有符合给定函数的元素
    fun useFilter(): List<Int> {
        return valueList.filter { it % 3 == 0 }
    }

    //filterNot 过滤掉所有不符合给定函数的元素
    fun useFilterNot(): List<Int> {
        return valueList.filterNot { it % 3 == 0 }
    }

    //slice 过滤集合中一个指定index的元素
    fun useSlice(): List<Int> {
        return valueList.slice(listOf(1, 2, 3))
    }

    //take 返回从第一开始的n个元素
    //takeLast 返回从最后一个开始的n个元素
    fun useTake(): List<Int> {
        return valueList.take(4)
    }

   //takeWhile 返回从第一个开始符合给定函数的元素
    fun useTakeWhile():List<Int>{
       return  valueList.takeWhile { it>5 }
   }

    companion object {
        var valueList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    }
}