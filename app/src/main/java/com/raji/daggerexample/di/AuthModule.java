package com.raji.daggerexample.di;

import androidx.lifecycle.ViewModel;

import com.raji.daggerexample.auth.AuthApiService;
import com.raji.daggerexample.auth.AuthViewModel;
import com.raji.daggerexample.auth.SessionDataManager;

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

    @Provides
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    static ViewModel providesAuthViewModel(AuthApiService authApiService, SessionDataManager sessionDataManager) {
        return new AuthViewModel(authApiService, sessionDataManager);
    }

    @Provides
    static AuthApiService providesAPiService(Retrofit retrofit) {
        return retrofit.create(AuthApiService.class);
    }
}
