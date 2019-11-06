package com.inpromos.app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.inpromos.app.R;

public class SplashScreenActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash_screen);

        //Intent to main
        final Intent intent = new Intent(this, MainActivity.class);
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                //Execute intent
                startActivity(intent);
                //Close current activity
                SplashScreenActivity.this.finish();
            }
        }, 2000);

    }

    private void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}
