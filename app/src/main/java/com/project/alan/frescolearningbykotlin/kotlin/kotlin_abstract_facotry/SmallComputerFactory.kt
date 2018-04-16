package com.project.alan.frescolearningbykotlin.kotlin.kotlin_abstract_facotry

/**
 * Created by Alan on 2018/4/4.
 */
class SmallComputerFactory : ComputerFactory() {
    override fun createScreen(): IScreen {
        return SmallScreen()

    }

    override fun createHost(): IHost {
        return SmallHost()
    }
}