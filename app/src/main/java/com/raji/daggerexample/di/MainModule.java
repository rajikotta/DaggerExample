package com.raji.daggerexample.di;

import androidx.lifecycle.ViewModel;

import com.raji.daggerexample.main.MainApiService;
import com.raji.daggerexample.main.PostRecyclerAdapter;
import com.raji.daggerexample.main.PostViewModel;
import com.raji.daggerexample.main.ProfileViewModel;

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
public abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @MainScope
    @Provides
    static MainApiService providesMainApiService(Retrofit retrofit) {
        return retrofit.create(MainApiService.class);
    }

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    abstract ViewModel bindPostViewModel(PostViewModel profileViewModel);

    @MainScope
    @Provides
    static PostRecyclerAdapter providesRVAdapter() {
        return new PostRecyclerAdapter();
    }

}
