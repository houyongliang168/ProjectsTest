package com.example.houyongliang.dagger2demo.other;

import javax.inject.Inject;

/**
 * Created by houyongliang on 2018/3/7 10:45.
 * Function(功能):
 */

public class Rose {
    @Inject
    public Rose() {}

    public String whisper()  {
        return "热恋";
    }

}
