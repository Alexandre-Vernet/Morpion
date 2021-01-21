package com.ynov.vernet.morpion;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class TwoPlayersActivity extends AppCompatActivity implements View.OnClickListener {

    // Crosses & Rounds
    private final boolean[] cross = new boolean[9];
    private final boolean[] round = new boolean[9];
    private final boolean[] box = new boolean[9];
    private boolean victory = false;

    // Scores
    TextView m_ScoreJ1, m_ScoreJ2;
    private int scoreJ1, scoreJ2 = 0;

    TextView[] btn = new TextView[9];

    private int choixPion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_players);

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
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                placementPion(0);
                break;
            case R.id.btn1:
                placementPion(1);
                break;
            case R.id.btn2:
                placementPion(2);
                break;
            case R.id.btn3:
                placementPion(3);
                break;
            case R.id.btn4:
                placementPion(4);
                break;
            case R.id.btn5:
                placementPion(5);
                break;
            case R.id.btn6:
                placementPion(6);
                break;
            case R.id.btn7:
                placementPion(7);
                break;
            case R.id.btn8:
                placementPion(8);
                break;
        }
    }

    public void placementPion(int noBtn) {
        // No double click on case
        if (btn[noBtn].getText() == "") {
            // Difference between crosses & rounds
            choixPion++;

            String pion;

            if (choixPion % 2 == 0) {
                pion = "X";
                cross[noBtn] = true;
                btn[noBtn].setTextColor(getResources().getColor(R.color.joueur1));
            } else {
                pion = "O";
                round[noBtn] = true;
                btn[noBtn].setTextColor(getResources().getColor(R.color.joueur2));
            }
            box[noBtn] = true;

            btn[noBtn].setText(pion);

            stats();
        }
    }

    public void stats() {
        /*Crosses*/
        // Lines
        if ((cross[0] && cross[1] && cross[2]) || (cross[3] && cross[4] && cross[5]) || (cross[6] && cross[7] && cross[8]))
            victorycross();

        // Columns
        if ((cross[0] && cross[3] && cross[6]) || (cross[1] && cross[4] && cross[7]) || (cross[2] && cross[5] && cross[8]))
            victorycross();

        // Diagonals
        if ((cross[0] && cross[4] && cross[8]) || (cross[2] && cross[4] && cross[6]))
            victorycross();

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
    @SuppressLint("SetTextI18n")
    public void victorycross() {
        AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayersActivity.this).create();
        alertDialog.setTitle(getString(R.string.victory));
        alertDialog.setMessage(getString(R.string.victory_J1));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        replay();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        // Increment score
        scoreJ1++;
        m_ScoreJ1.setText(getString(R.string.score_j1) + scoreJ1);

        victory = true;
    }

    // Player 2 wins
    @SuppressLint("SetTextI18n")
    public void victoryRound() {
        AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayersActivity.this).create();
        alertDialog.setTitle(getString(R.string.victory));
        alertDialog.setMessage(getString(R.string.victory_J2));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        replay();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        // Increment score
        scoreJ2++;
        m_ScoreJ2.setText(getString(R.string.score_J2) + scoreJ2);
    }

    // if grid is full
    public void equality() {
        AlertDialog alertDialog = new AlertDialog.Builder(TwoPlayersActivity.this).create();
        alertDialog.setTitle(getString(R.string.equality));
        alertDialog.setMessage(getString(R.string.personne_n_a_gagne));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        replay();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        victory = true;
    }

    public void replay() {
        choixPion = 1;
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

    // Button back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}