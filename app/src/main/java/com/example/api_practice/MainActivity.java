package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private String data;
    private ImageView dogImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dogImg = findViewById(R.id.imgDog);
        final TextView textView = (TextView) findViewById(R.id.txt_data);

        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="https://dog.ceo/api/breeds/image/random";
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        data = new JSONObject(response).getString("message");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    textView.setText(data);
                    Picasso.get().load(data).into(dogImg);
                }, error -> textView.setText("That didn't work!"));

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}