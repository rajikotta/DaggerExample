package com.raji.daggerexample.di;

import androidx.lifecycle.ViewModel;

import com.raji.daggerexample.ApiService;
import com.raji.daggerexample.ui.viewmodel.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import retrofit2.Retrofit;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
@Module
public abstract class AuthModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    abstract ViewModel providesAuthViewModel(AuthViewModel authViewModel);

    @Provides
    static ApiService providesAPiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}