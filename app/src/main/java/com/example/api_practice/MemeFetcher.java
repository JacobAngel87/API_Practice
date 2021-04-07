package com.example.api_practice;


import android.view.View;

import com.squareup.picasso.Picasso;

private ApiRequester apiRequester;

public class MemeFetcher {

    String memeUrl = "";
    MemeFetcher(int imageId, String topText, String bottomText){

    }

    public void request(cb){
        apiRequester.post(87743020, "top", "bottom", "https://api.imgflip.com/caption_image", new VolleyCallBack() {
        @Override
        public void onSuccess(String data) {
            Picasso.get().load(data).into(dogImg);
            if(changeDogBtn.getVisibility() == View.INVISIBLE)
                changeDogBtn.setVisibility(View.VISIBLE);
        }});
    }


    public String get(){
        return memeUrl;
    }

}
