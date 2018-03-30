package com.example.houyongliang.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houyongliang on 2018/3/28 17:24.
 * Function(功能): 由于 7.0 yishang
 */

public class NetBreadCastReceiver2 extends BroadcastReceiver {
    public static final int WIFI = 100;
    public static final int MOBIO = 101;
    public static final int DISCONNECT = 102;
    public static final int NOAVAILABLE = 103;
    public static final int BLUETOOTH = 104;
    private List<OnNetChangeListener> list;
    private static NetBreadCastReceiver2 defaultInstance;

    public NetBreadCastReceiver2(OnNetChangeListener mListener) {
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(mListener);
    }

    public NetBreadCastReceiver2() {

    }

    public static NetBreadCastReceiver2 getDefault() {
        if (defaultInstance == null) {
            synchronized (NetBreadCastReceiver2.class) {
                if (defaultInstance == null) {
                    defaultInstance = new NetBreadCastReceiver2();
                }
            }
        }
        return defaultInstance;
    }

    public void addObserver(OnNetChangeListener mListener) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (list.contains(mListener)) {
            return;
        }
        list.add(mListener);
    }

    public void removeObserver(OnNetChangeListener mListener) {
        if (list == null) {
            return;
        }
        if (list.contains(mListener)) {
            list.remove(mListener);
        }
    }


    private OnNetChangeListener listener;
    private int STATE = NOAVAILABLE;//默认不连接

    @Override
    public void onReceive(Context context, Intent intent) {
        if (list == null) {
            return;
        }
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() ) {//WIFI已连接
                if (STATE != WIFI) {
                    for (OnNetChangeListener onNetChangeListener : list) {
                        onNetChangeListener.onWifi();
                    }
                    STATE = WIFI;
                }
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {//移动网络连接
                if (STATE != MOBIO) {
                    for (OnNetChangeListener onNetChangeListener : list) {
                        onNetChangeListener.onMobile();
                    }
                    STATE = MOBIO;
                }
            }  else {
                if (STATE != DISCONNECT) {
                    for (OnNetChangeListener onNetChangeListener : list) {
                        onNetChangeListener.onDisConnect();
                    }
                    STATE = DISCONNECT;
                }
            }

        }else {
           //"API level 大于21"
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();

            if(networks==null||networks.length==0){

                if (STATE != DISCONNECT) {
                    for (OnNetChangeListener onNetChangeListener : list) {
                        onNetChangeListener.onDisConnect();
                    }
                    STATE = DISCONNECT;
                }

            }
            //通过循环将网络信息逐个取出来
            for (int i=0; i < networks.length; i++){
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);

                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI && networkInfo.isConnected()) {   /*WIFI */
                    if (STATE != WIFI) {
                        for (OnNetChangeListener onNetChangeListener : list) {
                            onNetChangeListener.onWifi();
                        }
                        STATE = WIFI;
                    }
                    return;
                }else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE&& networkInfo.isConnected()){  //MOBILE
                    if (STATE != MOBIO) {
                        for (OnNetChangeListener onNetChangeListener : list) {
                            onNetChangeListener.onMobile();
                        }
                        STATE = MOBIO;
                    }
                    return;
                }


                    if (STATE != DISCONNECT) {
                        for (OnNetChangeListener onNetChangeListener : list) {
                            onNetChangeListener.onDisConnect();
                        }
                        STATE = DISCONNECT;
                    }




            }

        }
    }




    public interface OnNetChangeListener {
        // wifi
        void onWifi();
        // 手机
        void onMobile();

        // 网络断开
        void onDisConnect();

//        // 网路不可用
//        void onNoAvailable();
        /*蓝牙*/
//        void onBluetooth();

    }
}

