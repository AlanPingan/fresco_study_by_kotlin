package com.project.alan.frescolearningbykotlin.UIUtils

import  android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.daoba.network.NetWorkApi
import com.project.alan.frescolearningbykotlin.api.NewsChannelBean
import com.daoba.network.observer.BaseObserver
import com.project.alan.frescolearningbykotlin.R
import com.project.alan.frescolearningbykotlin.UIUtils.view.LoadingDrawable
import com.project.alan.frescolearningbykotlin.api.NewApiInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fish.*
import kotlinx.android.synthetic.main.activity_test_network.*
import java.net.NetworkInterface

/**
 * Created by Alan on 2018/4/17.
 */
class TestNetWorkActivity : AppCompatActivity() {

    private var loadingDrawable: LoadingDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_network)
        click_network.setOnClickListener {
            NetWorkApi.getService(NewApiInterface::class.java)
                    .newsChannels
                    .compose(NetWorkApi.applySchedulers(object : BaseObserver<NewsChannelBean>() {
                        override fun onSuccess(t: NewsChannelBean?) {
                            println(NetWorkApi.TAG + t?.showapiResBody?.totalNum)
                        }

                        override fun onFailure(e: Throwable?) {
                        }
                    }))

        }
        fish_imageview.setImageDrawable(loadingDrawable)
    }


}