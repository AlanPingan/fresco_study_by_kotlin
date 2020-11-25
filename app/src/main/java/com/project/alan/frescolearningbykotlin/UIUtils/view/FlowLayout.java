package com.project.alan.frescolearningbykotlin.UIUtils.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alan on 2020/10/14.
 */

public class FlowLayout extends ViewGroup {

    private List<Integer> lineHeights; // 保存所有行高
    private List<List<View>> allLines; // 保存所有行中所有的view
    private int mHorizontalSpacing = dp2px(16);
    private int mVerticalSpacing = dp2px(8);

    public FlowLayout(Context context) {
        super(context);
    }

    //在xml布局中通过反射调用
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置主题style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initMeasureParams() {
        lineHeights = new ArrayList<>(); // 保存所有行高
        allLines = new ArrayList<>(); // 保存所有行中所有的view
    }

    //度量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        initMeasureParams();
        //度量孩子
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();

        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);

        List<View> lineViews = new ArrayList<>(); // 保存一行中所有的view


        int lineWidthUsed = 0;//记录这一行使用了多宽的size
        int lineHeight = 0;//记录一行的行高

        int parentNeedWidth = 0;
        int parentNeedHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLP = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childLP.width);
            int childHeightMeasureSpec = getChildMeasureSpec(widthMeasureSpec, paddingTop + paddingBottom, childLP.height);
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            //获取子View的高宽
            int childMeasureWidth = childView.getMeasuredWidth();
            int childMeasureHeight = childView.getMeasuredHeight();


            //通过宽度来判断是否需要换行，通过换行后的每行的行高来获取整个viewgroup的行高
            //如果需要换行
            if (childMeasureWidth + lineWidthUsed + mHorizontalSpacing > selfWidth) {
                allLines.add(lineViews);
                lineHeights.add(lineHeight);

                //一旦换行，我们就可以判断当前行的高和宽了，所以此时要记录下来
                parentNeedHeight = lineHeight + parentNeedHeight + mVerticalSpacing;
                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed + mHorizontalSpacing);

                lineViews = new ArrayList<>();
                lineWidthUsed = 0;
                lineHeight = 0;
            }

            //view 是分行layout的，所以要记录每一行有哪些view，这样可以方便layout布局
            lineViews.add(childView);
            lineWidthUsed = lineWidthUsed + childMeasureWidth + mHorizontalSpacing;
            lineHeight = Math.max(lineHeight, childMeasureHeight);
            //最后一行处理
            if (i == childCount - 1 && lineWidthUsed <= selfWidth) {
                allLines.add(lineViews);
                lineHeights.add(lineHeight);
                parentNeedHeight = lineHeight + parentNeedHeight + mVerticalSpacing;
                parentNeedWidth = Math.max(parentNeedWidth, lineWidthUsed);
            }
        }
        //根据子view的度量结果，来重新度量自己viewgroup
        //作为一个viewgroup，它自己也是一个view，它的大小也需要根据父亲给它提供的高宽来度量
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int realWidth = (widthMode == MeasureSpec.EXACTLY) ? selfWidth : parentNeedWidth;
        int realHeight = (heightMode == MeasureSpec.EXACTLY) ? selfHeight : parentNeedHeight;

        //度量自己
        setMeasuredDimension(realWidth, realHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int lineCount = allLines.size(); //获取行数

        int curL = getPaddingLeft();
        int curT = getPaddingTop();
        for (int i = 0; i < lineCount; i++) {
            List<View> lineView = allLines.get(i);
            int lineHeight = lineHeights.get(i);
            for (int j = 0; j < lineView.size(); j++) {
                View view = lineView.get(j);
                int left = curL;
                int top = curT;
                int right = curL + view.getMeasuredWidth();
                int bottom = curL + view.getMeasuredHeight();
                view.layout(left, top, right, bottom);
                curL = right + mHorizontalSpacing;
            }
            curL = getPaddingLeft();
            curT = curT + lineHeight + mVerticalSpacing;
        }
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
