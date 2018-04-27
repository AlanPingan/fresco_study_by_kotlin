package com.project.alan.frescolearningbykotlin.UIUtils

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.project.alan.frescolearningbykotlin.R
import com.project.alan.frescolearningbykotlin.fresco.FrescoUseActivity
import com.project.alan.frescolearningbykotlin.scorll.ScrollActivity

/**
 * Created by Alan on 2018/4/17.
 */
class UITestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_test)
    }


    fun frescoClick(view: View) {
        var intent = Intent(this, FrescoUseActivity::class.java)
        startActivity(intent)
    }

    fun fastAddNumber(view: View) {
        var intent = Intent(this, FastAddNumberActivity::class.java)
        startActivity(intent)
    }
    fun scroll(view: View) {
        var intent = Intent(this, ScrollActivity::class.java)
        startActivity(intent)
    }
}