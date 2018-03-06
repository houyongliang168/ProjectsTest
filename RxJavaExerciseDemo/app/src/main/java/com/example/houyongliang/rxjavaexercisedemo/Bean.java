package com.example.houyongliang.rxjavaexercisedemo;

/**
 * Created by houyongliang on 2018/2/8.
 */

public class Bean {
    private String url;
    private int num;

    public Bean(int num) {
        this.num = num;
    }

    public Bean(String url, int num) {
        this.url = url;
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "url='" + url + '\'' +
                ", num=" + num +
                '}';
    }
}
