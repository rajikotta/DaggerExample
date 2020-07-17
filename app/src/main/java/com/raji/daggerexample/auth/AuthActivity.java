package com.raji.daggerexample.auth;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.raji.daggerexample.R;
import com.raji.daggerexample.databinding.ActivityAuthBinding;
import com.raji.daggerexample.di.ViewModelFactory;
import com.raji.daggerexample.main.MainActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    ActivityAuthBinding databinding;

    @Inject
    Drawable logo;
    @Inject
    RequestManager mRequestManager;
    @Inject
    ViewModelFactory mViewModelFactory;

    AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_auth);
        mRequestManager.load(logo).into(databinding.loginLogo);
        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(AuthViewModel.class);

        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                switch (userAuthResource.status) {
                    case LOADING:
                        databinding.progressBar.setVisibility(View.VISIBLE);
                        break;

                    case AUTHENTICATED:
                        databinding.progressBar.setVisibility(View.GONE);
                        navigateToMainActivity();
                        break;
                    case ERROR:
                        databinding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(AuthActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        break;
                    case LOGOUT:
                        databinding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(AuthActivity.this, "logout", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + userAuthResource.status);
                }
            }
        });


        databinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.login_button) {
                    if (TextUtils.isEmpty(databinding.userIdInput.getText().toString()))
                        return;

                    else {

                        viewModel.authenticateUser(databinding.userIdInput.getText().toString());
                    }
                }
            }
        });

    }

    private void navigateToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}