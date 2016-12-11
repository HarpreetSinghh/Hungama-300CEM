package com.harpreet.moviereviews;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
/*
 The class contains the ui for a rating bar system to rate this app
 */
public class RateUsActivity extends AppCompatActivity {
     SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rateus);

        prefs = getSharedPreferences("prefs2",0);


        final TextView tvRatingVal = (TextView) findViewById(R.id.tvRatingVal);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setEnabled(true);
        ratingBar.setRating(prefs.getFloat("key_rate_val",0));
        tvRatingVal.setText("You rated "+prefs.getFloat("key_rate_val",0)+" star"+((prefs.getFloat("key_rate_val",0)>1)?"s":""));
        //ratingBar.setEnabled(false);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                tvRatingVal.setText("You rated "+rating+" star"+((rating>1)?"s":""));
                ratingBar.setEnabled(true);//set to false to put a lock on a rating so nobody can change their rating once set
                prefs.edit().putFloat("key_rate_val",rating).commit();

            }
        });


    }
}
