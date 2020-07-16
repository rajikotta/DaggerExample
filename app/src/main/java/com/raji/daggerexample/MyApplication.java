package com.raji.daggerexample;

import com.raji.daggerexample.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * Created by Raji on 7/15/20.
 * Golden Scent
 */
public class MyApplication extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().setApplication(this).build();
    }
}
