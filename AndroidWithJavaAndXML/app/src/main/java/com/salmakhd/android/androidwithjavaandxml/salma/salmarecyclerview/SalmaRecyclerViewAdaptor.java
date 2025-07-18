package com.salmakhd.android.androidwithjavaandxml.salma.salmarecyclerview;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salmakhd.android.androidwithjavaandxml.R;

import java.util.List;

public class SalmaRecyclerViewAdaptor extends RecyclerView.Adapter<SalmaRecyclerViewAdaptor.ViewHolder>{

    public Context context;
    public List<CustomModel> studentList;

    public SalmaRecyclerViewAdaptor(Context context, List<CustomModel> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(com.salmakhd.android.androidwithjavaandxml.R.layout.salma_recycler_view_row, parent, false);

        return new SalmaRecyclerViewAdaptor.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // when a view becomes visible
        holder.name.setText(studentList.get(position).name);
        holder.age.setText(studentList.get(position).age);
        holder.grade.setText(studentList.get(position).grade);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        TextView grade;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            grade = itemView.findViewById(R.id.grade);
        }
    }
}
