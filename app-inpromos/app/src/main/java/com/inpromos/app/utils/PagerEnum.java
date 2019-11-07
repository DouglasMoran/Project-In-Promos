package com.inpromos.app.utils;

import com.inpromos.app.R;

public enum PagerEnum {

    ONE(R.layout.layout_on_boarding_one),
    TWO(R.layout.layout_on_boarding_two),
    THREE(R.layout.layout_on_boarding_three);

    private int layoutId;

    PagerEnum(int layoutId) {
        this.layoutId = layoutId;
    }

    public int getLayoutId() {
        return layoutId;
    }

}
