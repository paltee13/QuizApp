package com.example.android.quizapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.quizapp.json.JsonParser;
import com.example.android.quizapp.model.User;
import com.example.android.quizapp.network.HttpClient;
import com.example.android.quizapp.util.Util;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button aboutButton;
    private EditText usernameField;
    private EditText passwordField;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuoteTask quoteTask=new QuoteTask();
        quoteTask.execute();

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

    public class QuoteTask extends AsyncTask<Void,Void, String> {


        @Override
        protected String doInBackground(Void... voids) {
            HttpClient httpClient=new HttpClient();
            JsonParser jsonParser=new JsonParser();
            String data=httpClient.getDefaultList(Util.BASE_URL);
            String quote=jsonParser.getQuote(data);

            return quote;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            textView=(TextView) findViewById(R.id.quote_zone);
            textView.setText("Quote of the day:\n" + s);
        }
    }

}
