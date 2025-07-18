package com.salmakhd.android.androidwithjavaandxml.viewpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;


public class CustomPagerAdaptor extends PagerAdapter {
    Context context;

    public CustomPagerAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        return layoutInflater.inflate(modelObject.getmLayoutResId(), container, false);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    public CharSequence getPageTitle(int position) {
        ModelObject customerPagerEnum = ModelObject.values()[position];
        return context.getString(customerPagerEnum.getmTtitleResId());
    }
}
