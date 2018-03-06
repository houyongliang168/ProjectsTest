package com.example.houyongliang.surfaceviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private LuckyPanView mLuckyPanView;
    private ImageView mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLuckyPanView = (LuckyPanView) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);
        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPanView.isStart()) {
                    mLuckyPanView.luckyStart(1);
                    mStartBtn.setImageResource(R.mipmap.stop);
                } else {
                    if (!mLuckyPanView.isShouldEnd()) {
                        mLuckyPanView.luckyEnd();
                        mStartBtn.setImageResource(R.mipmap.start);
                    }

                }
            }
        });
    }
}
