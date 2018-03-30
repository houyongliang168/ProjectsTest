package com.example.houyongliang.broadcastdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.houyongliang.broadcastdemo.Constant.ACTION_SHOW;
import static com.example.houyongliang.broadcastdemo.Constant.PERM_PRIVATE;

public class MainActivity extends AppCompatActivity implements NetBreadCastReceiver.OnNetChangeListener{
    private String TAG=MainActivity.class.getSimpleName();
    OwnerBroadcastReceiver ownerBroadcastReceiver;
    private NetBreadCastReceiver netBreadCastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"ASKDFJLAK");
        /*广播代码*/
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netBreadCastReceiver = new NetBreadCastReceiver(this);
        registerReceiver(netBreadCastReceiver,filter);


        IntentFilter filter1 = new IntentFilter();
        filter1.addAction(ACTION_SHOW);

        ownerBroadcastReceiver = new OwnerBroadcastReceiver();
        registerReceiver(ownerBroadcastReceiver,filter1,PERM_PRIVATE,null);
//        registerReceiver(ownerBroadcastReceiver,filter1);

//        sendBroadcast(new Intent(ACTION_SHOW));
        sendBroadcast(new Intent(ACTION_SHOW),PERM_PRIVATE);

        /*服务代码*/
        startService(PollService.newIntent(this));


    }
    @Override
    public void onWifi() {
        Log.e(TAG,"wifi");
    }

    @Override
    public void onMobile() {
        Log.e(TAG,"onMobile");
    }

    @Override
    public void onDisConnect() {
        Log.e(TAG,"onDisConnect");
    }

//    @Override
//    public void onNoAvailable() {
//        Log.e(TAG,"onNoAvailable");
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(netBreadCastReceiver);
        unregisterReceiver(ownerBroadcastReceiver);
    }
}
