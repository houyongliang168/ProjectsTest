package com.example.houyongliang.dagger2demo;

import android.util.Log;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

/**
 * Created by houyongliang on 2018/3/5.
 */
@Module
public class UserModule {
    public UserModule() {
    }

    @Provides
    public ApiService provideApiService() {
        Log.e("UserModule","provideUserManger");
        return new ApiService();
    }
}
