package com.example.houyongliang.database_demo

import android.content.ContentValues
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main2.*
import android.R.attr.name
import android.R.attr.name
import com.example.houyongliang.database_demo.R.id.listView
import android.R.attr.name








class MainActivity : AppCompatActivity(), View.OnClickListener {


    override fun onClick(v: View?) {
        val id = v!!.id
        Log.e(TAG,"v!!.id"+v!!.id)
        when (id) {

            R.id.add -> {
//                /*第一种方式*/
//                //获取数据
//                Log.e(TAG,"Bigin add")
//                val name1 = et_name.text.toString()
//                val age1 = et_age.text.toString()
//                //1.  通过帮助实例，获取数据库 ，此时会调用  帮助类中的 onCreate 方法。
//                val database_add = myHelper!!.writableDatabase
//                //2.  给表中添加数据 定义sql语句
//                val sql = "insert into day02 (name,age) values (?,?);"
//                //3.  执行sql 语句
//                database_add.execSQL(sql, arrayOf<String>(name1, age1))
//                //4. 关闭数据库
//                database_add.close()
                //第二种方式
                val name1 = et_name.getText().toString()
                val age1 = et_age.getText().toString()
                //1.  通过帮助实例，获取数据库 ，此时会调用  帮助类中的 onCreate 方法。
                val database_add = myHelper!!.getWritableDatabase()
                //2.  给表中添加数据 定义sql语句
                val values = ContentValues()
                values.put("name", name1)
                values.put("age", age1)
                database_add.insert("day02", null, values)
                //4. 关闭数据库
                database_add.close()


            }//插入数据
            R.id.update -> {
                Log.e(TAG,"Bigin upadte")
//                /*第一种方式*/
//                //清空集合
//                list!!.clear()
//                //根据名字更新年龄
//                val name3 = et_name.getText().toString()
//                val age3 = et_age.getText().toString()
//                //1、 通过帮助实例，获取数据库 ，此时会调用 帮助类 中的 onCreate 方法
//                val database_update = myHelper!!.getWritableDatabase()
//                //2. 定义更新sql语句
//                val sql_update = "update day02 set age=? where name=? ;"
//                //3. 执行sql语句
//                database_update.execSQL(sql_update, arrayOf<String>(age3, name3))
//                //4. 关闭数据库
//                database_update.close()
                /*第二中方式*/
                //根据名字更新年龄
                val name3 = et_name.getText().toString()
                val age3 = et_age.getText().toString()
                //1、 通过帮助实例，获取数据库 ，此时会调用 帮助类 中的 onCreate 方法
                val database_update = myHelper!!.getWritableDatabase()
                //2. 执行跟新语句
                val values2 = ContentValues()
                values2.put("name", name3)
                values2.put("age", age3)
                database_update.update("day02", values2, "name=?", arrayOf(name3))
                //3. 关闭数据库
                database_update.close()
            }//修改数据
            R.id.query -> {
                Log.e(TAG,"Bigin query")
//                //根据名字查询数据
//                val name4 = et_name.getText().toString()
//                val age4 = et_age.getText().toString()
//                //1. 获取帮助实例，获取数据库，此时会调用帮助类中的 onCreate 方法
//                val database_query = myHelper!!.getWritableDatabase()
//                //2. 定义查询语句 结果集
////                val sql_select = " select * from day02 where name = ? ;"
////                val cursor = database_query.rawQuery(sql_select, arrayOf(name4))
//                val sql_select = " select * from day02  ;"
//                val cursor =database_query.rawQuery(sql_select, null)
//                list!!.clear()
//                //3.遍历结果集
//                while (cursor.moveToNext()) {
//
//                    val name5 = cursor.getString(cursor.getColumnIndex("name"))
//                    val age5 = cursor.getInt(cursor.getColumnIndex("age"))
//                    val id5 = cursor.getInt(cursor.getColumnIndex("_id"))
//                    val person = Person(id5, name5, age5)
//                    list!!.add(person)
//                }
//                listView.adapter = ArrayAdapter<Person>(this@MainActivity, android.R.layout.simple_list_item_1, list)
//                /*      //2. 定义查询语句 结果集
//                Cursor cursor = database_query.query("day02", new String[]{"name","age"}, "name=?", new String[]{name4}, null, null, null);
//                //3. 遍历结果集
//                while(cursor.moveToNext()){
//
//                    name5 += cursor.getString(cursor.getColumnIndex("name"));
//                    age5 += cursor.getString(cursor.getColumnIndex("age"));
//
//                    content.setText("NAME--------"+name5+"age_____"+age5);
//                }*/
//                //关闭数据库
//                database_query.close()


                //清空数据
                list!!.clear()
                //根据名字查询数据
                val name4 = et_name.getText().toString()
                val age4 = et_age.getText().toString()
                //1. 获取帮助实例，获取数据库，此时会调用帮助类中的 onCreate 方法
                val database_query = myHelper!!.getWritableDatabase()
                //2. 执行查询语句 获取 结果集
//                val cursor = database_query.query("day02", null, "name=?", arrayOf(name4), null, null, null)
                val cursor   =database_query.query("day02", null, null, null, null, null, null)
                //3.遍历结果集
                while (cursor.moveToNext()) {

                    val name5 = cursor.getString(cursor.getColumnIndex("name"))
                    val age5 = cursor.getInt(cursor.getColumnIndex("age"))
                    val id5 = cursor.getInt(cursor.getColumnIndex("_id"))
                    val person = Person(id5, name5, age5)
                    list!!.add(person)
                }
                if(!cursor.isClosed){
                    cursor.close()
                }

                listView.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, list)

                //4.关闭数据库
                database_query.close()


            }//查询数据
            R.id.delt -> {
                Log.e(TAG,"Bigin delt")
                /*第一种方式*/
//                //将相同名字的条目删除
//                val name2 = et_name.getText().toString()
//                //1. 通过帮助实例，获取数据库， 此时会调用 帮助类中的 onCreate 方法
//                val database_delt = myHelper!!.getWritableDatabase()
//                //2. 定义删除 sql 语句
//                val sql_delt = "delete from day02 where name=?;"
//                //3. 执行sql 语句
//                database_delt.execSQL(sql_delt, arrayOf<String>(name2))
//                //4. 关闭数据库
//                database_delt.close()
                /*第二中方式*/

                //将相同名字的条目删除
                val name2 = et_name.getText().toString()
                //1. 通过帮助实例，获取数据库， 此时会调用 帮助类中的 onCreate 方法
                val database_delt = myHelper!!.getWritableDatabase()
                //2. 定义删除  语句
                database_delt.delete("day02", "name=?", arrayOf(name2))
                //3. 关闭数据库
                database_delt.close()

            }//删除数据
//            R.id.delete_database -> {
//            }//删除数据库

            else -> {
            }
        }

    }
    private var TAG:String="MainActivity";
    private var myHelper: MyHelper? = null
    private var list: ArrayList<Person>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //1.获取数据库对象 ，该对象还未创建表格
        myHelper = MyHelper(this)
        list = ArrayList<Person>()
        init()


    }

    private fun init() {
        add.setOnClickListener(this)
        delt.setOnClickListener(this)
        update.setOnClickListener(this)
        query.setOnClickListener(this)
//              SQLiteDatabase db=SQLiteDatabase.openDatabase(path, factory, flags);
//              SQLiteDatabase.openOrCreateDatabase(file, factory);
    }


}
