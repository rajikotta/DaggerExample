package com.raji.daggerexample.di;

import com.raji.daggerexample.MyApplication;
import com.raji.daggerexample.auth.SessionDataManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Raji on 7/15/20.
 * Golden Scent
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ViewModelFactoryModule.class,
        ActivityBuilderModule.class
})
public interface AppComponent extends AndroidInjector<MyApplication> {

    SessionDataManager sessionDataManager();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder setApplication(MyApplication application);

        AppComponent build();

    }
}
