package com.example.houyongliang.rxjavaexercisedemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

// Rxjava 测试

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Rxjava";
    private TextView tv;
    private int xx=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);


    }






}
