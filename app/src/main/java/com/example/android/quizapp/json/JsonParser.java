package com.example.android.quizapp.json;

import com.example.android.quizapp.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    public String getQuote(String data){
        String s="";
        try{

            JSONArray jsonArray=new JSONArray(data);

            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                s = Util.getString("quote", jsonObject)+"\n - "+Util.getString("author",jsonObject);
            }



        }catch (JSONException e){
            e.printStackTrace();
        }
        return s;
    }


}
