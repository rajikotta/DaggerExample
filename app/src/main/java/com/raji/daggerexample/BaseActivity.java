package com.raji.daggerexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.raji.daggerexample.auth.AuthActivity;
import com.raji.daggerexample.auth.AuthResource;
import com.raji.daggerexample.auth.SessionDataManager;
import com.raji.daggerexample.auth.User;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Inject
    public SessionDataManager mSessionDataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Observe cached user");
        mSessionDataManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource != null) {
                    switch (userAuthResource.status) {
                        case LOADING: {
                            Log.d(TAG, "onChanged: BaseActivity: LOADING...");
                            break;
                        }

                        case AUTHENTICATED: {
                            Log.d(TAG, "onChanged: BaseActivity: AUTHENTICATED... " +
                                    "Authenticated as: " + userAuthResource.data.getEmailID());
                            break;
                        }

                        case ERROR: {
                            Log.d(TAG, "onChanged: BaseActivity: ERROR...");
                            break;
                        }

                        case LOGOUT: {
                            Log.d(TAG, "onChanged: BaseActivity: NOT AUTHENTICATED. Navigating to Login screen.");
                            navigateToAuthActivity();
                            break;
                        }
                    }
                }
            }
        });
    }

    private void navigateToAuthActivity() {
        startActivity(new Intent(this, AuthActivity.class));
        finish();
    }
}
