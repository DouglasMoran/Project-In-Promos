package com.inpromos.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.inpromos.app.R;
import com.inpromos.app.utils.ApplicationKeys;
import com.inpromos.app.utils.SharedPreferencesReference;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private boolean isFirstTime;
    private SharedPreferencesReference sharedPreferencesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash_screen);

        verifyIfIsFirstTime();

    }

    private void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void makeIntent(Boolean isFirstTime) {
        final Intent intent;
        if (isFirstTime) {
            //Intent to on boarding
            intent = new Intent(this, OnBoardingActivity.class);
        } else {
            //Intent to main
            intent = new Intent(this, MainActivity.class);
        }

        handler.postDelayed(new Runnable() {
            @Override public void run() {
                //Execute intent
                startActivity(intent);
                //Close current activity
                SplashScreenActivity.this.finish();
            }
        }, 2000);

    }

    private void verifyIfIsFirstTime() {
       sharedPreferencesReference = new SharedPreferencesReference(this);
       isFirstTime = sharedPreferencesReference.getPreferences().getBoolean(ApplicationKeys.IS_FIRST_TIME_KEY, true);
       makeIntent(isFirstTime);
    }

}
