package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfessorActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button createTestButton;
    private Button viewGradesBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        welcomeText = (TextView)findViewById(R.id.welcomeTextView);
        createTestButton = (Button)findViewById(R.id.createTestButton);
        viewGradesBut = (Button)findViewById(R.id.viewGradesBut);

        Intent i = getIntent();
        final User loggedUser = (User)i.getSerializableExtra("loggedUser");

        welcomeText.setText(getString(R.string.welcome) + loggedUser.getUserName());

    }
}
