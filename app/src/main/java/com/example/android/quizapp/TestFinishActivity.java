package com.example.android.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TestFinishActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_finish);
        Bundle bundle = getIntent().getExtras();
        float mark = bundle.getFloat("mark");
        textView=(TextView) findViewById(R.id.yourScore);
        textView.setTextSize(35);
        textView.setText("Your mark is: "+ mark);
    }
}
