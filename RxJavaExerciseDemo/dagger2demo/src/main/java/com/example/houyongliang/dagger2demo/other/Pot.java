package com.example.houyongliang.dagger2demo.other;

import javax.inject.Inject;

/**
 * Created by houyongliang on 2018/3/7 10:46.
 * Function(功能):
 */

public class Pot {
    private Rose rose;

    @Inject
    public Pot(Rose rose) {
        this.rose = rose;
    }

    public String show() {
        return rose.whisper();
    }

}
