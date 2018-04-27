package com.project.alan.frescolearningbykotlin.scorll

import android.content.Context
import android.view.Gravity
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.Scroller
import android.widget.TextView

/**
 * Created by Alan on 2018/4/27.
 */
class ScrollLayout(context: Context) : FrameLayout(context) {
    private var lastX = 0
    private var lastY = 0
    private var viewDownY = 0
    private var viewDownX = 0
    private var scroller = Scroller(context)

    init {
        addTextView()
    }

    private fun addTextView() {
        var textView = TextView(context)
        textView.text = "拖动我，如果放开可以回到原来位置！"
        var params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        textView.layoutParams = params
        addView(textView)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var x = event.rawX.toInt()
        var y = event.rawY.toInt()
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //在down 的时候判断动画是否完成，没有完成就停止动画
                if (!scroller.isFinished) {
                    scroller.abortAnimation()
                }
                viewDownX = scrollX
                viewDownY = scrollY
                lastX = x
                lastY = y
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = x - lastX
                var dy = y - lastY
                scrollBy(-dx, -dy)
                lastX = x
                lastY = y
                return true
            }
            MotionEvent.ACTION_CANCEL
                    and MotionEvent.ACTION_UP -> {
                // 在cancel和up的时候，开始返回动画，
                scroller.startScroll(scrollX, scrollY, -scrollX, -scrollY, 500)
                invalidate()
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    // 重写该函数用于计算每次滑动的距离，并滑动
    override fun computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.currX, scroller.currY)
            invalidate()
        }
    }

}