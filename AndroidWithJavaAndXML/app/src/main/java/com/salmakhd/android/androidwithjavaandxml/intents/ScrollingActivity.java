package com.salmakhd.android.androidwithjavaandxml.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.salmakhd.android.androidwithjavaandxml.R;
import com.salmakhd.android.androidwithjavaandxml.databinding.ActivityScrollingBinding;
import com.salmakhd.android.androidwithjavaandxml.intents.MainActivity;

public class ScrollingActivity extends AppCompatActivity {

    private ActivityScrollingBinding binding;
    Button activityBbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityScrollingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activityBbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // specify an explicit intent
                // send data before starting the activity
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("email", "salmakhd@gmail.com");
                startActivity(intent);
                // specify an implicit intent
                Intent intent2 = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://google.com"));
                startActivity(intent2);
            }
        });
        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

    }
}