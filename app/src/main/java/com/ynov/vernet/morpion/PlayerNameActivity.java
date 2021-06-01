package com.ynov.vernet.morpion;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerNameActivity extends AppCompatActivity {

    EditText editTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_player);

        editTextUsername = findViewById(R.id.editTextUsername);

        // Button play
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check empty editText
                if (editTextUsername.getText().toString().isEmpty()) {
                    editTextUsername.setError(getString(R.string.cannot_be_empty));
                    return;
                }

                // Get username
                String username = editTextUsername.getText().toString();

                // Save in memory
                SharedPreferences sp = getSharedPreferences("username", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("username", username);
                editor.apply();

                // Start main menu
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        // Enter keyboard
        editTextUsername.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    // Check empty editText
                    if (editTextUsername.getText().toString().isEmpty()) {
                        editTextUsername.setError(getString(R.string.cannot_be_empty));
                        return false;
                    }

                    // Get username
                    String username = editTextUsername.getText().toString();

                    // Save in memory
                    SharedPreferences sp = getSharedPreferences("username", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username", username);
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