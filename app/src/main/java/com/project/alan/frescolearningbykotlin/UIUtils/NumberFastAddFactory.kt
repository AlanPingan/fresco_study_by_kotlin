package com.inspiration.lemoCard.stone.getStone

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Log
import android.widget.TextView
import com.project.alan.frescolearningbykotlin.R
import java.text.DecimalFormat

/**
 * Created by Alan on 2018/4/14.
 */
class NumberFastAddFactory {
    private val TAG = "NumberFastAddFactory"

    fun addNumber(textView: TextView, endNumber: Float, isDoAlphaAnim: Boolean) {

        if (isHasThisTextView(textView)) {
            textView.setTag(R.id.end_number, endNumber)
            startAddAnim(true, textView, isDoAlphaAnim)
        } else {
            textViewList.add(textView)
            textView.setTag(R.id.current_number, getCurrentNumber(textView))
            textView.setTag(R.id.end_number, endNumber)
            startAddAnim(false, textView, isDoAlphaAnim)
        }
    }

    private fun startAlphaAnim(textView: TextView) {
        var animSet = AnimatorSet()
        var disPlayAlphaAnim = ObjectAnimator.ofFloat(textView, "alpha", 0F, 1F)
        disPlayAlphaAnim.duration = ALPHA_ANIM_TIME
        var hideAlphaAnim = ObjectAnimator.ofFloat(textView, "alpha", 1F, 0F)
        hideAlphaAnim.duration = ALPHA_ANIM_TIME
        hideAlphaAnim.startDelay = ALPHA_ANIM_TIME
        animSet.play(disPlayAlphaAnim).before(hideAlphaAnim)
        animSet.start()
    }


    private fun getCurrentNumber(textView: TextView): Float {
        //如果为第一次做动画从0开始加
        if (textView.getTag(R.id.current_number) == null) {
            return DEFAULT_NUMBER_TIME
        }
        return textView.getTag(R.id.current_number) as Float
    }

    private fun startAddAnim(isDoingAnim: Boolean, textView: TextView, isDoAlphaAnim: Boolean) {
        if (isDoingAnim) {
            (textView.getTag(R.id.current_anim) as ValueAnimator)?.cancel()
        }
        Log.d(TAG, "当前值是：${textView.getTag(R.id.current_number)}结尾值：${textView.getTag(R.id.end_number)}")

        var addAnim = ValueAnimator.ofFloat(textView.getTag(R.id.current_number) as Float, textView.getTag(R.id.end_number) as Float)
        addAnim.duration = ADD_NUMBER_TIME
        addAnim.addUpdateListener { animation ->
            textView.text = DecimalFormat("###,###,###.##").format(animation?.animatedValue)
            textView.setTag(R.id.current_number, animation?.animatedValue)
            Log.d(TAG, "当前的设置值是：${DecimalFormat("###,###,###.##").format(animation?.animatedValue)}")

        }
        addAnim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {
                textView.setTag(R.id.current_anim, null)
                textViewList.remove(textView)
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                if (isDoAlphaAnim) {
                    startAlphaAnim(textView)
                }
            }
        })
        textView.setTag(R.id.current_anim, addAnim)
        addAnim.start()

    }


    private fun isHasThisTextView(textView: TextView): Boolean {
        return textViewList.contains(textView)
    }


    companion object {
        var instance = NumberFastAddFactory()
        var textViewList = ArrayList<TextView>()
        val ADD_NUMBER_TIME = 150L
        val ALPHA_ANIM_TIME = 50L
        val DEFAULT_NUMBER_TIME: Float = 0F
    }
}