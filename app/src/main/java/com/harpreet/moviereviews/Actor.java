package com.harpreet.moviereviews;


import java.io.Serializable;

public class Actor implements Serializable{
    private String mName;
    private String mImageUri;

    public Actor() {
    }

    public Actor(String name, String imageUri) {
        mName = name;
        mImageUri = imageUri;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUri() {
        return mImageUri;
    }

    public void setImageUri(String imageUri) {
        mImageUri = imageUri;
    }

    @Override
    public String toString() {
        return mName +"<<>>"+getImageUri();
    }
}
