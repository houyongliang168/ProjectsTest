package com.example.houyongliang.broadcastdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by houyongliang on 2018/3/29 16:29.
 * Function(功能):
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
