package com.example.api_practice;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate (Bundle savedinstanceStat){
        super.onCreate(savedinstanceStat);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
   }
}
