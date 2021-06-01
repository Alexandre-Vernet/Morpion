package com.ynov.vernet.morpion;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Crosses & Rounds
    private final boolean[] cross = new boolean[9];
    private final boolean[] round = new boolean[9];
    private final boolean[] box = new boolean[9];
    private boolean victory = false;

    // Scores
    TextView m_ScoreJ1, m_ScoreJ2;
    private int scoreJ1, scoreJ2 = 0;

    TextView[] btn = new TextView[9];

    private int pawnChoice = 1;

    String username;

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextViews
        m_ScoreJ1 = findViewById(R.id.scoreJ1);
        m_ScoreJ2 = findViewById(R.id.scoreJ2);

        // Buttons
        btn[0] = findViewById(R.id.btn0);
        btn[0].setOnClickListener(this);

        btn[1] = findViewById(R.id.btn1);
        btn[1].setOnClickListener(this);

        btn[2] = findViewById(R.id.btn2);
        btn[2].setOnClickListener(this);

        btn[3] = findViewById(R.id.btn3);
        btn[3].setOnClickListener(this);

        btn[4] = findViewById(R.id.btn4);
        btn[4].setOnClickListener(this);

        btn[5] = findViewById(R.id.btn5);
        btn[5].setOnClickListener(this);

        btn[6] = findViewById(R.id.btn6);
        btn[6].setOnClickListener(this);

        btn[7] = findViewById(R.id.btn7);
        btn[7].setOnClickListener(this);

        btn[8] = findViewById(R.id.btn8);
        btn[8].setOnClickListener(this);


        // Get username
        SharedPreferences sp = getSharedPreferences("username", Activity.MODE_PRIVATE);
        username = sp.getString("username", null);

        if (username == null) {
            startActivity(new Intent(this, PlayerNameActivity.class));
            finish();
        }

        m_ScoreJ1.setText(getString(R.string.score_p1, scoreJ1, username));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                placePawn(0);
                break;
            case R.id.btn1:
                placePawn(1);
                break;
            case R.id.btn2:
                placePawn(2);
                break;
            case R.id.btn3:
                placePawn(3);
                break;
            case R.id.btn4:
                placePawn(4);
                break;
            case R.id.btn5:
                placePawn(5);
                break;
            case R.id.btn6:
                placePawn(6);
                break;
            case R.id.btn7:
                placePawn(7);
                break;
            case R.id.btn8:
                placePawn(8);
                break;
        }
    }

    public void placePawn(int noBtn) {
        // No double click on case
        if (btn[noBtn].getText() == "") {
            // Difference between crosses & rounds
            pawnChoice++;

            String pawn;

            if (pawnChoice % 2 == 0) {
                pawn = "X";
                cross[noBtn] = true;
                btn[noBtn].setTextColor(getResources().getColor(R.color.player1));
            } else {
                pawn = "O";
                round[noBtn] = true;
                btn[noBtn].setTextColor(getResources().getColor(R.color.player2));
            }
            box[noBtn] = true;

            btn[noBtn].setText(pawn);

            stats();
        }
    }

    public void stats() {
        /*Crosses*/
        // Lines
        if ((cross[0] && cross[1] && cross[2]) || (cross[3] && cross[4] && cross[5]) || (cross[6] && cross[7] && cross[8]))
            victoryCross();

        // Columns
        if ((cross[0] && cross[3] && cross[6]) || (cross[1] && cross[4] && cross[7]) || (cross[2] && cross[5] && cross[8]))
            victoryCross();

        // Diagonals
        if ((cross[0] && cross[4] && cross[8]) || (cross[2] && cross[4] && cross[6]))
            victoryCross();

        /*Rounds*/
        // Lines
        if ((round[0] && round[1] && round[2]) || (round[3] && round[4] && round[5]) || (round[6] && round[7] && round[8]))
            victoryRound();

        // Columns
        if ((round[0] && round[3] && round[6]) || (round[1] && round[4] && round[7]) || (round[2] && round[5] && round[8]))
            victoryRound();

        // Diagonals
        if ((round[0] && round[4] && round[8]) || (round[2] && round[4] && round[6]))
            victoryRound();

        /*If grid is full*/
        if (box[0] && box[1] && box[2] && box[3] && box[4] && box[5] && box[6] && box[7] && box[8] && !victory)
            equality();
    }

    // Player 1 wins
    public void victoryCross() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.victory)
                .setTitle(getString(R.string.victory))
                .setMessage(getString(R.string.victory_p1))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.replay();
                    }
                })
                .setCancelable(false)
                .show();

        // Increment score
        scoreJ1++;
        m_ScoreJ1.setText(getString(R.string.score_p1, scoreJ1, username));

        victory = true;
    }

    // Player 2 wins
    public void victoryRound() {

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.computer)
                .setTitle(getString(R.string.defeat))
                .setMessage(getString(R.string.victory_p2))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.replay();
                    }
                })
                .setCancelable(false)
                .show();
        // Increment score
        scoreJ2++;
        m_ScoreJ2.setText(getString(R.string.score_p2, scoreJ2));
    }

    // if grid is full
    public void equality() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(getString(R.string.equality))
                .setMessage(getString(R.string.nobody_won))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MainActivity.this.replay();
                    }
                })
                .show();

        victory = true;
    }

    public void replay() {
        pawnChoice = 1;
        // Reset cases
        for (int i = 0; i <= 8; i++) {
            cross[i] = false;
            round[i] = false;
            box[i] = false;
        }

        // Reset texts
        for (int i = 0; i <= 8; i++) {
            btn[i].setText("");
        }

        victory = false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.leave)
                .setMessage(R.string.stop_game_progress)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }
}