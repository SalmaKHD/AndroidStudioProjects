package com.salmakhd.android.androidwithjavaandxml.listViewSalma;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;

import com.salmakhd.android.androidwithjavaandxml.R;

public class SalmaListAdaptor extends ArrayAdapter<String> {
    private final String[] titles;
    private final Activity context;
    public SalmaListAdaptor(@NonNull Activity activity, int resource, String[] titles) {
        super(activity, resource);
        this.titles = titles;
        this.context = activity;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater layoutInflater =context.getLayoutInflater();
        View rowView = layoutInflater.inflate(R.layout.custom_list_item, null, true);
        ((TextView) rowView.findViewById(R.id.title)).setText(titles[position]);
        return rowView;
    }
}
