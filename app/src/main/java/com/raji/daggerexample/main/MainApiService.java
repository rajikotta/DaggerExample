package com.raji.daggerexample.main;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Raji on 7/17/20.
 * Golden Scent
 */
public interface MainApiService {
    @GET("/posts")
    Flowable<List<Post>> getPosts(@Query("userId") String id);
}
