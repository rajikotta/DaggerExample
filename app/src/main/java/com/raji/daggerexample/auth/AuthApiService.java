package com.raji.daggerexample.auth;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public interface AuthApiService {

    @GET("/users/{id}")
    Flowable<User> authenticate(@Path("id") String id);

}
