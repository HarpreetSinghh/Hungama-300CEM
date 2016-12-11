package com.harpreet.moviereviews;

import android.provider.BaseColumns;

/**
 */

public final class MoviesContract {

    public static abstract class MoviesEntry implements BaseColumns{

         public static final String TABLE_MOVIES ="movies";
         public static final String COL_ID = BaseColumns._ID;
         public static final String COL_TITLE ="title";
         public static final String COL_RATING ="rating";
         public static final String COL_REVIEWS_AMT ="reviews";
         public static final String COL_CAST ="actors";
         public static final String COL_VIDEOS ="videos";

         public static final String[] PROJECTION = {COL_ID, COL_TITLE, COL_RATING, COL_REVIEWS_AMT, COL_CAST, COL_VIDEOS};

    }
}
