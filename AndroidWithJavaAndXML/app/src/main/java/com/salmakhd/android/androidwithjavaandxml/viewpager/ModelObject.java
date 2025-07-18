package com.salmakhd.android.androidwithjavaandxml.viewpager;

import com.salmakhd.android.androidwithjavaandxml.R;

public enum ModelObject {
    RED(R.string.red, R.layout.view_red), // each represents a fragment
    BLUE(R.string.blue, R.layout.blue_layout),
    YELLOW(R.string.yellow, R.layout.view_yellow);

    private int mTtitleResId;
    private int mLayoutResId;

    ModelObject(int mTtitleResId, int mLayoutResId) {
        this.mTtitleResId = mTtitleResId;
        this.mLayoutResId = mLayoutResId;
    }

    public int getmTtitleResId() {
        return mTtitleResId;
    }

    public void setmTtitleResId(int mTtitleResId) {
        this.mTtitleResId = mTtitleResId;
    }

    public int getmLayoutResId() {
        return mLayoutResId;
    }

    public void setmLayoutResId(int mLayoutResId) {
        this.mLayoutResId = mLayoutResId;
    }
}
