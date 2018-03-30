package com.example.houyongliang.others;

import com.example.houyongliang.typesviews.R;
import com.kingja.loadsir.callback.Callback;

/**
 * Created by houyongliang on 2018/3/12 11:19.
 * Function(功能):
 */

public class ErrorCallback extends Callback {
    @Override
    protected int onCreateView() {
        return R.layout.base_retry;
    }
}
