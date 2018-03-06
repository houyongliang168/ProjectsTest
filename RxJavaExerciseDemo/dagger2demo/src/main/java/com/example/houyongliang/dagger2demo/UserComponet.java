package com.example.houyongliang.dagger2demo;

import dagger.Component;

/**
 * Created by houyongliang on 2018/3/5.
 */
@Component(modules = {UserModule.class})
public interface UserComponet {
    void inject(MainActivity activity);
}
