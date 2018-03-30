package com.example.houyongliang.testdemo.mockito;

/**
 * Created by houyongliang on 2018/3/21 15:33.
 * Function(功能):
 */

public class Presenter {
    Model model;

    public Presenter(Model model) {
        this.model = model;
    }

    public boolean getBoolean() {
        return model.getBoolean();
    }


}
