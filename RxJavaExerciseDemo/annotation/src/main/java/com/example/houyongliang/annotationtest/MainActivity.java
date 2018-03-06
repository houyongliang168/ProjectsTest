package com.example.houyongliang.annotationtest;

import android.Manifest;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;

/**
 * dependencies {
 * compile 'com.android.support:support-annotations:22.2.0'
 * }
 * 但是如果我们已经引入了appcompat则没有必要再次引用support-annotations,因为appcompat默认包含了对其引用.
 */


public class MainActivity extends AppCompatActivity {
    public float currentProgress;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        // appcompat

//        //不提示警告
//        String referrer = getIntent().getStringExtra("apps_referrer");
//        Log.e("TAG","referrer:"+referrer);
//        setReferrer(referrer);
//
//        //提示警告
//        String referrer1 = getIntent().getStringExtra("apps_referrer");
//        if (referrer1 == null) {
//            setReferrer(referrer1);
//        }

        setReferrer("1");
        setCurrentProgress(0.2f);
//        setData(new String[]{"b", "a"});
        tv.setTextColor(Color.RED);
        tv.setText("adfjhakjdfhka f");

    }

    @Nullable
    public void setReferrer(@NonNull String referrer) {
        //some code here
        Log.e("referrer", "referrer:" + referrer);
    }


    public void setCurrentProgress(@FloatRange(from = 0.0f, to = 1.0f) float progress) {
        currentProgress = progress;
        Log.e("currentProgress", "currentProgress：" + currentProgress);
    }

    private void setKey(@Size(6) String key) {
    }

    private void setData(@Size(max = 1) String[] data) {
    }

    @RequiresPermission(Manifest.permission.SET_WALLPAPER)
    public void changeWallpaper(Bitmap bitmap) throws IOException {
    }


    @CheckResult
    public String trim(String s) {
        return s.trim();
    }
}
