package com.raji.daggerexample.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
@Singleton
public class SessionDataManager {
    MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionDataManager() {

    }

    public void authenticateUser(final LiveData<AuthResource<User>> source) {
        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading((User) null));
            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);

                    if (userAuthResource.status.equals(AuthResource.Status.ERROR)) {
                        cachedUser.setValue(AuthResource.<User>logout());
                    }
                }
            });
        }
    }


    public void logout() {
        cachedUser.setValue(AuthResource.logout());
    }

    public LiveData<AuthResource<User>> getAuthUser() {
        return cachedUser;
    }
}
