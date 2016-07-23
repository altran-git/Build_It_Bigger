package com.a2g.nd.builditbigger.jokebackend;

/** The object model for the data we are sending through endpoints */
public class MyJoke {

    private String mJoke;

    public String getJoke() {
        return mJoke;
    }

    public void setJoke(String joke) {
        mJoke = joke;
    }
}