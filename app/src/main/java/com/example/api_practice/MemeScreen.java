package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MemeScreen extends AppCompatActivity {
    private ImageView memeImg;
    private String imgURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_screen);
        memeImg = findViewById(R.id.imgMeme);
        // Get the meme url
        Bundle extras = getIntent().getExtras();
        imgURL = extras.getString("IMG_URL");
        // Make sure there is something there
        if(imgURL != null) {
            // Put that url into the imageView
            Picasso.get().load(imgURL).into(memeImg);
        }
    }
}