package com.salmakhd.android.androidwithjavaandxml.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salmakhd.android.androidwithjavaandxml.R;

import java.util.List;

public class MyAdaptor extends RecyclerView.Adapter<MyAdaptor.MyViewHolder> {
    private List<Movie> movieList;

    public MyAdaptor(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_row_layout, parent, false);

        // inflate view and initialize (when an item becomes visible)
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // view is visible and ready to get data
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.genre.setText(movie.getGenre());
        holder.date.setText(movie.getDate());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    // recyclerview item view
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, genre, date;
        public MyViewHolder(@NonNull View itemView, TextView title, TextView genre, TextView date) {
            super(itemView);
            this.title = title;
            this.genre = genre;
            this.date = date;
        }

        // initialize view
        public MyViewHolder(@NonNull View itemView) { // this is going to receive the recyclerview item view
            super(itemView);
            title = itemView.findViewById(R.id.titleText);
            genre = itemView.findViewById(R.id.genreText);
            date = itemView.findViewById(R.id.dateText);
        }
    }
}
