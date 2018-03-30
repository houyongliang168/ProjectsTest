package com.example.houyongliang.database_demo;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by houyongliang on 2018/3/22 15:29.
 * Function(功能):
 */

public class SQLiteUtils {
    private static SQLiteUtils mSQLiteUtils = null;

    private SQLiteUtils() {
    }

    public static SQLiteUtils getInstance() {
        if (mSQLiteUtils == null) {
            synchronized (SQLiteUtils.class) {
                if (mSQLiteUtils == null) {
                    mSQLiteUtils = new SQLiteUtils();
                }
            }
        }
        return mSQLiteUtils;
    }

    private Context context;
    private String name;
    private SQLiteDatabase writableDatabase;

    public SQLiteDatabase bigin(Context context, String name) {
        this.context = context;
        this.name = name;
        // 步骤1：创建DatabaseHelper对象
        // 注：此时还未创建数据库
        UserSQLiteOpenHelper dbHelper = new UserSQLiteOpenHelper(context, name);
        // 步骤2：真正创建 / 打开数据库
        writableDatabase = dbHelper.getWritableDatabase();
        return writableDatabase;
//        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase(); // 创建 or 打开 可读/写的数据库
//        SQLiteDatabase sqliteDatabase = dbHelper.getReadableDatabase(); // 创建 or 打开 可读的数据库
    }

    public void insertDate(){
        if(writableDatabase==null){
            return;
        }
    // a. 创建ContentValues对象
        ContentValues values = new ContentValues();
        // b. 向该对象中插入键值对
        values.put("id", "1");
        values.put("name", "carson");
        values.put("age", "22");
        //其中，key = 列名，value = 插入的值
        //注：ContentValues内部实现 = HashMap，区别在于：ContenValues Key只能是String类型，Value可存储基本类型数据 & String类型
        // c. 插入数据到数据库当中：insert()

        // 参数1：要操作的表名称
        // 参数2：SQl不允许一个空列，若ContentValues是空，那么这一列被明确的指明为NULL值
        // 参数3：ContentValues对象
        writableDatabase.insert("user", null, values);

//        // 注：也可采用SQL语句插入
//        String sql = "insert into user (id,name) values (1,'carson')";
//        writableDatabase.execSQL(sql) ;


    }

    public void updateDate(){
        if(writableDatabase==null){
            return;
        }



    }


}
