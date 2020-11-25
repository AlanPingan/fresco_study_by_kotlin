package com.project.alan.frescolearningbykotlin.system

import com.daoba.network.NetWorkApi
import com.facebook.drawee.backends.pipeline.Fresco

/**
 * Created by Administrator on 2018/1/12.
 */
class SystemApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        NetWorkApi.init(Network())
    }
}