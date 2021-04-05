package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private ImageView dogImg;
    private DogAPI dogAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dogImg = findViewById(R.id.imgDog);
        dogAPI = new DogAPI();
        /*try {
            Picasso.get().load(dogAPI.getDogImg()).into(dogImg);
        } catch (JSONException e) {
            e.printStackTrace();
        } */
    }
}