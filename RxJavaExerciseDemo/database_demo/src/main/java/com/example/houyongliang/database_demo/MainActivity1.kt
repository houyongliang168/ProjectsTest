package com.example.houyongliang.database_demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class MainActivity1 : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        val id = v!!.id

        when (id) {

            R.id.instablish -> {

            }//创建数据库
            R.id.upgrade -> {
            }//更新数据库
            R.id.insert -> {
            }//插入数据
            R.id.modify -> {
            }//修改数据
            R.id.query -> {
            }//查询数据
            R.id.delete -> {
            }//删除数据
            R.id.delete_database -> {
            }//删除数据库

            else -> {
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }


}