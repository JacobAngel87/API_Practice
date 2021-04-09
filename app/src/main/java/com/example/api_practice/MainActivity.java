package com.example.api_practice;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiRequester apiRequester = new ApiRequester(this);
        apiRequester.getMeme(101470, "this is text on top", "this is bottom text", new VolleyCallBack<String>() {
            @Override
            public void onSuccess(String data) {
                System.out.println("Meme Url: "+data);
            }
        });
        apiRequester.getMemeList(new VolleyCallBack<JSONArray>(){
            @Override
            public void onSuccess(JSONArray memeList) {
                System.out.println("JSONArray of Meme Objects are Here");
            }
        });
    }
}