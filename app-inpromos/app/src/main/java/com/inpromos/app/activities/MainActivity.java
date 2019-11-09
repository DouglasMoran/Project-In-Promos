package com.inpromos.app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.fragment.NavHostFragment;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inpromos.app.R;

import java.util.Objects;

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
        mBottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        switchFragments(R.color.colorAccentPrimary, R.id.nav_home);
                        return true;
                    case R.id.nav_orders:
                        switchFragments(R.color.colorAccentThird, R.id.nav_orders);
                        return true;
                    default:
                        switchFragments(R.color.colorAccentSecondary, R.id.nav_help);
                        return true;
                }
            }
        });
    }

    private void switchFragments(int colorId, int actionId) {
        NavHostFragment.findNavController(Objects.requireNonNull(getSupportFragmentManager()
                .findFragmentById(R.id.navHostFragment)))
                .navigate(actionId);

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


    @Override
    public void onBackPressed() {
        finish();
    }
}
