package com.example.houyongliang.typesviews.viewtype1;

import android.app.Application;
import android.hardware.Camera;


import com.example.houyongliang.others.CustomCallback;
import com.example.houyongliang.others.EmptyCallback;
import com.example.houyongliang.others.ErrorCallback;
import com.example.houyongliang.others.LoadingCallback;
import com.example.houyongliang.others.TimeoutCallback;
import com.example.houyongliang.typesviews.R;
import com.example.houyongliang.typesviews.viewz.LoadingAndRetryManager;
import com.kingja.loadsir.core.LoadSir;


/**
 * Created by zhy on 15/8/27.
 */
public class MyApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID = R.layout.base_retry;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID = R.layout.base_empty;

        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())//'添加各种状态页
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)//设置默认状态页
                .commit();




    }
}
