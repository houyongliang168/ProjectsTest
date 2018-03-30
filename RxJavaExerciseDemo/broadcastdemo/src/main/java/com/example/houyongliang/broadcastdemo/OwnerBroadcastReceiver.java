package com.example.houyongliang.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class OwnerBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接受阿凡达发送了", Toast.LENGTH_SHORT).show();
        Log.e("adf","接受了");
    }
}
