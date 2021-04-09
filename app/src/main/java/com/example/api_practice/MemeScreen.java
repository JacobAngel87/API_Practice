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
        Bundle extras = getIntent().getExtras();
        imgURL = extras.getString("IMG_URL");
        if(imgURL != null) {
            Picasso.get().load(imgURL).into(memeImg);
        }
    }
}