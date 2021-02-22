package com.ynov.vernet.morpion;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.concurrent.ThreadLocalRandom;

public class OnePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    // Create score
    TextView m_ScoreJ1, m_ScoreJ2;
    TextView[] btn = new TextView[9];

    String namePlayer1 = "";

    // Difference between crosses & rounds
    private final boolean[] cross = new boolean[9]; /*Player*/
    private final boolean[] round = new boolean[9]; /*Computer*/
    private final boolean[] box = new boolean[9];
    private boolean victory = false;
    private int scoreJ1, scoreJ2 = 0;

    // Debug
    private static final String TAG = "OnePlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_player);

        // Get name
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        namePlayer1 = prefs.getString("namePlayer1", null);
        if (namePlayer1 == null) {
            startActivity(new Intent(getApplicationContext(), PlayerNameActivity.class));
            finish();
        }

        // Scores
        m_ScoreJ1 = findViewById(R.id.scoreJ1);
        m_ScoreJ2 = findViewById(R.id.scoreJ2);

        m_ScoreJ1.setText(namePlayer1 + " : " + scoreJ1);
        m_ScoreJ2.setText(getString(R.string.computer, scoreJ2));

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
                playerPlacement(0);
                break;
            case R.id.btn1:
                playerPlacement(1);
                break;
            case R.id.btn2:
                playerPlacement(2);
                break;
            case R.id.btn3:
                playerPlacement(3);
                break;
            case R.id.btn4:
                playerPlacement(4);
                break;
            case R.id.btn5:
                playerPlacement(5);
                break;
            case R.id.btn6:
                playerPlacement(6);
                break;
            case R.id.btn7:
                playerPlacement(7);
                break;
            case R.id.btn8:
                playerPlacement(8);
                break;
        }
    }

    // Player choice
    public void playerPlacement(int noBtn) {
        // Don't double click
        if (!box[noBtn]) {
            cross[noBtn] = true;
            box[noBtn] = true;
            btn[noBtn].setTextColor(getResources().getColor(R.color.joueur1));
            btn[noBtn].setText("X");


            // Check stats
            stats();

            // Wait 1/2s and computer's turn
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    computerPlacement();
                }
            }, 500);
        }
    }

    public int chooseNumber() {
        return ThreadLocalRandom.current().nextInt(0, 8);
    }

    // Computer choice
    public void computerPlacement() {
        // Choose number between 0 & 8
        int computerChoice = chooseNumber();

        // if computer chose a wrong case
        for (int i = 0; i < 8; i++)
            if (computerChoice == i && !box[i])
                computerPlacement();

        Log.d(TAG, "computerPlacement: " + computerChoice);

        round[computerChoice] = true;
        box[computerChoice] = true;
        btn[computerChoice].setTextColor(getResources().getColor(R.color.joueur2));
        btn[computerChoice].setText("O");

        stats();
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

    // Player win
    @SuppressLint("SetTextI18n")
    public void victoryCross() {
        AlertDialog alertDialog = new AlertDialog.Builder(OnePlayerActivity.this).create();
        alertDialog.setTitle(getString(R.string.victory));
        alertDialog.setMessage(getString(R.string.gagne));
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
        m_ScoreJ1.setText(namePlayer1 + " : " + scoreJ1);

        victory = true;
    }

    // Computer win
    @SuppressLint("SetTextI18n")
    public void victoryRound() {
        AlertDialog alertDialog = new AlertDialog.Builder(OnePlayerActivity.this).create();
        alertDialog.setTitle(getString(R.string.defaite));
        alertDialog.setMessage(getString(R.string.perdu));
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
        m_ScoreJ2.setText(getString(R.string.computer, scoreJ2));
    }

    // Grid full
    public void equality() {
        AlertDialog alertDialog = new AlertDialog.Builder(OnePlayerActivity.this).create();
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
        // Reset cases
        for (int i = 0; i <= 8; i++) {
            cross[i] = false;
            round[i] = false;
            box[i] = false;
        }

        // Reset text
        for (int i = 0; i <= 8; i++) {
            btn[i].setText("");
        }

        victory = false;
    }

    // Button back
    @Override
    public void onBackPressed() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.leave)
                .setMessage(R.string.stop_game_progress)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        OnePlayerActivity.super.onBackPressed();
                        OnePlayerActivity.this.startActivity(new Intent(OnePlayerActivity.this.getApplicationContext(), MainActivity.class));
                        OnePlayerActivity.this.finish();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
        alertDialog.setCanceledOnTouchOutside(false);
    }
}