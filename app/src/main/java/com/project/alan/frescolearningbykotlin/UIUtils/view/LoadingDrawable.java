package com.project.alan.frescolearningbykotlin.UIUtils.view;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.LinearInterpolator;

/**
 * Created by Alan on 2020/10/19.
 */

public class LoadingDrawable extends Drawable {
    private double unitHeight;
    private double unitSize;
    private int colorA;
    private int colorB;
    private int windowSize = 0;
    private float offset = 0;
    public ValueAnimator mValueAnimator;
    Path path = new Path();

//colorA、B为两种传入的颜色

    public LoadingDrawable(int windowSize, double unitHeight, int colorA, int colorB) {
        this.windowSize = windowSize;
        this.unitHeight = unitHeight;
        this.colorA = colorA;
        this.colorB = colorB;
        this.unitSize = Math.sqrt(unitHeight * unitHeight / 3);
    }


    @Override
    public void draw(@NonNull Canvas canvas) {

        //此方法设置path，path为平行四边形
        Paint paint = new Paint();
        for (int i = 0; i < getShapeNumber(); i++) {
            if ((i + 1) % 2 == 1) {
                paint.setColor(colorA);
            } else {
                paint.setColor(colorB);
            }
            path.reset();
            path.moveTo((float) (offset + windowSize - unitSize * i), 0);
            path.lineTo((float) (offset + windowSize - unitSize - unitSize * i), 0);
            path.lineTo((float) (offset + windowSize - 2 * unitSize - unitSize * i), (float) unitHeight);
            path.lineTo((float) (offset + windowSize - unitSize - unitSize * i), (float) unitHeight);
            canvas.drawPath(path, paint);
        }
    }

    @Override
    public void setAlpha(int alpha) {

    }


    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.OPAQUE;
    }

    //计算要绘制的平行四边形的个数
    private int getShapeNumber() {
        return (int) (2 * (windowSize / unitSize));
    }



    private void initValueAnimator() {
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofFloat(0, 4 * (float) unitSize);
            mValueAnimator.setInterpolator(new LinearInterpolator());
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    offset = (float) animation.getAnimatedValue();
                    invalidateSelf();
                }
            });
            mValueAnimator.setDuration(1000);
            mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        }
    }

    public void startAnimator() {
        initValueAnimator();
        mValueAnimator.start();
    }

    public void stopAnimator() {
        if (mValueAnimator == null) {
            return;
        } else {
            mValueAnimator.cancel();
        }
    }

}
