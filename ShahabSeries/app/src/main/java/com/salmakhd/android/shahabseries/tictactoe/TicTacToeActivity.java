package com.salmakhd.android.shahabseries.tictactoe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.salmakhd.android.shahabseries.R;

public class TicTacToeActivity extends AppCompatActivity {
    private TextView player1ScoreText;
    private TextView playerTwoScroreText;
    private Button[][] buttons= new Button[3][3];
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private SharedPreferences sp;

    private int round = 0;
    private boolean playerOneTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tic_tac_toe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    public void init() {
        sp = getSharedPreferences("Tic Tac", MODE_PRIVATE);
        String playerOne = sp.getString("p1Name", "player1");
        String playerTwo = sp.getString("p2Name", "player2");

        player1ScoreText = findViewById(R.id.player1score);
        playerTwoScroreText = findViewById(R.id.player2score);
        player1ScoreText.setTextColor(getColor(R.color.purple));
        player1ScoreText.setText(playerOne + " Score: 0");
        playerTwoScroreText.setText(playerTwo + " Score: 0");
        for(int i = 0; i <3; i++) {
            for(int j = 0; j < 3; j++) {
                String idText = "button_" + (i) + (j);
                buttons[i][j] = findViewById(getResources().getIdentifier(idText, "id", getPackageName()));
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        buttonClick(v);
                    }
                });
            }
        }
    }

    public void buttonClick(View view) {
        Button button = (Button) view;
        if(button.getText().toString().isEmpty()) {
            if(playerOneTurn) {
                button.setText("O");
                playerOneTurn = false;
                playerTwoScroreText.setTextColor(getColor(R.color.purple));
                player1ScoreText.setTextColor(getColor(R.color.black));
            }
            else {
                button.setText("X");
                playerOneTurn = true;
                player1ScoreText.setTextColor(getColor(R.color.purple));
                playerTwoScroreText.setTextColor(getColor(R.color.black));
            }
        }

        if(checkForWinner()) {
            clearScreen();
            if(playerOneTurn) {
                Toast.makeText(TicTacToeActivity.this, "Player 1 wins!", Toast.LENGTH_LONG).show();
                playerOneScore++;
            } else {
                Toast.makeText(TicTacToeActivity.this, "Player 2 wins!", Toast.LENGTH_LONG).show();
                playerTwoScore++;
            }
        } else if(round == 9) {
            clearScreen();
            Toast.makeText(TicTacToeActivity.this, "Draw!", Toast.LENGTH_LONG).show();
        } else {
            round++;
        }
        playerOneTurn = !playerOneTurn;
        if(playerOneTurn) {
            playerTwoScroreText.setText("Player1 Score:" + playerOneScore);
        } else {
            player1ScoreText.setText("Player1 Score:" + playerOneScore);
        }

    }

    private void clearScreen() {
        for(int i = 0; i <3; i++) {
            for(int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        round = 0;
    }

    private boolean checkForWinner() {
        String[][] texts = new String[3][3];
        for(int i = 0; i <3; i++) {
            for(int j = 0; j < 3; j++) {
                texts[i][j] = buttons[i][j].getText().toString();
            }
        }
        for(int i = 0; i < 3; i++) {
            if(texts[0][i].equals(texts[1][i]) && texts[1][i].equals(texts[2][i]) && !texts[1][i].isEmpty()) {
                return true;
            }
        }
        for(int i = 0; i < 3; i++) {
            if(texts[i][0].equals(texts[i][1]) && texts[i][1].equals(texts[i][2]) && !texts[i][1].isEmpty()) {
                return true;
            }
        }

        if(texts[0][0].equals(texts[1][1]) && texts[1][1].equals(texts[2][1])) {
            return true;
        }
        return false;
    }
}