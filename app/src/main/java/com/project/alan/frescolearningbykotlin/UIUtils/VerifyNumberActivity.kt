package com.project.alan.frescolearningbykotlin.UIUtils

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

import com.inspiration.lemoCard.stone.getStone.NumberFastAddFactory
import com.project.alan.frescolearningbykotlin.R
import kotlinx.android.synthetic.main.activity_fast_add_number.*

/**
 * Created by Alan on 2018/4/17.
 */
class FastAddNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fast_add_number)
    }

    fun addOneHundred(view: View) {
        NumberFastAddFactory.instance.addNumber(content_add_textview, 1000F, false)
    }

    fun addTwoHundred(view: View) {
        NumberFastAddFactory.instance.addNumber(content_add_textview, 2000F, false)
    }
}