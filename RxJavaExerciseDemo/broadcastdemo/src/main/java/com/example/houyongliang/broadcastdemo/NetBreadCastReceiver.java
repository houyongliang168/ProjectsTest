package com.example.houyongliang.broadcastdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by houyongliang on 2018/3/28 17:24.
 * Function(功能): 由于 7.0 yishang
 */

public class NetBreadCastReceiver extends BroadcastReceiver {
    private static final int WIFI = 100;
    private static final int MOBIO = 101;
    private static final int DISCONNECT = 102;
    private static final int NOAVAILABLE = 103;
    private static final int BLUETOOTH = 104;


    public NetBreadCastReceiver(OnNetChangeListener mListener) {
        listener = mListener;
    }

    private OnNetChangeListener listener;
    private int STATE = NOAVAILABLE;//默认不连接

    @Override
    public void onReceive(Context context, Intent intent) {
        if (listener == null) {
            return;
        }
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//            NetworkInfo networkInfo = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (networkInfo == null) {
                /** 没有任何网络 */
                if (STATE != DISCONNECT) {
//                    listener.onNoAvailable();
                    listener.onDisConnect();
                    STATE = DISCONNECT;
                }

                return;
            }
            if (!networkInfo.isConnected()) {
                /** 网络断开或关闭 */
                if (STATE != DISCONNECT) {
                    listener.onDisConnect();
                    STATE = DISCONNECT;
                }
                return;
            }

            if (NetworkInfo.State.CONNECTED == networkInfo.getState() && networkInfo.isAvailable()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    /** wifi网络，当激活时，默认情况下，所有的数据流量将使用此连接 */
                    if (STATE != WIFI) {
                        listener.onWifi();
                        STATE = WIFI;
                    }

                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    /** 移动数据连接,不能与连接共存,如果wifi打开，则自动关闭 */
                    if (STATE != MOBIO) {
                        listener.onMobile();
                        STATE = MOBIO;
                    }

                }
//                else if(networkInfo.getType() == ConnectivityManager.TYPE_BLUETOOTH){
//                    if(STATE !=BLUETOOTH){
//                        listener.onBluetooth();
//                        STATE = BLUETOOTH;
//                    }
//                }
            } else {
                /** 未知网络 */
                if (STATE != DISCONNECT) {
                    listener.onDisConnect();
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
