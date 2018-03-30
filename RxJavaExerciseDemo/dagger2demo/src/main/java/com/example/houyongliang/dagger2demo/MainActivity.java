package com.example.houyongliang.dagger2demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.houyongliang.dagger2demo.other.Pot;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    @Inject
    ApiService mApiService;
    @Inject
    Pot pot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerUserComponet.create().inject(this);
        mApiService.register();
        Log.d("TAG", "onCreate: " + mApiService);

//        DaggerMainActivityComponent

    }
}
