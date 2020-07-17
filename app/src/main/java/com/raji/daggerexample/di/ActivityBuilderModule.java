package com.raji.daggerexample.di;

import com.raji.daggerexample.auth.AuthActivity;
import com.raji.daggerexample.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = {AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(modules = {FragmentBuilderModule.class, MainModule.class})
    abstract MainActivity contributeMainActivity();
}
