package com.example.houyongliang.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.example.houyongliang.surfaceviewdemo.R;

/**
 * Created by houyongliang on 2018/2/28.
 */

public class Nums {

    /**
     * 单个数字的高度的1/15
     */
    private static final float RADIO_SINGLE_NUM_HEIGHT = 1 / 15f;
    /**
     * 单个数字的宽度
     */
    private int mSingleGradeWidth;
    /**
     * 单个数字的高度
     */
    private int mSingleGradeHeight;

    private Bitmap[] mBitmap;

    private RectF rect=new RectF();

    private int mGrade = 0;//等级
    private int gameWidth;
    private int gameHeight;

    /**
     * 分数
     */
    private final int[] mNums = new int[]{R.drawable.n0, R.drawable.n1,
            R.drawable.n2, R.drawable.n3, R.drawable.n4, R.drawable.n5,
            R.drawable.n6, R.drawable.n7, R.drawable.n8, R.drawable.n9};
    private Bitmap[] mNumBitmap;

    //构造方法
    public Nums(Context context, int gameWidth, int gameHeight) {
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        initBitmaps(context);
        //高度
        mSingleGradeHeight = (int) (gameHeight * RADIO_SINGLE_NUM_HEIGHT);
        //宽度
        mSingleGradeWidth = mSingleGradeWidth = (int) (mSingleGradeHeight * 1.0f
                / mNumBitmap[0].getHeight() * mNumBitmap[0].getWidth());
        Log.e("tag","mSingleGradeWidth:"+mSingleGradeWidth+"mSingleGradeHeight:"+mSingleGradeHeight);
        rect.set(0, 0, mSingleGradeWidth, mSingleGradeHeight);
    }

    /**
     * 初始化图片
     */
    private void initBitmaps(Context context) {
        mNumBitmap = new Bitmap[mNums.length];
        for (int i = 0; i < mNumBitmap.length; i++) {
            mNumBitmap[i] = Util.loadImageByResId(context, mNums[i]);
        }
    }

    public void draw(Canvas canvas) {
        String grade = mGrade + "";
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
//        Log.e("tag","grade.length:"+ grade.length());
        canvas.translate(gameWidth / 2 - grade.length() * mSingleGradeWidth / 2,
                1f / 8 * gameHeight);
        for (int i = 0; i < grade.length(); i++) {
            String numStr = grade.substring(i, i + 1);
            int num = Integer.valueOf(numStr);
            canvas.drawBitmap(mNumBitmap[num], null, rect, null);
            canvas.translate(mSingleGradeWidth, 0);
        }
        canvas.restore();
    }

    public void setGrade(int grade) {
        mGrade = grade;
    }

    public int getGrade() {
        return mGrade;
    }

}
