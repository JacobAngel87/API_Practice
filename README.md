## Carls Notes

I consolidated a bunch of code in the `Apirequester` class. The GET method was combind with the POST method, because they did exactly the same thing. All methods for interacting with the API are inside the `ApiRequester` class.

A new Interface was created: `MemeVolleyCallback`

I created a method to get a JSONArray of Memes from the API. This does exactly what `getMemesFromAPI()` in the `MainActivity` does, but it makes more sense for both API requests to be handeled the same way. The return should be passed to `makeMemes()` in the `MainActivity`. To get the JSONArray of available memes run this code.:
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
apiRequester.getMeme(101470, "this is text on top", "this is bottom text", new VolleyCallBack() {
    @Override
    public void onSuccess(String imageUrl) {
        // the image url to jpg is stored in imageUrl
        // null is returned if error
    }
});
```

Example code can also be found in the MainActivity of the `carl` branch