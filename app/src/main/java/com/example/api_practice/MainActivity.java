package com.example.api_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ApiRequester apiRequester;
    private List<Meme> memes;
    private Spinner memeSpinner;
    private Button submitButton;
    private final String getMemesUrl = "https://api.imgflip.com/get_memes";
    private EditText topText;
    private EditText bottomText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        memeSpinner = findViewById(R.id.spnMemes);
        submitButton = findViewById(R.id.btnSubmit);
        topText = findViewById(R.id.editTextTopText);
        bottomText = findViewById(R.id.editTextBottomText);
        apiRequester = new ApiRequester(this);
        memes = new ArrayList<Meme>();
        makeMemes();
        addMemesToSpinner();

        // Meme Img Preview
        memeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                apiRequester.requestAPIGet(getMemesUrl, new VolleyCallBack() {
                    @Override
                    public void onSuccess(String data) {

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // Generate Meme Button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topTextInput = topText.getText().toString();
                String bottomTextInput = bottomText.getText().toString();
                int memePosition = memeSpinner.getSelectedItemPosition();
                int memeID = memes.get(memePosition).id;
                if(topTextInput.length() < 1 || bottomTextInput.length() < 1) {
                    Toast.makeText(getApplicationContext(), "Fields cannot be blank", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        });
    }

    private void makeMemes() {
        memes.add(new Meme("Distracted Boyfriend", 112126428));
        memes.add(new Meme("Batman Slapping Robin", 438680));
        memes.add(new Meme("Expanding Brain", 93895088));
        memes.add(new Meme("Grumpy Cat", 405658));
    }
    private void addMemesToSpinner() {
        List<String> tmpNames = new ArrayList<>();
        for(int i = 0; i < memes.size(); i++) {
            tmpNames.add(memes.get(i).name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, tmpNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = findViewById(R.id.spnMemes);
        sItems.setAdapter(adapter);
    }
}