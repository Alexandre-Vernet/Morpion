package com.ynov.vernet.morpion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Afficher le nom du joueur
        TextView nomJoueur1 = findViewById(R.id.nomJoueur1);
        Intent intent = getIntent();
        String str = intent.getStringExtra("nomJoueur1");
        nomJoueur1.setText("Bienvenue " + str + " !");

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