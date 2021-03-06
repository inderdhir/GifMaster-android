package com.inderdhir.gifmaster.core.di.module;

import com.inderdhir.gifmaster.core.GifMasterApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    protected GifMasterApplication mApplication;

    public AppModule() {
    }

    public AppModule(GifMasterApplication mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    protected GifMasterApplication providesApplication() {
        return mApplication;
    }
}

