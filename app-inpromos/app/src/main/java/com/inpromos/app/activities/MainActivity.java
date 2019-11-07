package com.inpromos.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inpromos.app.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavViewConfiguration();

    }

    private void bottomNavViewConfiguration() {
        mBottomNavView = findViewById(R.id.mainBottomNavView);
        //Setting navigation
        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        NavigationUI.setupWithNavController(mBottomNavView, hostFragment.getNavController());

        mBottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        switchColors(R.color.colorAccentPrimary);
                        break;
                    case R.id.nav_orders:
                        switchColors(R.color.colorAccentThird);
                        break;
                    default:
                        switchColors(R.color.colorAccentSecondary);
                        break;
                }
                return true;
            }
        });
    }

    private void switchColors(int colorId) {
        getWindow().setStatusBarColor(getResources().getColor(colorId));

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_checked }, //enables
                new int[] { -android.R.attr.state_checked },//disabled
        };

        int [] colors = { ContextCompat.getColor(getBaseContext(), colorId),
                ContextCompat.getColor(getBaseContext(), R.color.colorPrimaryDark) };

        mBottomNavView.setItemIconTintList(new ColorStateList(states, colors));
        mBottomNavView.setItemTextColor(new ColorStateList(states, colors));

    }

}
