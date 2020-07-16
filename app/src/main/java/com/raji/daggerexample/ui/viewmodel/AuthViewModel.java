package com.raji.daggerexample.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.raji.SessionDataManager;
import com.raji.daggerexample.ApiService;
import com.raji.daggerexample.AuthResource;
import com.raji.daggerexample.User;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class AuthViewModel extends ViewModel {
    ApiService mApiService;
    SessionDataManager mSessionDataManager;


    @Inject
    public AuthViewModel(ApiService apiService, SessionDataManager sessionDataManager) {
        mApiService = apiService;
        mSessionDataManager = sessionDataManager;
    }


    public void authenticateUser(String id) {

        mSessionDataManager.authenticateUser(queryUser(id));
    }

    public LiveData<AuthResource<User>> queryUser(String id) {
        return LiveDataReactiveStreams.fromPublisher(mApiService.authenticate(id)
                .onErrorReturn(new Function<Throwable, User>() {
                    @Override
                    public User apply(Throwable throwable) throws Throwable {
                        return new User("-1");

                    }
                }).map(new Function<User, AuthResource<User>>() {
                    @Override
                    public AuthResource<User> apply(User user) throws Throwable {
                        if ("-1".equalsIgnoreCase(user.getId()))
                            return AuthResource.error("Could not authenticate");
                        else
                            return AuthResource.authenticated(user);

                    }
                }).subscribeOn(Schedulers.io()));
    }

    public LiveData<AuthResource<User>> observeAuthState() {
        return mSessionDataManager.cachedUser;
    }
}
