package com.inpromos.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.inpromos.app.utils.PagerEnum;

public class OnBoardingAdapter extends PagerAdapter {

    private Context context;

    public OnBoardingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        PagerEnum pagerEnum = PagerEnum.values()[position];
        View view = LayoutInflater.from(context).inflate(pagerEnum.getLayoutId(), container, false);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return PagerEnum.values().length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
