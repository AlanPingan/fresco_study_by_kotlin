package com.project.alan.frescolearningbykotlin.scorll

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Alan on 2018/4/27.
 */

class ScrollActivity : AppCompatActivity() {
    private val contentView: ScrollLayout by lazy {
        ScrollLayout(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
    }


}

