package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button takeQuizButton;
    private Button viewGradesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        welcomeText = (TextView)findViewById(R.id.welcomeTextView);
        takeQuizButton = (Button)findViewById(R.id.takeQuizButton);
        viewGradesButton  = (Button)findViewById(R.id.viewGradesButton);

        Intent i = getIntent();
        final User loggedUser = (User)i.getSerializableExtra("loggedUser");

        welcomeText.setText(getString(R.string.welcome) + loggedUser.getUserName());

    }
}
