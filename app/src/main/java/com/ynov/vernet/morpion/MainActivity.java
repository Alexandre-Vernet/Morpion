package com.ynov.vernet.morpion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Différencier les croix des ronds
    private String pion = "";

    // Gérer les scores entre croix et rond
    private boolean[] croix = new boolean[9];
    private boolean[] rond = new boolean[9];
    private boolean[] box = new boolean[9];

    // Regrouper les boutons dans un tableau
    TextView[] btn = new TextView[9];

    // Permet de permuter entre le pion croix ou rond
    private int choixPion = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Référencer les boutons
        btn[0] = (TextView) findViewById(R.id.btn0);
        btn[0].setOnClickListener(this);

        btn[1] = (TextView) findViewById(R.id.btn1);
        btn[1].setOnClickListener(this);

        btn[2] = (TextView) findViewById(R.id.btn2);
        btn[2].setOnClickListener(this);

        btn[3] = (TextView) findViewById(R.id.btn3);
        btn[3].setOnClickListener(this);

        btn[4] = (TextView) findViewById(R.id.btn4);
        btn[4].setOnClickListener(this);

        btn[5] = (TextView) findViewById(R.id.btn5);
        btn[5].setOnClickListener(this);

        btn[6] = (TextView) findViewById(R.id.btn6);
        btn[6].setOnClickListener(this);

        btn[7] = (TextView) findViewById(R.id.btn7);
        btn[7].setOnClickListener(this);

        btn[8] = (TextView) findViewById(R.id.btn8);
        btn[8].setOnClickListener(this);
    }

    // Au clic d'un bouton
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

    // 
    public void placementPion(int noBtn) {
        // Choix du pion en fonction du joueur 1 ou 2
        choixPion++;
        if (choixPion % 2 == 0) {
            pion = "X";
            croix[noBtn] = true;
        } else {
            pion = "O";
            rond[noBtn] = true;
        }
        box[noBtn] = true;

        // Placement du pion au clic du bouton
        btn[noBtn].setText(pion);

        // Vérifier si un joueur a gagné
        stats();
    }

    public void stats() {
        /*Croix*/
        // Ligne
        if ((croix[0] && croix[1] && croix[2]) || (croix[3] && croix[4] && croix[5]) || (croix[6] && croix[7] && croix[8]))
            victoireCroix();

        // Colonne
        if ((croix[0] && croix[3] && croix[6]) || (croix[1] && croix[4] && croix[7]) || (croix[2] && croix[5] && croix[8]))
            victoireCroix();

        // Diagonales
        if ((croix[0] && croix[4] && croix[8]) || (croix[2] && croix[4] && croix[6]))
            victoireCroix();

        /*Rond*/
        // Ligne
        if ((rond[0] && rond[1] && rond[2]) || (rond[3] && rond[4] && rond[5]) || (rond[6] && rond[7] && rond[8]))
            victoireRond();

        // Colonne
        if ((rond[0] && rond[3] && rond[6]) || (rond[1] && rond[4] && rond[7]) || (rond[2] && rond[5] && rond[8]))
            victoireRond();

        // Diagonales
        if ((rond[0] && rond[4] && rond[8]) || (rond[2] && rond[4] && rond[6]))
            victoireRond();

        // Si la grille est pleine
        if (box[0] && box[1] && box[2] && box[3] && box[4] && box[5] && box[6] && box[7] && box[8])
            egalite();
    }

    // Si le joueur 1 gagne
    public void victoireCroix() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Victoire");
        alertDialog.setMessage("Le joueur 1 gagne !");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    // Si le joueur 2 gagne
    public void victoireRond() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Victoire");
        alertDialog.setMessage("Le joueur 2 gagne !");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    // Si la grille est rempli et que personne n'a gagné
    public void egalite() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Egalité");
        alertDialog.setMessage("Personne n'a gagné !");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}