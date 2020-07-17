package com.raji.daggerexample.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.raji.daggerexample.R;
import com.raji.daggerexample.auth.AuthResource;
import com.raji.daggerexample.auth.User;
import com.raji.daggerexample.databinding.FragmentProfileBinding;
import com.raji.daggerexample.di.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class ProfileFragment extends DaggerFragment {

    FragmentProfileBinding dataBinding;
    @Inject
    ViewModelFactory mViewModelFactory;

    ProfileViewModel mProfileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);

        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProfileViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ProfileViewModel.class);
        mProfileViewModel.getProfile().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    if (userAuthResource.status == AuthResource.Status.AUTHENTICATED) {
                        dataBinding.email.setText(userAuthResource.data.getEmailID());
                        dataBinding.username.setText(userAuthResource.data.getName());
                        dataBinding.website.setText(userAuthResource.data.getWebsite());
                    }
                }
            }
        });
    }
}
