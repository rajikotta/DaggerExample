package com.raji.daggerexample.di;

import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.raji.daggerexample.AppConstant;
import com.raji.daggerexample.MyApplication;
import com.raji.daggerexample.R;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
@Module
public class AppModule {

    @Provides
    static RequestOptions providesRequestOptions() {

        return RequestOptions.
                placeholderOf(R.drawable.ic_launcher_background).
                error(R.drawable.white_background);
    }

    @Provides
    static RequestManager providesRequestManager(MyApplication myApplication, RequestOptions requestOptions) {
        return Glide.with(myApplication).applyDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable providesLogo(MyApplication myApplication) {
        return ContextCompat.getDrawable(myApplication, R.drawable.logo);
    }

    @Provides
    static Retrofit providesRetrofit() {
        return new Retrofit.Builder().baseUrl(AppConstant.baseURL).
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build();
    }


}
