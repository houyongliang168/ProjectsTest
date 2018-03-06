package com.example.houyongliang.bird;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.Log;

/**
 * Created by houyongliang on 2018/2/27.
 */

public class Floor {
    /*
    * 地板位置游戏面板高度的4/5到底部
    */
    private static final float FLOOR_Y_POS_RADIO = 4 / 5F;//height of 4/5
    /**
     * x坐标
     */
    private int x;
    /**
     * y坐标
     */
    private int y;
    /**
     * 填充物
     */
    private BitmapShader mFloorShader;
    private int mGameWidth;
    private int mGameHeight;



    public Floor(int gameWidth, int gameHeight, Bitmap floorBg){
        mGameWidth=gameWidth;
        mGameHeight=gameHeight;
        y= (int) (gameHeight*FLOOR_Y_POS_RADIO);
        //REPEAT 重复  clamp  延伸
        mFloorShader=new BitmapShader(floorBg, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
    }


    /**
     * 绘制自己
     *
     * @param mCanvas
     * @param mPaint
     */
    public void  draw(Canvas mCanvas, Paint mPaint){
        if(-x > mGameWidth){
            x=x % mGameWidth;
        }
        mCanvas.save(Canvas.MATRIX_SAVE_FLAG);//保存画布
        //移动到指定位置
        mCanvas.translate(x,y);//以此基点 画图
        mPaint.setShader(mFloorShader);
//        Log.e("hyl","-x+mGameWidth:"+(-x+mGameWidth));
//        Log.e("hyl","mGameHeight-y:"+(mGameHeight-y));
        //x 为负值
        mCanvas.drawRect(x,0,-x+mGameWidth,mGameHeight-y,mPaint);

        mCanvas.restore();
        mPaint.setShader(null);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY(){
        return y;
    }
}
