package com.example.houyongliang.typesviews;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 定义多状态 view  的基本功能
 *
 * 状态
 * 容器
 * view 和 状态 添加 到容器方法
 *
 * 展示 success 后页面的方法
 *
 * 两种监听 ： 扩充和 重新加载
 */


public class MultiStateView extends FrameLayout {

    private static final String TAG = MultiStateView.class.getSimpleName();

    public static final int STATE_CONTENT = 10001;
    public static final int STATE_LOADING = 10002;
    public static final int STATE_EMPTY = 10003;
    public static final int STATE_FAIL = 10004;
    public static final int STATE_NONET = 10005;
    private SparseArray<View> mStateViewArray = new SparseArray<>(); /*存放view */
    private SparseIntArray mLayoutIDArray = new SparseIntArray();/*存放layout 的*/
    private View mContentView;
    private int mCurrentState = STATE_CONTENT;
    private OnInflateListener mOnInflateListener;
    private onReLoadlistener mOnReLoadlistener;

    public MultiStateView(Context context) {
        this(context, null);
    }

    public MultiStateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /*添加view */
    @Override
    public void addView(View child) {
        validContentView(child);
        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        validContentView(child);
        super.addView(child, index);
    }

    @Override
    public void addView(View child, int width, int height) {
        validContentView(child);
        super.addView(child, width, height);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        validContentView(child);
        super.addView(child, index, params);
    }

    /**
     * 改变视图状态
     *
     * @param state 状态类型
     */
    public void setViewState(int state) {
        if (getCurrentView() == null) {
            return;
        }
        if (state != mCurrentState) {
            View view = getView(state);

            getCurrentView().setVisibility(GONE);
            mCurrentState = state;
            if (view != null) {
                view.setVisibility(VISIBLE);

            } else {
                int resLayoutID = mLayoutIDArray.get(state);
                if (resLayoutID == 0) {
                    return;
                }
                view = LayoutInflater.from(getContext()).inflate(resLayoutID, this, false);
                mStateViewArray.put(state, view);
                addView(view);
//                if (state == STATE_FAIL) {
//                    View bt = view.findViewById(R.id.retry_bt);
//                    if (bt != null) {
//                        bt.setOnClickListener(new OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if (mOnReLoadlistener != null) {
//                                    mOnReLoadlistener.onReload();
//                                    setViewState(STATE_LOADING);
//                                }
//                            }
//                        });
//                    }
//                }

                view.setVisibility(VISIBLE);
                if (mOnInflateListener != null) {
                    mOnInflateListener.onInflate(state, view);
                }

            }
        }
    }

    /**
     * 获取当前状态
     *
     * @return 状态
     */
    public int getViewState() {
        return mCurrentState;
    }

    /**
     * 获取指定状态的View
     *
     * @param state 状态类型
     * @return 指定状态的View
     */
    public View getView(int state) {
        return mStateViewArray.get(state);
    }

    /**
     * 获取当前状态的View
     *
     * @return 当前状态的View
     */
    public View getCurrentView() {
        if (mCurrentState == -1) {
            return null;
        }
        View view = getView(mCurrentState);
        if (view == null && mCurrentState == STATE_CONTENT) {
            throw new NullPointerException("content is null");
        } else if (view == null) {
            throw new NullPointerException("current state view is null, state = " + mCurrentState);
        }
        return getView(mCurrentState);
    }

    /*添加 view 的 id ,使得 view 与 status 对应*/
    public void addViewForStatus(int status, int resLayoutID) {
        mLayoutIDArray.put(status, resLayoutID);
    }

    /*设置加载更多监听*/
    public void setonReLoadlistener(onReLoadlistener onReLoadlistener) {
        mOnReLoadlistener = onReLoadlistener;
    }

    /*设置 扩充监听*/
    public void setOnInflateListener(OnInflateListener onInflateListener) {
        mOnInflateListener = onInflateListener;
    }

    /*判断是否 有  contentview  是否有 成功之后展示的view */
    private boolean isValidContentView(View view) {
        if (mContentView == null) {
            for (int i = 0; i < mStateViewArray.size(); i++) {
                if (mStateViewArray.indexOfValue(view) != -1) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 检查当前view是否为content  有就展示没有 就添加
     */
    public void validContentView(View view) {
        if (isValidContentView(view)) {
            mContentView = view;
            mStateViewArray.put(STATE_CONTENT, view);
        } else if (mCurrentState != STATE_CONTENT) {
            mContentView.setVisibility(GONE);
        }
    }

    /*扩充*/
    public interface OnInflateListener {
        void onInflate(int state, View view);
    }

    /**
     * 重新加载接口
     */
    public interface onReLoadlistener {
        void onReload();
    }
}