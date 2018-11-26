package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button aboutButton;
    private EditText usernameField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button)findViewById(R.id.login_button);
        aboutButton = (Button)findViewById(R.id.about_button);
        usernameField = (EditText)findViewById(R.id.username_field);
        passwordField = (EditText)findViewById(R.id.password_field);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                User loggedUser = new User();

                loggedUser.setUserName(username);

                Intent new_activity = new Intent(MainActivity.this, StudentActivity.class);
                new_activity.putExtra("loggedUser", loggedUser);
                MainActivity.this.startActivity(new_activity);
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }



}
