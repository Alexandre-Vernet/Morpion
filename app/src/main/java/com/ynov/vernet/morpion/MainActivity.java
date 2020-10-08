package com.ynov.vernet.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Différencier les croix des ronds
    private String pion = "";

    // Regrouper les boutons dans un tableau
    TextView[] btn = new TextView[9];

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                choixPion(0);
                break;
            case R.id.btn1:
                choixPion(1);
                break;
            case R.id.btn2:
                choixPion(2);
                break;
            case R.id.btn3:
                choixPion(3);
                break;
            case R.id.btn4:
                choixPion(4);
                break;
            case R.id.btn5:
                choixPion(5);
                break;
            case R.id.btn6:
                choixPion(6);
                break;
            case R.id.btn7:
                choixPion(7);
                break;
            case R.id.btn8:
                choixPion(8);
                break;
        }
    }

    public void choixPion(int noBtn) {
        // Choix du pion en fonction du joueur 1 ou 2
        choixPion++;
        if (choixPion % 2 == 0) {
            pion = "X";
        } else
            pion = "O";

        // Placement du pion au clic du bouton
        btn[noBtn].setText("" + pion);
    }
}