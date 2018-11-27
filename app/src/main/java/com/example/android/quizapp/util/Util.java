package com.example.android.quizapp.util;

import org.json.JSONException;
import org.json.JSONObject;

public class Util {

    public static final String BASE_URL="https://andruxnet-random-famous-quotes.p.mashape.com/endpoint?mashape-key=oV2ondfLWNmshlct6exm7IMpevb5p1ltpQRjsnOboh6pIpkcG3";


    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        JSONObject jObj=jsonObject.getJSONObject(tagName);
        return jObj;
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(tagName);
    }

    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static int getInt(String tagName,JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);

    }

}
