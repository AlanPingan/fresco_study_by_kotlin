package com.project.alan.frescolearningbykotlin.kotlin.kotlin_abstract_facotry

/**
 * Created by Alan on 2018/4/4.
 */
class BigComputerFactory : ComputerFactory() {

    override fun createScreen(): IScreen {
        return BigScreen()
    }

    override fun createHost(): IHost {
        return BigHost()
    }
}