package com.example.harpreet.hungama;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {


    public MovieDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragmentmoviedetail, container, false);
        TextView titleTv = (TextView) rootView.findViewById(R.id.tvTitle);
        TextView ratingTv = (TextView) rootView.findViewById(R.id.tvRating);
        TextView reviewsAmtTv = (TextView) rootView.findViewById(R.id.tvReviewsAmount);
        RatingBar movieRatingStarsTv = (RatingBar) rootView.findViewById(R.id.tvMovieRating);
        TextView videoUrl1Tv = (TextView) rootView.findViewById(R.id.tvVideoUrl1);
        TextView videoUrl2Tv = (TextView) rootView.findViewById(R.id.tvVideoUrl2);
        ImageView imageActor1 = (ImageView) rootView.findViewById(R.id.imgActor1);
        ImageView imageActor2 = (ImageView) rootView.findViewById(R.id.imgActor2);
        ImageView imageActor3 = (ImageView) rootView.findViewById(R.id.imgActor3);
//
//
        Movie movie = (Movie) getActivity().getIntent().getSerializableExtra("key_movie");

        titleTv.setText(movie.getTitle());
        ratingTv.setText(movie.getRating()+"");
        movieRatingStarsTv.setRating((float)movie.getRating());
        movieRatingStarsTv.setEnabled(false);
        reviewsAmtTv.setText(movie.getReviewsAmount()+" reviews");
        videoUrl1Tv.setText(movie.getVideos().get(0));
        videoUrl2Tv.setText(movie.getVideos().get(1));


        //Picasso is a image library for android. this simplifies the process for displaying images from the internet.
        // only 1 line of code is needed to load the images

        Picasso.with(getActivity()).load(Uri.parse(movie.getCast().get(0).getImageUri())).fit().into(imageActor1);
        Picasso.with(getActivity()).load(Uri.parse(movie.getCast().get(1).getImageUri())).fit().into(imageActor2);
        Picasso.with(getActivity()).load(Uri.parse(movie.getCast().get(2).getImageUri())).fit().into(imageActor3);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Movie Detail");
    }
}

