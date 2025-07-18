package com.salmakhd.android.shahabseries.cityapplication.activities;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salmakhd.android.shahabseries.cityapplication.model.CityModel;

import java.util.ArrayList;

public class CityAdaptor extends RecyclerView.Adapter {
    ArrayList<CityModel> list;

    public CityAdaptor(ArrayList<CityModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
