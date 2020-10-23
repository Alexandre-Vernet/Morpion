package com.ynov.vernet.morpion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class nomJoueurActivity extends AppCompatActivity {

    EditText nomJoueur1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nom_joueur);

        // Référencer la zone de texte
        nomJoueur1 = findViewById(R.id.nomJoueur1);

        // Récupérer le nom du joueur 1 s'il existe
        SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
        String nom = prefs.getString("nom", null);

        if (nom != null)
            // Afficher le contenu de la mémoire
            nomJoueur1.setText("" + nom);

        // Au clic du bouton jouer
        Button btnJouer = findViewById(R.id.btnJouer);
        btnJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Stocker le nom du joueur saisi précédemment
                SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nom", "" + nomJoueur1.getText());
                editor.apply();

                // Démarrer l'activité principale
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Si le joueur appui sur le bouton entrée de son clavier
        nomJoueur1.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Stocker le nom du joueur saisi précédemment
                    SharedPreferences prefs = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("nom", "" + nomJoueur1.getText());
                    editor.apply();

                    // Démarrer l'activité principale
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
}