package com.example.houyongliang.database_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by houyongliang on 2018/3/22 17:03.
 * Function(功能):
 */

public class MyHelper extends SQLiteOpenHelper {


    private static String NAME = "day02.db";

    //建表语句
    private static String CREATE_TABLE = " create table day02 (_id integer primary key autoincrement ,name text ,age text);";

    // 四个参数 ： context 上下文  name 数据库的名称  factory 游标工厂  version 版本号，必须从一开始
    public MyHelper(Context context) {
        super(context, NAME, null, 1);
    }

    //当数据库创建的时候调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL(CREATE_TABLE);

    }

    //更新数据库的时候调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 比较 oldVersion 与 newVersion ，newVersion 大于  oldVersion 则进行更新操作。
        //      Toast.makeText(context, text, duration).show();
    }
}
