package com.salmakhd.android.androidwithjavaandxml.intents;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.salmakhd.android.androidwithjavaandxml.R;
import com.salmakhd.android.androidwithjavaandxml.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private TextView salmaSadText;
    private ImageView imageView;
    private Button button;
    private ActivityMainBinding binding;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.myTextView);
        // get data from previous activity
        String email = getIntent().getStringExtra("email");
        text.setText(email);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onButtonClicked(v, salmaSadText.getText().toString());
//            }
//        });
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
//        imageView.setImageResource(R.drawable.ic_launcher_background);
//    }
//
//    private void onButtonClicked(View v, String textToBeDisplayed) {
//        Log.v("Button", "The button was just clicked: " + textToBeDisplayed);
//
//    }
    }
}