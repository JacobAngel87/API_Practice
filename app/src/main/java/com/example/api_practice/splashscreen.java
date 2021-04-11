package com.example.api_practice;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class splashscreen extends AppCompatActivity {
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
             Intent intent = new Intent(this , MainActivity.class);
             Timer t = new Timer();
             TimerTask tt = new TimerTask() {
                 @Override
                 public void run() {
                     startActivity(intent);
                     finish();
                 }
             };
             t.schedule(tt, 3000);
    }
}
