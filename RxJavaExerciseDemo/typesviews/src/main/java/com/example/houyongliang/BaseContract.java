package com.example.houyongliang;


import android.view.View;

/**
 * desc:
 * author: Will .
 * date: 2017/9/2 .
 */
public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {

        void attachView(T view);

        void detachView();
    }


    interface BaseView {

        //显示进度中
        void showLoading();

        //显示请求成功
        void showSuccess();

        //失败重试
        void showFaild();

        //显示当前网络不可用
        void showNoNet();

        //重试
        void onRetry();

        /*设置绑定的view 可以重写该方法 设置 不同的view*/
        View BindView();

        int bindLayout();

    }
}
