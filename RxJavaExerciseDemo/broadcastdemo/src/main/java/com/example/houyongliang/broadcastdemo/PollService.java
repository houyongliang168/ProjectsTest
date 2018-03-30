package com.example.houyongliang.broadcastdemo;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by houyongliang on 2018/3/29 16:28.
 * Function(功能):
 */

public class PollService extends IntentService {
    private static final String tag = "PollService";

    public static Intent newIntent(Context context) {
        return new Intent(context, PollService.class);
    }

    public PollService(String name) {
        super(name);
    }

    public PollService(){

            super(tag);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d(tag,"Received an intent :"+intent);
    }
}
