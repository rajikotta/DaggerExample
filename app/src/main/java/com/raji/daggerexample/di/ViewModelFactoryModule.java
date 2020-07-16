package com.raji.daggerexample.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    abstract ViewModelProvider.Factory providesViewModelFacory(ViewModelFactory viewModelFactory);
}
