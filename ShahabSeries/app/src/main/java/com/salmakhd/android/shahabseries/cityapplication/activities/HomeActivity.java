package com.salmakhd.android.shahabseries.cityapplication.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.salmakhd.android.shahabseries.R;
import com.salmakhd.android.shahabseries.cityapplication.MyDialog;
import com.salmakhd.android.shahabseries.cityapplication.model.CityModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
private Button homeBtn;
private FloatingActionButton addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//        homeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                YoYo.with(Techniques.Wobble).duration(1000).playOn(homeBtn);
//            }
//        });
        String jsonStr = loadJson();
        ArrayList<CityModel> list = convetToArray(jsonStr);
        CityAdaptor cityAdaptor = new CityAdaptor(list);
        addBtn = findViewById(R.id.ic_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create custom dialog and show
                MyDialog dialog = new MyDialog(HomeActivity.this);
                dialog.show();
            }
        });
    }

    private String loadJson() {
        String res = "";
        try {
            InputStream in = null;
            in = getAssets().open("city.json");
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
             res = new String(buffer, "UTF-8");
            return res;
        } catch (IOException e) {
           return res;
        }
    }

    private ArrayList<CityModel> convetToArray(String str) {
        ArrayList<CityModel> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(str);
            for(int i = 0; i<jsonArray.length(); i++ ) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CityModel cityModel = new CityModel();
                cityModel.setCity(jsonObject.getString("city"));
                cityModel.setCityF(jsonObject.getString("cityF"));
                cityModel.setLat(jsonObject.getString("lat"));
                cityModel.setLongt(jsonObject.getString("lon"));
                list.add(cityModel);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void buttonAnimation(View view) {

    }
}