package com.project.alan.frescolearningbykotlin.kotlin.kotlin_prototype

/**
 * Created by Alan on 2018/3/12.
 */
class Student : Cloneable {

    var name: String = "啦啦啦"
    var number: Int = 666

    override fun clone(): Any {
        var s: Student = Student()
        s.number = this.number
        s.name = this.name
        return s
    }

    override fun toString(): String {
        return "name:$name,number:$number"
    }


}