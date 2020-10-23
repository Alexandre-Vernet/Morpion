package com.ynov.vernet.morpion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TextView nomJoueur1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nomJoueur1 = findViewById(R.id.nomJoueur1);

        // Récupérer le nom du joueur 1 s'il existe
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        String nom = prefs.getString("nom", null);

        if (nom != null)
            // Afficher le nom du joueur
            nomJoueur1.setText("Bienvenue " + nom);

        // Mode 1 joueur
        Button m_btn1Joueur = findViewById(R.id.btn1Joueur);
        m_btn1Joueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UnJoueurActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Mode 2 joueurs
        Button m_btn2Joueurs = findViewById(R.id.btn2Joueurs);
        m_btn2Joueurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeuxJoueursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // A propos
        Button m_btnAPropos = findViewById(R.id.btnAPropos);
        m_btnAPropos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers MyCode
                Intent intent = new Intent("android.intent.action.VIEW",
                        Uri.parse("http://alexandre-vernet.net"));
                startActivity(intent);
            }
        });

        // Quitter l'application
        Button m_btnQuitter = findViewById(R.id.btnQuitter);
        m_btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}