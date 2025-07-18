package com.salmakhd.android.androidwithjavaandxml.salma.customelistview;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.salmakhd.android.androidwithjavaandxml.R;

public class MyListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] titles;
    private final String[] desriptions;
    private final int[] images;

    public MyListAdapter(Activity context1, String[] titles, String[] desriptions, int[] images) {
        super(context1, R.layout.custom_list_item, titles);
        this.context = context1;
        this.titles = titles;
        this.desriptions = desriptions;
        this.images = images;
    }

    public View getView(int position, View view, ViewGroup parent) {
        // serves as a template that shows items
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_item, null, true);

        TextView title = rowView.findViewById(R.id.title);
        TextView subTitle = rowView.findViewById(R.id.subtitle);
        ImageView imageView = rowView.findViewById(R.id.icon);
        title.setText(titles[position]);
        subTitle.setText(desriptions[position]);
        imageView.setImageResource(images[position]);

        return view;
    }
}
