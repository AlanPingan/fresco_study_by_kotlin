package com.project.alan.frescolearningbykotlin.kotlin.kotlin_builder

/**
 * Created by Administrator on 2018/2/1.
 */
class ComputerBuilder {
    @JvmField var name: String? = null
    @JvmField var memorySize: Int? = 0

    fun setName(name: String): ComputerBuilder {
        this.name = name
        return this
    }

    fun setMemorySize(memorySize: Int): ComputerBuilder {
        this.memorySize = memorySize
        return this
    }

    fun build(): Computer {
        var computer = Computer()
        computer.memorySize = this.memorySize
        computer.name = this.name
        return computer
    }
}