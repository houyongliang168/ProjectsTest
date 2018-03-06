package com.example.houyongliang.dagger2demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    @Inject
    ApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerUserComponet.create().inject(this);
        mApiService.register();
        Log.d("TAG", "onCreate: " + mApiService);
    }
}
