package com.example.api_practice;

import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DogAPI {
    private StringRequest apiCall;
    private final String url = "https://randomuser.me/api";
    private JSONObject results;

    public DogAPI() {
        getNewDog();
    }

    public void getNewDog() {
        apiCall = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        results = new JSONObject(response).getJSONObject("list");
                        Log.e("JSON DATA", "" + results);
                    }
                    catch (JSONException e) {
                        // TODO Error Catching
                        Log.e("ERROR", "JSON Data Parsing Failed");
                    }
                },
                error -> {
                    Log.e("ERROR", "API Request Error");
                }
        );
    }

    public String getDogImg() throws JSONException {
        if(results == null) return "";
        return results.getString("message");
    }
}