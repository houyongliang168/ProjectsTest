package com.example.houyongliang.testdemo.mockito;

/**
 * Created by houyongliang on 2018/3/21 15:51.
 * Function(功能):
 */

public class Person {

    private String name;
    private int sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return 11;
    }

    public String eat(String food) {
        return food;
    }

}
