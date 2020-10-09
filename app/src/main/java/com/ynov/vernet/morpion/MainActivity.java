package com.ynov.vernet.morpion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
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


        // Mode 1 joueur
        Button m_btn1Joueur = (Button) findViewById(R.id.btn1Joueur);
        m_btn1Joueur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UnJoueurActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Mode 2 joueurs
        Button m_btn2Joueurs = (Button) findViewById(R.id.btn2Joueurs);
        m_btn2Joueurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DeuxJoueursActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // A propos
        Button m_btnAPropos = (Button) findViewById(R.id.btnAPropos);
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
        Button m_btnQuitter = (Button) findViewById(R.id.btnQuitter);
        m_btnQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}