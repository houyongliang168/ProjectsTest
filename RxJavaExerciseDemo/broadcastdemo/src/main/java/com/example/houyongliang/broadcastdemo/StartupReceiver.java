package com.example.houyongliang.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by houyongliang on 2018/3/28 14:08.
 * Function(功能):
 */

public class StartupReceiver extends BroadcastReceiver {
    private static final String TAG=StartupReceiver.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"Received broadcast intent:"+intent.getAction());
    }
}
