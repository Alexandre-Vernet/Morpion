package com.ynov.vernet.morpion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textViewPlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.textViewPlayer1);

        // Read player1 name
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String namePlayer1 = prefs.getString("namePlayer1", null);
        textViewPlayer1.setText(namePlayer1);

        // Buttons
        Button btn1Joueur = findViewById(R.id.btn1Player);
        btn1Joueur.setOnClickListener(this);

        Button btn2Joueur = findViewById(R.id.btn2Players);
        btn2Joueur.setOnClickListener(this);

        Button btnAPropos = findViewById(R.id.btnSettings);
        btnAPropos.setOnClickListener(this);

        Button btnQuitter = findViewById(R.id.btnLeave);
        btnQuitter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1Player:
                // 1 player
                startActivity(new Intent(getApplicationContext(), OnePlayerActivity.class));
                finish();
                break;
            case R.id.btn2Players:
                // 2 players
                startActivity(new Intent(getApplicationContext(), TwoPlayersActivity.class));
                finish();
                break;
            case R.id.btnSettings:
                // Settings
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                finish();
                break;
            case R.id.btnLeave:
                // Leave app
                finish();
                break;
        }
    }
}