package com.project.alan.frescolearningbykotlin.UIUtils.view;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.logging.Handler;

/**
 * Created by Alan on 2020/10/19.
 */

public class FishDrawable extends Drawable {

    private Path path;
    private Paint paint;
    public static final int HEAD_RADIUS = 50;

    private final static int OTHER_ALPHA = 110;
    private final static int BODY_ALPHA = 160;
    private PointF middlePoint;
    private float fishMainAngle = 0;

    private final static float body_length = 3.2f * HEAD_RADIUS;
    //寻找鱼鳍的线长
    private final static float FIND_FINS_LENGTH = 0.9f * HEAD_RADIUS;

    private final static float FINS_LENGTH = 1.3f * HEAD_RADIUS;

    public FishDrawable() {
        init();
    }

    private void init() {
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);// 抗锯齿
        paint.setDither(true);//防抖
        paint.setStyle(Paint.Style.FILL);// 画笔填充类型

        paint.setARGB(OTHER_ALPHA, 244, 92, 71); //设置画笔颜色
        middlePoint = new PointF(4.18f * HEAD_RADIUS, 4.18f * HEAD_RADIUS);
    }


    //类似自定义view里面的onDraw()
    @Override
    public void draw(@NonNull Canvas canvas) {
        float fishAngle = fishMainAngle;
        PointF headPointF = calculatPoint(middlePoint, body_length / 2, fishAngle);
        canvas.drawCircle(headPointF.x, headPointF.y, HEAD_RADIUS, paint);
        //绘制右鱼鳍
        PointF rightPinsStartPoint = calculatPoint(headPointF, FIND_FINS_LENGTH, fishAngle - 110);
        makeFins(canvas, rightPinsStartPoint, fishAngle, true);
        //绘制左鱼鳍
        PointF leftPinsStartPoint = calculatPoint(headPointF, FIND_FINS_LENGTH, fishAngle + 110);
        makeFins(canvas, leftPinsStartPoint, fishAngle, false);
    }

    private void makeFins(Canvas canvas, PointF startPoint, float fishAngle, boolean isRight) {
        float controlAngel = 115;
        //二阶贝赛尔曲线的控制点坐标
        PointF controlPointF = calculatPoint(startPoint, 1.8f * FINS_LENGTH, isRight ?
                fishAngle - controlAngel : fishAngle + controlAngel);
        // 结束点坐标
        PointF endPointF = calculatPoint(startPoint, FINS_LENGTH, fishAngle - 180);
        path.reset();
        path.moveTo(startPoint.x, startPoint.y);
        path.quadTo(controlPointF.x, controlPointF.y, endPointF.x, endPointF.y);
        canvas.drawPath(path, paint);

    }

    /*
     * @param startPint 起始点坐标
     * @param length 两点之间的长度
     * @param angle 两点连线和x的夹角
     * @return 点的坐标
     */
    public static PointF calculatPoint(PointF startPint, float length, float angle) {
        float deltaX = (float) (Math.cos(Math.toRadians(angle)) * length);
        // 数学的坐标系和Android的坐标的差异，要将y乘-1
        float deltaY = (float) (Math.sin(Math.toRadians(angle - 180)) * length);
        return new PointF(startPint.x + deltaX, startPint.y + deltaY);
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) (8.38f * HEAD_RADIUS);
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) (8.38f * HEAD_RADIUS);
    }
}
