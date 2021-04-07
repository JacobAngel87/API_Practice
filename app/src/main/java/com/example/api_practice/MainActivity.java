package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    /* All of the code in this class is subject to change and will not be documented
       All we are doing is getting an imageView and a button and defining those elements.
       We then add an onClick eventListener that gets a request from the dog.ceo API.
       After we get the data back we find the message from the json and then use Picasso to put
       the img into our image view. */

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

                // parse data
                apiRequester.requestAPI("https://api.imgflip.com/caption_image", new VolleyCallBack() {
                    @Override
                    public void onSuccess(String data) {

                    }});
            }
        });
        changeDogBtn.callOnClick();
    }
}