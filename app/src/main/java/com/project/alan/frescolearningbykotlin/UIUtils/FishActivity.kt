package com.project.alan.frescolearningbykotlin.UIUtils

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView

import com.inspiration.lemoCard.stone.getStone.NumberFastAddFactory
import com.project.alan.frescolearningbykotlin.R
import com.project.alan.frescolearningbykotlin.UIUtils.tools.DeviceUtils
import com.project.alan.frescolearningbykotlin.UIUtils.view.FishDrawable
import com.project.alan.frescolearningbykotlin.UIUtils.view.LoadingDrawable
import kotlinx.android.synthetic.main.activity_fast_add_number.*
import kotlinx.android.synthetic.main.activity_fish.*

/**
 * Created by Alan on 2018/4/17.
 */
class FishActivity : AppCompatActivity() {

    private var loadingDrawable: LoadingDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fish)
        initDrawable()
        fish_imageview.setImageDrawable(loadingDrawable)
    }

    private fun initDrawable() {
        loadingDrawable = LoadingDrawable(DeviceUtils.getScreenWidth(applicationContext), 40.0,
                resources.getColor(R.color.colorAccent), resources.getColor(R.color.colorPrimary))
        loadingDrawable!!.startAnimator()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        loadingDrawable!!.stopAnimator()
    }


}