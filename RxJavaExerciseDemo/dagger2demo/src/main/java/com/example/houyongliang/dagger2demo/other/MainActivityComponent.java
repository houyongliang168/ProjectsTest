package com.example.houyongliang.dagger2demo.other;

import com.example.houyongliang.dagger2demo.MainActivity;

import dagger.Component;

/**
 * @author houyongliang
 * @date 2018/3/7 10:47
 * Function(功能): component 组件
 */
@Component
public interface MainActivityComponent {

    void inject(MainActivity activity);

}
