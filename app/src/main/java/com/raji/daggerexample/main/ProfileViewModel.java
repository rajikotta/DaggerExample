package com.raji.daggerexample.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.raji.daggerexample.auth.AuthResource;
import com.raji.daggerexample.auth.SessionDataManager;
import com.raji.daggerexample.auth.User;

import javax.inject.Inject;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class ProfileViewModel extends ViewModel {

    SessionDataManager mSessionDataManager;
    MediatorLiveData<AuthResource<User>> profile = new MediatorLiveData<>();

    @Inject
    public ProfileViewModel(SessionDataManager sessionDataManager) {
        mSessionDataManager = sessionDataManager;
    }

    public LiveData<AuthResource<User>> getProfile() {
        profile.addSource(mSessionDataManager.getAuthUser(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                profile.setValue(userAuthResource);
                profile.removeSource(mSessionDataManager.getAuthUser());
            }
        });
        return profile;
    }
}
