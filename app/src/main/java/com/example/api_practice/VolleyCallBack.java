package com.example.api_practice;

public interface VolleyCallBack {
    // Gets invoked after an API has a successful response
    // Should be called back after all of the code in the 'onResponse' interface has been ran
    void onSuccess();
}