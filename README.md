## Carls Notes

I consolidated a bunch of code in the `Apirequester` class. The GET method was combind with the POST method, because they did exactly the same thing. All methods for interacting with the API are inside the `ApiRequester` class.

I edited the VolleyCallBack to use generics so it could handle multiple datatypes.

I created a method to get a JSONArray of Memes from the API. This does exactly what `getMemesFromAPI()` in the `MainActivity` does, but it makes more sense for both API requests to be handled the same way. The return should be passed to `makeMemes()` in the `MainActivity`. To get the JSONArray of available memes run this code.:
```java
apiRequester.getMemeList(new MemeVolleyCallback(){
    @Override
    public void onSuccess(JSONArray memeList) {
        // JSONArray of memes from the API are stored in memeList
        // null is returned if error
    }
});
```

To request a meme run this sample code:
```java
apiRequester.getMemeList(new VolleyCallBack<JSONArray>(){
    @Override
    public void onSuccess(JSONArray memeList) {
        System.out.println("JSONArray of Meme Objects are Here");
    }
});
```

Example code can also be found in the MainActivity of the `carl` branch