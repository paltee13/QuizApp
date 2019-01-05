package com.example.android.quizapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.quizapp.database.DatabaseHelper;
import com.example.android.quizapp.database.LoginUtility;
import com.example.android.quizapp.json.JsonParser;
import com.example.android.quizapp.model.User;
import com.example.android.quizapp.network.HttpClient;
import com.example.android.quizapp.util.Util;

import java.io.IOException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button aboutButton;
    private EditText usernameField;
    private EditText passwordField;
    private TextView textView;
    private LoginUtility loginUtility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuoteTask quoteTask=new QuoteTask();
        quoteTask.execute();

        final DatabaseHelper myDbHelper;
        myDbHelper = new DatabaseHelper(this);

        try {

            myDbHelper.createDataBase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        myDbHelper.openDataBase();

        loginUtility = new LoginUtility(this);
        loginUtility = loginUtility.openDB();

        loginButton = (Button)findViewById(R.id.login_button);
        aboutButton = (Button)findViewById(R.id.about_button);
        usernameField = (EditText)findViewById(R.id.username_field);
        passwordField = (EditText)findViewById(R.id.password_field);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Username: ", usernameField.getText().toString());
                Log.i("Password: ", passwordField.getText().toString());
                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();
                String storedPassword = loginUtility.getUser(username, LoginUtility.USER_PASSWORD);

                if(password.equals(storedPassword)) {

                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    String userType = loginUtility.getUser(username, LoginUtility.USER_TYPE);

                    if(Integer.valueOf(userType) == 0) {

                        User loggedUser = new User();
                        loggedUser.setUserName(username);
                        loggedUser.setUserId(Integer.valueOf(loginUtility.getUser(username, LoginUtility.USER_ID)));

                        myDbHelper.close();
                        loginUtility.closeDB();

                        Intent new_activity = new Intent(MainActivity.this, ProfessorActivity.class);
                        new_activity.putExtra("loggedUser", loggedUser);
                        MainActivity.this.startActivity(new_activity);

                    } else if (Integer.valueOf(userType) == 1) {

                        User loggedUser = new User();
                        loggedUser.setUserName(username);
                        loggedUser.setUserId(Integer.valueOf(loginUtility.getUser(username, LoginUtility.USER_ID)));

                        myDbHelper.close();
                        loginUtility.closeDB();

                        Intent new_activity = new Intent(MainActivity.this, ProfessorActivity.class);
                        new_activity.putExtra("loggedUser", loggedUser);
                        MainActivity.this.startActivity(new_activity);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Username or password wrong", Toast.LENGTH_LONG).show();
                }
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
