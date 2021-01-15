package com.ynov.vernet.morpion;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import java.util.concurrent.ThreadLocalRandom;

public class OnePlayerActivity extends AppCompatActivity implements View.OnClickListener {

    // Create score
    TextView m_ScoreJ1, m_ScoreJ2;

    String namePlayer1 = "";

    TextView[] btn = new TextView[9];

    // Difference between crosses & rounds
    private final boolean[] croix = new boolean[9];
    private final boolean[] rond = new boolean[9];
    private final boolean[] box = new boolean[9];
    private boolean victoire = false;
    private int scoreJ1, scoreJ2 = 0;

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
                placementPionJoueur(0);
                break;
            case R.id.btn1:
                placementPionJoueur(1);
                break;
            case R.id.btn2:
                placementPionJoueur(2);
                break;
            case R.id.btn3:
                placementPionJoueur(3);
                break;
            case R.id.btn4:
                placementPionJoueur(4);
                break;
            case R.id.btn5:
                placementPionJoueur(5);
                break;
            case R.id.btn6:
                placementPionJoueur(6);
                break;
            case R.id.btn7:
                placementPionJoueur(7);
                break;
            case R.id.btn8:
                placementPionJoueur(8);
                break;
        }
    }

    // Player choice
    public void placementPionJoueur(int noBtn) {
        // Don't double click
        if (btn[noBtn].getText() == "") {
            croix[noBtn] = true;
            btn[noBtn].setTextColor(getResources().getColor(R.color.joueur1));
            box[noBtn] = true;

            btn[noBtn].setText("X");

            // Check stats
            stats();

            // Wait 1s and computer's turn
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    choixPionOrdi();
                }
            }, 1000);
        }
    }

    // Computer choice
    public void choixPionOrdi() {
        // Choisir un nombre entre 0 et 8
        int choixOrdi = ThreadLocalRandom.current().nextInt(0, 8);

        // if computer chose a wrong case
        for (int i = 0; i <= 9; i++)
            while (choixOrdi == i && box[i])
                // Re-tirer un nombre
                choixOrdi = ThreadLocalRandom.current().nextInt(0, 8);

        placementPionOrdi(choixOrdi);
    }

    // Computer playing
    public void placementPionOrdi(int noBtn) {
        if (!victoire) {
            rond[noBtn] = true;
            btn[noBtn].setTextColor(getResources().getColor(R.color.joueur2));

            btn[noBtn].setText("O");

            stats();
        }
    }

    // Gestion des manches gagnées
    public void stats() {
        /*Crosses*/
        // Lines
        if ((croix[0] && croix[1] && croix[2]) || (croix[3] && croix[4] && croix[5]) || (croix[6] && croix[7] && croix[8]))
            victoireCroix();

        // Columns
        if ((croix[0] && croix[3] && croix[6]) || (croix[1] && croix[4] && croix[7]) || (croix[2] && croix[5] && croix[8]))
            victoireCroix();

        // Diagonals
        if ((croix[0] && croix[4] && croix[8]) || (croix[2] && croix[4] && croix[6]))
            victoireCroix();

        /*Rounds*/
        // Lines
        if ((rond[0] && rond[1] && rond[2]) || (rond[3] && rond[4] && rond[5]) || (rond[6] && rond[7] && rond[8]))
            victoireRond();

        // Columns
        if ((rond[0] && rond[3] && rond[6]) || (rond[1] && rond[4] && rond[7]) || (rond[2] && rond[5] && rond[8]))
            victoireRond();

        // Diagonals
        if ((rond[0] && rond[4] && rond[8]) || (rond[2] && rond[4] && rond[6]))
            victoireRond();

        /*If grid is full*/
        if (box[0] && box[1] && box[2] && box[3] && box[4] && box[5] && box[6] && box[7] && box[8] && !victoire)
            egalite();
    }

    // Player win
    @SuppressLint("SetTextI18n")
    public void victoireCroix() {
        AlertDialog alertDialog = new AlertDialog.Builder(OnePlayerActivity.this).create();
        alertDialog.setTitle(getString(R.string.victoire));
        alertDialog.setMessage(getString(R.string.gagne));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        rejouer();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        // Increment score
        scoreJ1++;
        m_ScoreJ1.setText(namePlayer1 + " : " + scoreJ1);

        victoire = true;
    }

    // Computer win
    @SuppressLint("SetTextI18n")
    public void victoireRond() {
        AlertDialog alertDialog = new AlertDialog.Builder(OnePlayerActivity.this).create();
        alertDialog.setTitle(getString(R.string.defaite));
        alertDialog.setMessage(getString(R.string.perdu));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        rejouer();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        // Increment score
        scoreJ2++;
        m_ScoreJ2.setText(getString(R.string.computer) + scoreJ2);
    }

    // Grid full
    public void egalite() {
        AlertDialog alertDialog = new AlertDialog.Builder(OnePlayerActivity.this).create();
        alertDialog.setTitle(getString(R.string.egalite));
        alertDialog.setMessage(getString(R.string.personne_n_a_gagne));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        rejouer();
                    }
                });
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        victoire = true;
    }

    public void rejouer() {
        // Reset cases
        for (int i = 0; i <= 8; i++) {
            croix[i] = false;
            rond[i] = false;
            box[i] = false;
        }

        // Reset text
        for (int i = 0; i <= 8; i++) {
            btn[i].setText("");
        }

        victoire = false;
    }

    // Button back
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}