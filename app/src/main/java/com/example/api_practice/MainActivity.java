package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Api requester object
    private ApiRequester apiRequester;
    // List of memes
    private List<Meme> memes;
    // UI Elements
    private Spinner memeSpinner;
    private Button submitButton;
    private EditText topText;
    private EditText bottomText;
    private ImageView memeImg;
    // JSON array of memes that will be returned from the api
    private JSONArray memesJSON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Declare our UI elements
        memeSpinner = findViewById(R.id.spnMemes);
        submitButton = findViewById(R.id.btnSubmit);
        topText = findViewById(R.id.editTextTopText);
        bottomText = findViewById(R.id.editTextBottomText);
        memeImg = findViewById(R.id.imgMemePreview);

        // Declare our apiRequester
        apiRequester = new ApiRequester(this);

        // Declare our list of memes
        memes = new ArrayList<>();

        // Get some memes from our API
        getMemesFromAPI();

        // Spinner itemSelected Listener
        memeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            // Whenever we select an item from the spinner
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Check if the json has stuff in it
                if(memesJSON == null) {
                    // If it doesn't, go get the data and return out of this function
                    getMemesFromAPI();
                    return;
                }
                // Get the id of the currently selected meme
                int currentID = memes.get(position).id;

                // Find the meme in the JSON data
                for(int i = 0; i < memesJSON.length(); i++) {
                    try {
                        int memeID = memesJSON.getJSONObject(i).getInt("id");
                        // Once we found the right ID
                        if(memeID == currentID) {
                            // Get the img url of the meme
                            String imgUrl = memesJSON.getJSONObject(i).getString("url");
                            // Put the img into our memeImg element
                            Picasso.get().load(imgUrl).into(memeImg);
                            break;
                        }
                    } catch (JSONException e) { // Catches any JSON parsing error
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //TODO Auto generated
            }
        });

        // Generate meme onClick listener
        submitButton.setOnClickListener(v -> {
            // Get the data needed for our POST request
            String topTextInput = topText.getText().toString();
            String bottomTextInput = bottomText.getText().toString();
            int memePosition = memeSpinner.getSelectedItemPosition();
            int memeID = memes.get(memePosition).id;
            // Make sure that the user input isn't blank
            if(topTextInput.length() < 1 || bottomTextInput.length() < 1) return;

            apiRequester.getMeme(memeID, topTextInput, bottomTextInput, new VolleyCallBack() {
                @Override
                public void onSuccess(Object data) {
                    String url = (String) data;
                    Intent resultsScreen = new Intent(MainActivity.this, MemeScreen.class);
                    resultsScreen.putExtra("IMG_URL", url);
                    startActivity(resultsScreen);
                }
            });

        });
        // Calls our onItemSelectedListener so that we start with an image
        memeSpinner.setSelection(0);
    }
    // Gets all of the memes from the API
    private void getMemesFromAPI() {
        // Make a GET request
        apiRequester.getMemeList(new VolleyCallBack() {
            @Override
            public void onSuccess(Object data) {
                memesJSON = (JSONArray) data;
                makeMemes();
            }
        });
    }
    // Adds the memes from the api to our memes list
    private void makeMemes() {
        // Add every meme from the api to our list
        for(int i = 0; i < memesJSON.length(); i++) {
            try {
                // Get the name and id from the JSON
                int memeID = memesJSON.getJSONObject(i).getInt("id");
                String memeName = memesJSON.getJSONObject(i).getString("name");
                // Adds the meme to our list of memes
                memes.add(new Meme(memeName, memeID));
            } catch (JSONException e) { // Catches any JSON parsing error
                e.printStackTrace();
            }
        }
        // After we add the memes to our list add them to our spinner
        addMemesToSpinner();
    }
    private void addMemesToSpinner() {
        // Get all of the names from our meme list
        List<String> tmpNames = new ArrayList<>();
        for(int i = 0; i < memes.size(); i++) {
            tmpNames.add(memes.get(i).name);
        }
        // Copied straight from StackOverflow
        // Adds all of the items from our names list as new spinner entries
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, tmpNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.spnMemes);
        sItems.setAdapter(adapter);
    }
}