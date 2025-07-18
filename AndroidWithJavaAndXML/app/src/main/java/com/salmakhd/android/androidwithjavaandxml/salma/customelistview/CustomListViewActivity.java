package com.salmakhd.android.androidwithjavaandxml.salma.customelistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salmakhd.android.androidwithjavaandxml.R;

public class CustomListViewActivity extends AppCompatActivity {

    ListView listView;
    String[] titles = {
            "title1",
            "title2",
            "title3",
    };
    String[] descriptions = {
            "Download Master",
            "Download",
            "Learn"
    };
    int[] images = {
            R.drawable.bmichart,
            R.drawable.biking,
            R.drawable.ecg
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_list_view);
        MyListAdapter myListAdapter = new MyListAdapter(
                this,
                titles,
                descriptions,
                images
        );
        listView = findViewById(R.id.list);
        listView.setAdapter(myListAdapter);
        // add a click listener to list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Toast.makeText(CustomListViewActivity.this, "Item 1 clicked", Toast.LENGTH_LONG).show();
                }
                if(position == 1) {
                    Toast.makeText(CustomListViewActivity.this, "Item 2 clicked", Toast.LENGTH_LONG).show();
                }
                if(position == 2) {
                    Toast.makeText(CustomListViewActivity.this, "Item 3 clicked", Toast.LENGTH_LONG).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}