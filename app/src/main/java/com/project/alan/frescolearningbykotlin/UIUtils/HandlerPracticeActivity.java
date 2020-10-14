package com.project.alan.frescolearningbykotlin.UIUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.project.alan.frescolearningbykotlin.R;

/**
 * Created by Alan on 2020/10/14.
 */

public class HandlerPracticeActivity extends AppCompatActivity {
    private Handler handler0;
    private Handler handler1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_handler_test);
    }


    private Handler createHandlerInThread() {
        HandlerThread handlerThread = new HandlerThread("子线程");
        handlerThread.start();
        final Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.d("--->", "线程" + Thread.currentThread().getName());
            }
        };
        return handler;
    }

    public void testCreateThread(View view) {
        Handler handler = createHandlerInThread();
        handler.sendEmptyMessage(100);
    }

    // 在子线程中回调主线程
    private void testSonToMain() {
        new Handler(getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "son to main", Toast.LENGTH_LONG).show();
            }
        });
    }


}
