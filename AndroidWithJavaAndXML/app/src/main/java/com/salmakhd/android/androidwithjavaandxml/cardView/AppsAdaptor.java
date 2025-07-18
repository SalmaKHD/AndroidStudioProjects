package com.salmakhd.android.androidwithjavaandxml.cardView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.salmakhd.android.androidwithjavaandxml.R;

import java.util.List;

public class AppsAdaptor extends RecyclerView.Adapter<AppsAdaptor.ViewHolder>{
    private Context context;
    private List<AppModel> appModelList;

    public AppsAdaptor(Context context, List<AppModel> appModelList) {
        this.context = context;
        this.appModelList = appModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppModel appModel = appModelList.get(position);
        holder.title.setText(appModel.getTitle());
        holder.appDownloads.setText(appModel.getNumOfDownloads());

        // use Glide library to show images
        Glide.with(context)
                .load(appModel.getThumbnail())
                .into(holder.thumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "You clicked " + appModel.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return appModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,appDownloads;
        public ImageView thumbnail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            appDownloads = itemView.findViewById(R.id.downalods);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
