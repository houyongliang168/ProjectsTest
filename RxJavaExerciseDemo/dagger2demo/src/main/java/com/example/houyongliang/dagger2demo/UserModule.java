package com.example.houyongliang.dagger2demo;

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
        return new ApiService();
    }
}
