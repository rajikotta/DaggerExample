package com.raji.daggerexample.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.raji.daggerexample.BaseActivity;
import com.raji.daggerexample.R;
import com.raji.daggerexample.databinding.ActivityMainBinding;

/**
 * Created by Raji on 7/16/20.
 * Golden Scent
 */
public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityMainBinding databinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, navController, databinding.drawerLayout);
        NavigationUI.setupWithNavController(databinding.navView, navController);

        databinding.navView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                mSessionDataManager.logout();
                break;
            case android.R.id.home:
                if (databinding.drawerLayout.isOpen()) {
                    databinding.drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.profile:
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.main_nav, true)
                        .build();


                Navigation.findNavController(this, R.id.nav_host).navigate(R.id.profilefragment, null, navOptions);
                break;
            case R.id.posts:
                if (isValidDestination(R.id.postfragment)) {
                    Navigation.findNavController(this, R.id.nav_host).navigate(R.id.postfragment);
                }
                break;
        }
        item.setChecked(true);
        databinding.drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private boolean isValidDestination(int destination) {
        return destination != Navigation.findNavController(this, R.id.nav_host).getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host), databinding.drawerLayout);
    }


}
