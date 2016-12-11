package com.harpreet.moviereviews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 This class defines the database structure and creates the table
 */

public class MoviesDBHelper extends SQLiteOpenHelper {

    private static final String TAG = MoviesDBHelper.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies.db";
    private static final String SQL_CREATE = "CREATE TABLE "+  MoviesContract.MoviesEntry.TABLE_MOVIES+
            "( "+MoviesContract.MoviesEntry.COL_ID+" INT AUTO_INCREMENT PRIMARY KEY"+
            ", "+MoviesContract.MoviesEntry.COL_TITLE+" TEXT "+
            ", "+MoviesContract.MoviesEntry.COL_RATING+" DOUBLE "+
            ", "+MoviesContract.MoviesEntry.COL_REVIEWS_AMT+" INT "+
            ", "+ MoviesContract.MoviesEntry.COL_CAST+" TEXT "+
            ", "+ MoviesContract.MoviesEntry.COL_VIDEOS+" TEXT )";

    public MoviesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        Log.i(TAG, "Database and movies table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ MoviesContract.MoviesEntry.TABLE_MOVIES);
        onCreate(db);
        Log.i(TAG, "Database upgraded");
    }
}
