package com.raji.daggerexample;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public interface ApiService {

    @GET("/user/{id}")
    Flowable<User> authenticate(@Path("id") String id);

}
