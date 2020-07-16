package com.raji;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.raji.daggerexample.AuthResource;
import com.raji.daggerexample.User;

import javax.inject.Inject;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class SessionDataManager {
    public MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionDataManager() {

    }

    public void authenticateUser(final LiveData<AuthResource<User>> resourceLiveData) {
        if (cachedUser != null) {
            cachedUser.setValue(AuthResource.loading(null));
            cachedUser.addSource(resourceLiveData, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {
                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(resourceLiveData);
                }
            });
        }
    }


}
