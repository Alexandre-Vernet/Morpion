package com.ynov.vernet.morpion;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerNameActivity extends AppCompatActivity {

    Context context;
    EditText editTexNamePlayer1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_player);

        context = getApplicationContext();
        editTexNamePlayer1 = findViewById(R.id.editTexNamePlayer1);

        // Check player name
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String namePlayer1 = prefs.getString("namePlayer1", null);
        if (namePlayer1 != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        // Button play
        Button btnJouer = findViewById(R.id.btnPlay);
        btnJouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check empty editText
                if (editTexNamePlayer1.getText().toString().isEmpty()) {
                    editTexNamePlayer1.setError(getString(R.string.cannot_be_empty));
                    return;
                }

                // Save player name
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                editor.putString("namePlayer1", editTexNamePlayer1.getText().toString());
                editor.apply();

                // Start main menu
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        // Enter keyboard
        editTexNamePlayer1.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    // Save player name
                    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                    editor.putString("namePlayer1", editTexNamePlayer1.getText().toString());
                    editor.apply();

                    // Start main menu
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                    return true;
                }
                return false;
            }
        });
    }
}