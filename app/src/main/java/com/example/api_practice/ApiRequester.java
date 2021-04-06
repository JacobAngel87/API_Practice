package com.example.api_practice;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ApiRequester {

    private MainActivity main;
    private StringRequest stringRequest;
    private RequestQueue queue;

    private String data;

    public ApiRequester(MainActivity main) {
        this.main = main;
        queue = Volley.newRequestQueue(main.getApplicationContext());
    }

    public void requestAPI(String url, final VolleyCallBack callBack) {
        stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    data = response;
                    callBack.onSuccess();
                },
                error -> data = null);
        queue.add(stringRequest);
    }

    public String getData() {
        return data;
    }
}
