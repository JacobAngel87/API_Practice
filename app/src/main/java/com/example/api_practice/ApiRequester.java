package com.example.api_practice;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiRequester {

    /* We need an instance of the MainActivity so
         that volley knows the current context of our application */
    private MainActivity main;
    // API request stored in this variable
    private StringRequest stringRequest;
    // Queue for our API request
    private RequestQueue queue;
    // The data returned from the API stored goes into this variable
    private String data;

    private String id;
    private String topText;
    private String bottomText;

    // Constructor defines the queue that we created
    // Uses the MainActivity for context
    public ApiRequester(MainActivity main) {
        this.main = main;
        queue = Volley.newRequestQueue(main.getApplicationContext());
    }

    // Requests an API call from the user with a URL provided as a parameter.
    /* Uses the VolleyCallBack interface to allow the user to execute code after
         the call has completed inside of the onSuccess method */
    public void requestAPIGet(String url, final VolleyCallBack callBack) {
        stringRequest = new StringRequest(Request.Method.GET, url,
                // If the data was returned from the API
                response -> {
                    // Store the data returned
                    data = response;
                    // Callback the onSuccess method
                    callBack.onSuccess(data);
                },
                // If there was no data returned
                error -> {
                    data = null;
                    error.printStackTrace();
            });
        // After we create our API request add it to the queue
        // (Note that 'add' also starts the queue)
        queue.add(stringRequest);
    }
    public void requestAPIPost(String url, final VolleyCallBack callBack) {
        stringRequest = new StringRequest(Request.Method.POST, url,
                // If the data was returned from the API
                response -> {
                    // Store the data returned
                    data = response;
                    // Callback the onSuccess method
                    callBack.onSuccess(data);
                },
                // If there was no data returned
                error -> {
                    data = null;
                    error.printStackTrace();
                }){
            // Adds the http parameters to the post request
            protected Map<String,String> getParams() {
                Map<String,String> postData = new HashMap<>();
                postData.put("template_id", id);
                postData.put("username", "JacobAngel87");
                postData.put("password", "PizzaMan87!");
                postData.put("text0", topText);
                postData.put("text1", bottomText);
                return postData;
            }
        };
        // After we create our API request add it to the queue
        // (Note that 'add' also starts the queue)
        queue.add(stringRequest);
    }

    // Returns the data from the API
    public String getData() {
        return data;
    }

    // Need to call this method before calling the requestAPIPost
    public void addParametersToPost(int id, String topText, String bottomText) {
        this.id = String.valueOf(id);
        this.topText = topText;
        this.bottomText = bottomText;
    }
}