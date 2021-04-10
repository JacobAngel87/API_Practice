package com.example.api_practice;

public interface VolleyCallBack<E> {
    // Gets invoked after an API has a successful response
    // Should be called back after all of the code in the 'onResponse' interface has been ran
    // This interface uses generics so multiple data types can be handled.
    void onSuccess(E data);
}