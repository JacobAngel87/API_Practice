package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    /***************
    // -- Pain -- \\
    ***************/

    private ImageView dogImg;
    private ApiRequester apiRequester;
    private final String apiURL = "https://dog.ceo/api/breeds/image/random";
    private Button changeDogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dogImg = findViewById(R.id.imgDog);
        changeDogBtn = findViewById(R.id.btnChange);
        changeDogBtn.setVisibility(View.INVISIBLE);
        apiRequester = new ApiRequester(this);

        changeDogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiRequester.requestAPI(apiURL, new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        String data = apiRequester.getData();
                        try {
                            String imgUrl = new JSONObject(data).getString("message");
                            Picasso.get().load(imgUrl).into(dogImg);
                            if(changeDogBtn.getVisibility() == View.INVISIBLE)
                                changeDogBtn.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        changeDogBtn.callOnClick();
    }
}