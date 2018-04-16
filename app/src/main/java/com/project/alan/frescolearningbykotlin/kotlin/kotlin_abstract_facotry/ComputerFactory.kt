package com.project.alan.frescolearningbykotlin.kotlin.kotlin_abstract_facotry

/**
 * Created by Alan on 2018/4/4.
 * 抽象工厂模式最大的优点是将接口与具体实现分离，与具体的产品实现解耦
 * 但是它的缺点就是会增加具体实现类的个数，需要仔细考虑后使用
 */
abstract class ComputerFactory {

    abstract fun createScreen(): IScreen

    abstract fun createHost(): IHost

}