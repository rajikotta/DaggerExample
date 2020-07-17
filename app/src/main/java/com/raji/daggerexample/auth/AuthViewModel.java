package com.raji.daggerexample.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class AuthViewModel extends ViewModel {
    AuthApiService mAuthApiService;
    SessionDataManager mSessionDataManager;


    @Inject
    public AuthViewModel(AuthApiService authApiService, SessionDataManager sessionDataManager) {
        mAuthApiService = authApiService;
        mSessionDataManager = sessionDataManager;
    }


    public void authenticateUser(String id) {

        mSessionDataManager.authenticateUser(queryUser(id));
    }

    public LiveData<AuthResource<User>> queryUser(String id) {
        return LiveDataReactiveStreams.fromPublisher(mAuthApiService.authenticate(id)
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
