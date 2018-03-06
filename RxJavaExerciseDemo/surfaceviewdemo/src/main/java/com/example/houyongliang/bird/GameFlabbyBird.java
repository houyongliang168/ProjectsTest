package com.example.houyongliang.bird;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.houyongliang.surfaceviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyongliang on 2018/2/27.
 */

public class GameFlabbyBird extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    /**
     * 当前view 的尺寸
     */

    private SurfaceHolder mHolder;
    /**
     * 与SurfaceHolder绑定的Canvas
     */
    private Canvas mCanvas;//画布
    /**
     * 用于绘制的线程
     */
    private Thread t;
    /**
     * 线程的控制开关
     */
    private boolean isRunning;

    /**
     * 当前View的尺寸
     */
    private int mWidth;
    private int mHeight;
    private RectF mGamePanelRect = new RectF();
    /**
     * 背景
     */
    private Bitmap mBg;

    /**
     * 鸟相关
     *
     * @param context
     */
    private Bird mBird;
    private Bitmap mBirdBitmap;

    /**
     * 地板
     */
    private Floor mFloor;
    private Bitmap mFloorBg;
    private int mSpeed;
    private Paint mPaint;

    /**
     * 管道相关
     *
     * @param context
     */
    private Bitmap mPipeTop;
    private Bitmap mPipeBottom;
    private RectF mPipeRect;
    private int mPipeWidth;

    /**
     * 管道的宽度
     *
     * @param context
     */
    private static final int PIPE_WIDTH = 60;
    private List<Pipe> mPipes = new ArrayList<>();

    private Context context;
    //分数相关
    private Nums mNums;

    /**
     * 游戏的状态
     */
    private enum GameStatus {
        WAITTING, RUNNING, STOP;
    }

    //记录游戏的状态 初始状态
    private GameStatus mStatus = GameStatus.WAITTING;

    /**
     * 触摸上升的距离  上升为负值
     */
    private static final int TOUCH_UP_SIZE = -16;

    /**
     * 将上升的距离转化为px；这里多存储一个变量，变量在run中计算
     */
    private final int mBirdsUpDirs = Util.dip2px(getContext(), TOUCH_UP_SIZE);
    /**
     * 鸟自动下落的距离
     */
    private final int mAutoDownSpeed = Util.dip2px(getContext(), 2);
    private int mTmpBirdDis;//mTmpBirdDis即为每次用户点击时，鸟上升的距离，接下来会实现。

    /**
     * 两个管道间的距离
     */
    private final int PIPE_DIS_BETWEEN_TWO = Util.dip2px(getContext(), 300);
    /**
     * 记录移动的距离，达到 PIPE_DIS_BETWEEN_TWO 则生成一个管道
     */
    private int mTmpMoveDistance;

    /**
     * 记录需要移除的管道
     */
    private List<Pipe> mNeedRemovePipe = new ArrayList<>();
    private int mGrade = 0;
    private int mRemovedPipe=0;//记录分数
    public GameFlabbyBird(Context context) {
        this(context, null);
    }

    public GameFlabbyBird(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mHolder = getHolder();
        mHolder.addCallback(this);

        setZOrderOnTop(true);//SurfaceView置于Activity显示窗口的最顶层才能正常显示
        mHolder.setFormat(PixelFormat.TRANSLUCENT);   // 设置画布 背景透明

        //设置可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        //设置常亮
        this.setKeepScreenOn(true);
        // 初始化绘制圆弧的画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setDither(true);//防抖动

        // 初始化速度
        mSpeed = Util.dip2px(getContext(), 2);

        //初始化 管道
        mPipeWidth = Util.dip2px(getContext(), PIPE_WIDTH);
        //初始化背景
        initBitmaps();
    }

    private void initBitmaps() {
        mBg = Util.loadImageByResId(context, R.drawable.bg1);
        mBirdBitmap = Util.loadImageByResId(context, R.drawable.b1);
        mFloorBg = Util.loadImageByResId(context, R.drawable.floor_bg2);
        mPipeTop = Util.loadImageByResId(context, R.drawable.g2);
        mPipeBottom = Util.loadImageByResId(context, R.drawable.g1);

    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        //开启线程
        isRunning = true;
        t = new Thread(this);
        t.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // 通知关闭线程
        isRunning = false;
//        t.interrupt();//中断线程


    }

    @Override
    public void run() {
        while (isRunning) {
            logic();
            draw();
        }
    }

    /**
     * 处理逻辑上的计算
     */
    private void logic() {
        switch (mStatus) {
            case RUNNING:
                mGrade = 0;
                // 更新我们地板绘制的x坐标，地板移动
                mFloor.setX(mFloor.getX() - mSpeed);
                logicPipes();
                //默认下落，点击时瞬间上升
                mTmpBirdDis += mAutoDownSpeed;
                mBird.setY(mBird.getY() + mTmpBirdDis);
                // 计算分数
                mGrade += mRemovedPipe;
                for (Pipe pipe : mPipes) {
                    if (pipe.getX() + mPipeWidth < mBird.getX()) {
                        mGrade++;
                    }
                }
                mNums.setGrade(mGrade);
                checkGameOver();

                break;
            case STOP://鸟落下
                // 如果鸟还在空中，先让它掉下来
                if (mBird.getY() < mFloor.getY() - mBird.getHeight()) {
                    mTmpBirdDis += mAutoDownSpeed;
                    mBird.setY(mBird.getY() + mTmpBirdDis);
                } else {
                    mStatus = GameStatus.WAITTING;
                    initPos();
                }


                break;
            default:
                break;

        }
    }

    /**
     * 重置鸟的位置等数据
     */

    private void initPos() {

        mPipes.clear();
        mNeedRemovePipe.clear();
        //重置鸟的位置
        mBird.setY(mHeight * 2 / 3);
        //重置下落速度
        mTmpBirdDis = 0;
        mTmpMoveDistance = 0;
        mRemovedPipe=0;
    }

    /**
     * 管道的计算
     */
    private void logicPipes() {

        // 管道移动
        for (Pipe pipe : mPipes) {
            if (pipe.getX() < -mPipeWidth) {//已经移除屏幕
                mNeedRemovePipe.add(pipe);
                mRemovedPipe++;
                continue;
            }

            pipe.setX(pipe.getX() - mSpeed);
        }
        //移除管道
        mPipes.removeAll(mNeedRemovePipe);

        // 管道
        mTmpMoveDistance += mSpeed;
        //生成一个管道
        if (mTmpMoveDistance >= PIPE_DIS_BETWEEN_TWO) {
            Pipe pipe = new Pipe(getContext(), getWidth(), getHeight(), mPipeTop, mPipeBottom);
            mPipes.add(pipe);
            mTmpMoveDistance = 0;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            switch (mStatus) {
                case WAITTING:
                    mStatus = GameStatus.RUNNING;
                    break;
                case RUNNING:
                    mTmpBirdDis = mBirdsUpDirs;
                    break;

            }
        }
        return true;
    }

    private void draw() {
        try {
            // 获得canvas
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                // drawSomething..
                drawBg();
                drawBird();

                drawPipes();
                drawFloor();
                drawGrades();//画分数
            }
        } catch (Exception e) {
        } finally {
            if (mCanvas != null)
                mHolder.unlockCanvasAndPost(mCanvas);//显示像素  显示图片
            // 更新我们地板绘制的x坐标
//            Log.e("hyl","mFloor.getX():"+mFloor.getX());
//            Log.e("hyl","mFloor.getX() - mSpeed:"+(mFloor.getX() - mSpeed));

        }
    }

    /**
     * 绘制分数
     */
    private void drawGrades() {
        mNums.draw(mCanvas);

    }

    /**
     * 绘制管道
     */
    private void drawPipes() {
        for (Pipe pipe : mPipes) {
//            pipe.setX(pipe.getX() - mSpeed);
            pipe.drew(mCanvas, mPipeRect);
        }

    }

    private void drawFloor() {
        mFloor.draw(mCanvas, mPaint);
//        mFloor.setX(mFloor.getX() - mSpeed);
    }

    /**
     * 绘制背景
     */
    private void drawBg() {
        mCanvas.drawBitmap(mBg, null, mGamePanelRect, null);
    }

    /**
     * 绘制小鸟
     */
    private void drawBird() {
        mBird.draw(mCanvas);
    }


    /**
     * 初始化尺寸相关
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        Log.e("tag0", "w:" + w + "h:" + h);
        mGamePanelRect.set(0, 0, w, h);
        // 初始化mBird
        mBird = new Bird(getContext(), mWidth, mHeight, mBirdBitmap);
        // 初始化地板
        mFloor = new Floor(mWidth, mHeight, mFloorBg);
        // 初始化管道范围
        mPipeRect = new RectF(0, 0, mPipeWidth, mHeight);
        // 初始化管道范围
        Pipe pipe = new Pipe(getContext(), w, h, mPipeTop, mPipeBottom);
        mPipes.add(pipe);
//        Log.e("onSizeChanged:","onSizeChanged:"+mPipes.size());
        //初始化分数
        Log.e("tag1", "w:" + w + "h:" + h);
        mNums = new Nums(getContext(), mWidth, mHeight);


    }


    private void checkGameOver() {
        //如果碰到地板 gg
        if (mBird.getY() > mFloor.getY() + mBird.getHeight()) {
            mStatus = GameStatus.STOP;
        }
        //如果撞到管道 gg
        for (Pipe wall : mPipes) {
            if (wall.getX() + mBird.getWidth() < mBird.getX()) {
                continue;
            }
            if (wall.touchBird(mBird)) {
                mStatus = GameStatus.STOP;
                break;
            }

        }


    }
}
