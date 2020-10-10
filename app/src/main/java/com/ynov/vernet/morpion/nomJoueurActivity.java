package com.ynov.vernet.morpion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class nomJoueurActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nom_joueur);

        Button btnJouer = findViewById(R.id.btnJouer);

        btnJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nomJoueur1 = findViewById(R.id.nomJoueur1);
                Intent intent = new Intent(nomJoueurActivity.this, MainActivity.class);
                intent.putExtra("nomJoueur1", nomJoueur1.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }
}