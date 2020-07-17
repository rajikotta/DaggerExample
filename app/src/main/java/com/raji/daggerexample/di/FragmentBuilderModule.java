package com.raji.daggerexample.di;

import com.raji.daggerexample.main.PostFragment;
import com.raji.daggerexample.main.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();
}
