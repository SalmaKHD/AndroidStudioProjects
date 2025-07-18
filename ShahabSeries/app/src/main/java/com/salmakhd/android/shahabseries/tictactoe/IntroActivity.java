package com.salmakhd.android.shahabseries.tictactoe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salmakhd.android.shahabseries.R;

public class IntroActivity extends AppCompatActivity {
    EditText player1,player2;
    Button start;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        player1 = findViewById(R.id.player_one_edit);
        player2 = findViewById(R.id.player_two_edit);
        start = findViewById(R.id.start);
        sharedPreferences = getSharedPreferences("Tic Tac", MODE_PRIVATE);
        if(isPreviousDataAvailable()) {
            Intent intent = new Intent(IntroActivity.this, TicTacToeActivity.class);
            startActivity(intent);
            finish();
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p1 = player1.getText().toString();
                String p2 = player2.getText().toString();
                if(!p1.isEmpty() && !p2.isEmpty()) {
                    sharedPreferences.edit().putString("p1Name", p1).apply();
                    sharedPreferences.edit().putString("p2Name", p2).apply();
                    Intent intent = new Intent(IntroActivity.this, TicTacToeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean isPreviousDataAvailable() {
        String p1 = sharedPreferences.getString("p1Name", "");
        String p2 = sharedPreferences.getString("p2Name", "");
        return !p1.isEmpty() && !p2.isEmpty();
    }
}