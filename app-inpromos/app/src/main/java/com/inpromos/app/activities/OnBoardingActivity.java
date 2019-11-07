package com.inpromos.app.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;
import com.inpromos.app.R;
import com.inpromos.app.adapters.OnBoardingAdapter;
import com.inpromos.app.utils.ApplicationKeys;
import com.inpromos.app.utils.SharedPreferencesReference;

public class OnBoardingActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ImageView mIndicatorOne, mIndicatorTwo, mIndicatorThree, mNext;
    private MaterialButton mSkipButton, mEndButton;
    private SharedPreferencesReference sharedPreferencesReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        getReferences();
        buttonListeners();
        setViewPager();

    }

    private void setViewPager() {
        mViewPager = findViewById(R.id.onBoardViewPager);
        mViewPager.setAdapter(new OnBoardingAdapter(this));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(final int position) {
                switch (position) {
                    case 0:
                        mIndicatorOne.setColorFilter(getResources().getColor(R.color.colorAccentPrimary));
                        mIndicatorTwo.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                        mIndicatorThree.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                        mSkipButton.setVisibility(View.VISIBLE);
                        mEndButton.setVisibility(View.GONE);
                        mNext.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        mIndicatorOne.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                        mIndicatorTwo.setColorFilter(getResources().getColor(R.color.colorAccentPrimary));
                        mIndicatorThree.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                        mSkipButton.setVisibility(View.VISIBLE);
                        mEndButton.setVisibility(View.GONE);
                        mNext.setVisibility(View.VISIBLE);
                        break;
                    default:
                        mIndicatorOne.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                        mIndicatorTwo.setColorFilter(getResources().getColor(R.color.colorPrimaryDark));
                        mIndicatorThree.setColorFilter(getResources().getColor(R.color.colorAccentPrimary));
                        mSkipButton.setVisibility(View.GONE);
                        mEndButton.setVisibility(View.VISIBLE);
                        mNext.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) { }

        });

    }

    private void buttonListeners() {
        mEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBooleanPreference();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });

        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBooleanPreference();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
                finish();
            }
        });

        //Skip
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mViewPager.getCurrentItem()) {
                    case 0:
                        mViewPager.setCurrentItem(1);
                        break;
                    case 1:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }

    private void setBooleanPreference() {
        sharedPreferencesReference = new SharedPreferencesReference(this);
        sharedPreferencesReference.getEditor().putBoolean(ApplicationKeys.IS_FIRST_TIME_KEY, false).commit();
    }

    private void getReferences() {
        mIndicatorOne = findViewById(R.id.indicatorOne);
        mIndicatorTwo = findViewById(R.id.indicatorTwo);
        mIndicatorThree = findViewById(R.id.indicatorThree);
        mSkipButton = findViewById(R.id.onBoardSkipBtn);
        mEndButton = findViewById(R.id.onBoardFinishBtn);
        mNext = findViewById(R.id.onBoardNextBtn);
    }

}
