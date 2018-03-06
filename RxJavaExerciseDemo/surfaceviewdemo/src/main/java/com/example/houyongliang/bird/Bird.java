package com.example.houyongliang.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by houyongliang on 2018/2/27.
 */

public class Bird {
    /**
     * 鸟的横坐标
     */
    private int x;
    /**
     * 鸟的纵坐标
     */
    private int y;
    /**
     * 鸟的宽度
     */
    private int mWidth;
    /**
     * 鸟的高度
     */
    private int mHeight;
    /**
     * 鸟在屏幕高度的2/3位置
     */
    private static final float RADIO_POS_HEIGHT = 2 / 3F;
    /**
     * 鸟的宽度 30p
     */
    private static final int BIRD_SIZE = 30;
    /**
     * 鸟的bitmap
     */
    private Bitmap bitmap;
    /**
     * 鸟的绘制范围
     */
    private RectF rect = new RectF();

    public Bird (Context context, int gameWith, int gameHeight, Bitmap bitmap){
       this.bitmap=bitmap;
        //鸟的位置
        x=gameWith/2-bitmap.getWidth()/2;
        y= (int) (gameHeight  *RADIO_POS_HEIGHT);
        //计算鸟的高度和宽度
        mWidth=Util.dip2px(context,BIRD_SIZE);
        mHeight= (int) (mWidth*1.0f /bitmap.getWidth()*bitmap.getHeight());
    }
    /**
     * 绘制自己
     *
     * @param canvas
     */
    public void draw(Canvas canvas) {
        rect.set(x, y, x + mWidth, y + mHeight);
        canvas.drawBitmap(bitmap, null, rect, null);

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
