package com.example.harpreet.hungama;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Title
 Rating
 Reviews
 Cast
 Videos

 Book tickets ?
 Rate now ?
 */

public class Movie implements Serializable{
    private String mTitle;
    private double mRating;
    private int mReviewsAmount;
    private List<Actor> mCast;
    private List<String> mVideos;
    private long mId;

    public Movie() {
        mCast = new ArrayList<>();
        mVideos = new ArrayList<>();
    }

    public Movie(String title, double rating, int reviewsAmount, List<Actor> cast, List<String> videos) {
        mTitle = title;
        mRating = rating;
        mReviewsAmount = reviewsAmount;
        mCast = cast;
        mVideos = videos;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getRating() {
        return mRating;
    }

    public void setRating(double rating) {
        mRating = rating;
    }

    public int getReviewsAmount() {
        return mReviewsAmount;
    }

    public void setReviewsAmount(int reviewsAmount) {
        mReviewsAmount = reviewsAmount;
    }

    public List<Actor> getCast() {
        return mCast;
    }

    public void setCast(List<Actor> cast) {
        mCast = cast;
    }

    public List<String> getVideos() {
        return mVideos;
    }

    public void setVideos(List<String> videos) {
        mVideos = videos;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }
}

