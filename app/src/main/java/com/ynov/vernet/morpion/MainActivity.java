package com.ynov.vernet.morpion;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView nomJoueur1;
    String sNomJoueur1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomJoueur1 = findViewById(R.id.nomJoueur1);

        // Afficher le nom du joueur
        sNomJoueur1 = getIntent().getStringExtra("nomJoueur1");
        nomJoueur1.setText(getString(R.string.bienvenue) + sNomJoueur1);

        // Référencer les boutons
        Button btn1Joueur = findViewById(R.id.btn1Joueur);
        btn1Joueur.setOnClickListener(this);

        Button btn2Joueur = findViewById(R.id.btn2Joueurs);
        btn2Joueur.setOnClickListener(this);

        Button btnAPropos = findViewById(R.id.btnAPropos);
        btnAPropos.setOnClickListener(this);

        Button btnQuitter = findViewById(R.id.btnQuitter);
        btnQuitter.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1Joueur:
                // Démarrer mode 1 joueur
                Intent unJoueur = new Intent(getApplicationContext(), UnJoueurActivity.class);
                startActivity(unJoueur);
                finish();
                break;
            case R.id.btn2Joueurs:
                // Démarrer mode 1 joueur
                Intent deuxJoueurs = new Intent(getApplicationContext(), DeuxJoueursActivity.class);
                startActivity(deuxJoueurs);
                finish();
                break;
            case R.id.btnAPropos:
                // Rediriger vers MyCode
                Intent myCode = new Intent("android.intent.action.VIEW",
                        Uri.parse("http://alexandre-vernet.net"));
                startActivity(myCode);
                break;
            case R.id.btnQuitter:
                // Quitter l'app
                finish();
                break;
        }
    }
}