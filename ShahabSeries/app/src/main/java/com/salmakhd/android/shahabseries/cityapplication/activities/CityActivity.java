package com.salmakhd.android.shahabseries.cityapplication.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salmakhd.android.shahabseries.R;
import com.salmakhd.android.shahabseries.cityapplication.model.CityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CityActivity extends AppCompatActivity {
    private ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_city);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        logo = findViewById(R.id.main_logo);
        animation();

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent intent = new Intent(CityActivity.this, HomeActivity.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                  },
                8000);
    }

    private void animation() {
        ObjectAnimator scale = ObjectAnimator.ofFloat(
                logo,
                "scaleX",
                0f,
                2f,
                1f
        );
        scale.setDuration(3000);
        scale.setRepeatCount(ValueAnimator.INFINITE);
        scale.setRepeatMode(ValueAnimator.REVERSE);
        scale.start();
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(
                logo,
                "scaleY",
                0f,
                2f,
                1f
        );
        scaleY.setDuration(3000);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);
        scaleY.setRepeatMode(ValueAnimator.REVERSE);
        scaleY.start();

        ObjectAnimator rotation = ObjectAnimator.ofFloat(
                logo,
                "rotation",
                0f,
                360f
        );
        rotation.setDuration(3000);
        rotation.setStartDelay(3000);
        rotation.start();

        ObjectAnimator traslationY = ObjectAnimator.ofFloat(
                logo,
                "translationY",
                0f,
                50f
        );
        traslationY.setStartDelay(8000);
        traslationY.setDuration(3000);
        traslationY.start();

    }
    public void btnClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:900.."));
        startActivity(intent);
    }
}