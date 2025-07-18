package com.salmakhd.android.shahabseries.projectone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salmakhd.android.shahabseries.R;

import java.util.HashMap;

public class FirstActivity extends AppCompatActivity {

    private Button login;
    private EditText user;
    private EditText pass;
    private HashMap<String,String> params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        onClicks();
    }

    private void init() {
        login = findViewById(R.id.login);
        pass =findViewById(R.id.password);
        user = findViewById(R.id.username);
        params = new HashMap<>();
        params.put("shahab", "1234");
        params.put("ahmad", "3434");

    }

    private void onClicks() {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();

                if(params.get(username) != null && params.get(username).equals(password)) {
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    intent.putExtra("Username", username);
                    startActivity(intent);
                } else {
                    Toast.makeText(FirstActivity.this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    /*
    flow: onPause() -> old activity, onCreate() -> new activity, onStop() -> old activity
     */
    @Override
    public void onPause() {
        super.onPause();
//        user.setText("");
//        pass.setText("");
    }

    @Override
    public void onStop() {
        super.onStop();
        user.setText("");
        pass.setText("");
    }
}