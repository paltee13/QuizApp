package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.quizapp.model.User;

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

        welcomeText.setText(getString(R.string.welcome) + ", " + loggedUser.getUserName());

        createTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_activity = new Intent(ProfessorActivity.this, CreateTestActivity.class);
                new_activity.putExtra("logged", loggedUser);
                ProfessorActivity.this.startActivity(new_activity);
            }
        });
    }
}
