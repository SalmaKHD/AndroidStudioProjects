package com.salmakhd.android.androidwithjavaandxml.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.salmakhd.android.androidwithjavaandxml.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewCustomActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdaptor myAdaptor;
    List<Movie> movieList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler_view_custom);
        recyclerView = findViewById(R.id.recyclerView);
        myAdaptor = new MyAdaptor(movieList);

        // will manage the layout for the RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        // set recyclerview
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdaptor);
        insertData();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // will use the model class to send them correctly to the adaptor and the adaptor will bind it to the view
    private void insertData() {
        Movie movie = new Movie(
                "Fast & Furious", "Action", "2019"
        );
        Movie movie2 = new Movie(
                "Something", "Action", "2019"
        );
        movieList.add(movie);
        movieList.add(movie);

        // let the adapter invalidate layout
        myAdaptor.notifyDataSetChanged();
    }
}