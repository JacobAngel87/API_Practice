package com.example.api_practice;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ApiRequester {

    // API request stored in this variable
    private StringRequest stringRequest;
    // Queue for our API request
    private RequestQueue queue;
    // The http headers
    private Map<String,String> headers = new HashMap<>();

    // URLs
    private final String MEME_LIST_URL = "https://api.imgflip.com/get_memes";
    private final String MEME_URL = "https://api.imgflip.com/caption_image";

    // Account Information
    private final String USERNAME = "cc0217891";
    private final String PASSWORD = "SecurePassword1234";

    // Constructor defines the queue that we created
    // Uses the MainActivity for context
    public ApiRequester(MainActivity main) {
        queue = Volley.newRequestQueue(main.getApplicationContext());
    }

    // This method accepts a Request.Method.[POST/GET], a url, and a volley callback and returns a String to the callback.
    // Null is returned if there was an error
    public void requestAPI(int method, String url, final VolleyCallBack callBack) {
        stringRequest = new StringRequest(method, url,
                // If the data was returned from the API
                response -> {
                    callBack.onSuccess(response);
                },
                // If there was no data returned
                error -> {
                    callBack.onSuccess(null);
                    error.printStackTrace();
                }){
            // Adds the http parameters to the post request
            protected Map<String,String> getParams() {
                // requests will need to add headers to Map before calling the requestAPI method
                return headers;
            }
        };
        // After we create our API request add it to the queue
        // (Note that 'add' also starts the queue)
        queue.add(stringRequest);
    }

    // This method receives the user's request and starts the post request. Once finished, the method calls the callBack method to return the results to the user
    // null is returned if there is an error
    public void getMeme(int memeId, String topText, String bottomText, final VolleyCallBack callBack) {
        // Add the API required headers to the Request
        headers.put("template_id", ""+memeId);
        headers.put("text0", topText);
        headers.put("text1", bottomText);
        headers.put("username", USERNAME);
        headers.put("password", PASSWORD);
        // Call the method that sends the request to the API
        requestAPI(Request.Method.POST, MEME_URL, new VolleyCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                try {
                    // the query is finished. Parse the JSON and send data to the VolleyCallBack
                    callBack.onSuccess(new JSONObject(data).getJSONObject("data").getString("url"));
                } catch (JSONException e) {
                    // Could not parse JSON. Return null to VolleyCallBack and print stack
                    callBack.onSuccess(null);
                    e.printStackTrace();
                }
            }
        });
    }

    // This method requests a list of the top 100 memes from the API.
    // This list will populate a Spinner widget on the MainActivity
    public void getMemeList(final VolleyCallBack callBack){
        // Call the requestAPI method to send a GET request to the API
        requestAPI(Request.Method.GET, MEME_LIST_URL, data -> {
                try {
                    // The request is finished. Parse and return the response to the MemeVolleyCallback
                    callBack.onSuccess(new JSONObject((String) data).getJSONObject("data").getJSONArray("memes"));
                } catch (JSONException e) { // Catches any JSON parsing error
                    e.printStackTrace();
                    callBack.onSuccess(null);
                }
            }
        );
    }


}