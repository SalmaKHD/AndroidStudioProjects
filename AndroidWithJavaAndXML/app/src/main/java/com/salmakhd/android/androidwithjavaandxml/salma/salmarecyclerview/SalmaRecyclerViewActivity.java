package com.salmakhd.android.androidwithjavaandxml.salma.salmarecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.salmakhd.android.androidwithjavaandxml.R;

import java.util.ArrayList;

public class SalmaRecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SalmaRecyclerViewAdaptor adaptor;
    ArrayList<CustomModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_salma_recycler_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.salma_recycler_view);
        getDataReady();
        adaptor = new SalmaRecyclerViewAdaptor(this, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptor);
    }

    private void getDataReady() {
        list = new ArrayList<>();
        list.add(new CustomModel(
                "Salma",
                "15",
                "23"
        ));
        list.add(new CustomModel(
                "Soheil",
                "15",
                "23"
        ));
    }
}