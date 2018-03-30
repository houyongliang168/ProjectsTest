package com.example.houyongliang.testdemo.PowerMock;

/**
 * Created by houyongliang on 2018/3/21 17:11.
 * Function(功能):
 */

public class Banana extends Fruit {
    private static String COLOR = "黄色的";

    public Banana() {}

    public static String getColor() {
        return COLOR;
    }

    public String getBananaInfo() {
        return flavor() + getColor();
    }

    private String flavor() {
        return "甜甜的";
    }

    public final boolean isLike() {
        return true;
    }

}
