package com.example.houyongliang.annotationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import static com.example.houyongliang.annotationtest.WeeksDays.FRIDAY;
import static com.example.houyongliang.annotationtest.WeeksDays.MONDAY;
import static com.example.houyongliang.annotationtest.WeeksDays.SATURDAY;
import static com.example.houyongliang.annotationtest.WeeksDays.SUNDAY;
import static com.example.houyongliang.annotationtest.WeeksDays.THURSDAY;
import static com.example.houyongliang.annotationtest.WeeksDays.TUESDAY;
import static com.example.houyongliang.annotationtest.WeeksDays.WEDNESDAY;

public class Main2Activity extends AppCompatActivity {
    static final String TAG = "Main2Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        WeeksDays weeksDays = new WeeksDays();
        weeksDays.setCurrentDay(SATURDAY);
        @WeeksDays.WeekDays int today = weeksDays.getCurrentDay();
        switch (today) {
            case SUNDAY:
                Log.d(TAG,"SUNDAY");
                break;
            case MONDAY:
                Log.d(TAG,"MONDAY");
                break;
            case TUESDAY:
                Log.d(TAG,"TUESDAY");
                break;
            case WEDNESDAY:
                Log.d(TAG,"WEDNESDAY");
                break;
            case THURSDAY:
                Log.d(TAG,"THURSDAY");
                break;
            case FRIDAY:
                Log.d(TAG,"FRIDAY");
                break;
            case SATURDAY:
                Log.d(TAG,"SATURDAY");
                break;
            default:
                break;


        }

    }
}
