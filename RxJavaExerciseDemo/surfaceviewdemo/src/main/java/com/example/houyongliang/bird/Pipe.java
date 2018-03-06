package com.example.houyongliang.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.Random;

/**
 * Created by houyongliang on 2018/2/27.
 * 绘制管道
 */

public class Pipe {
    /**
     * 上下管道间的距离
     */
    private static final float RADIO_BETWEEN_UP_DOWN = 1 / 5f;
    /**
     * 上管道的最大高度
     */
    private static final float RADIO_MAX_HEIGHT = 2 / 5f;
    /**
     * 上管道的最小高度
     */
    private static final float RADIO_MIN_HEIGHT = 1 / 5f;
    /**
     * 管道的横坐标
     */
    private int x;
    /**
     * 上管道的高度
     */
    private int height;
    /**
     * 上下管道间的距离
     */
    private int margin;
    /**
     * 上管道图片
     */
    private Bitmap mTop;
    /**
     * 下管道的图片
     */
    private Bitmap mBottom;
    private static Random random = new Random();//随机数

    //构造方法
    public Pipe(Context context, int gameWidth, int gameHeight, Bitmap top,
                Bitmap bottom) {
        //间距
        margin = (int) (gameHeight * RADIO_BETWEEN_UP_DOWN);
        //默认从最左边开始
        x = gameWidth;

        mTop = top;
        mBottom = bottom;
        randomHeight(gameHeight);//生成一个随机高度

    }

    /**
     * 随机生成一个随机高度
     * 获取在最大值和最小值 范围内的随机数
     *
     * @param gameHeight
     */

    private void randomHeight(int gameHeight) {
        height = random.nextInt((int) (gameHeight * (RADIO_MAX_HEIGHT - RADIO_MIN_HEIGHT)));
        height = height + (int) (gameHeight * (RADIO_MIN_HEIGHT));
    }

    public void drew(Canvas canvas, RectF rect) {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        //移动到指定位置
        //rect 为整个管道 ，假设 完整管道为 100，需要绘制 20 ，则需要向上偏移 80
        canvas.translate(x, -(rect.bottom - height));
        canvas.drawBitmap(mTop, null, rect, null);
        //下管道 偏移量为 上管道高度 +margin
        canvas.translate(0, (rect.bottom - height) + height + margin);
        canvas.drawBitmap(mBottom, null, rect, null);
        canvas.restore();//采用原有的资源位置等信息 即 每次都复原
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    /**
     * 判断和鸟是否触碰
     *
     * @param mBird
     * @return
     */
    public boolean touchBird(Bird mBird) {
        /**
         * 如果bird已经触碰到管道
         */
        if (mBird.getX() + mBird.getWidth() > x
                && (mBird.getY() < height || mBird.getY() + mBird.getHeight() > height
                + margin)) {
            return true;
        }
        return false;

    }

}
