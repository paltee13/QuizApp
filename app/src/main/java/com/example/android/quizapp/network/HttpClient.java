package com.example.android.quizapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpClient {

    public String getDefaultList(String my_URL){
        HttpURLConnection connection=null;
        InputStream inputStream=null;
        try{
            connection=(HttpURLConnection)(new URL(my_URL)).openConnection();

            //reading response
            StringBuffer stringBuffer=new StringBuffer();
            inputStream =connection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line=null;
            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append((line+"\r\n"));
            }
            inputStream.close();
            return  stringBuffer.toString();

        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        return null;
    }

}
