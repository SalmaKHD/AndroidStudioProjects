package com.salmakhd.android.androidwithjavaandxml.shahab.projectone;

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

import com.salmakhd.android.androidwithjavaandxml.R;
import com.salmakhd.android.androidwithjavaandxml.intents.MainActivity;

public class ProjectOneActivity extends AppCompatActivity {
    private Button login;
    private EditText user;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_project_one);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
       //
        user = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String out = user.getText().toString();
                if (!out.isEmpty()) {
                    Toast.makeText(ProjectOneActivity.this, out, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}