package com.harpreet.moviereviews;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 This class encapsulates all database operations
 */
public class MoviesDataSource {
    MoviesDBHelper mMoviesDBHelper;
    SQLiteDatabase mSQLiteDatabase;
    Context mContext;

    public MoviesDataSource(Context context) {
        mContext = context;
        mMoviesDBHelper = new MoviesDBHelper(mContext);
    }

    public void open(){
        mSQLiteDatabase = mMoviesDBHelper.getWritableDatabase();

    }

    public void close(){
        if(mSQLiteDatabase != null && mSQLiteDatabase.isOpen() ){
            //mSQLiteDatabase.close();
            mMoviesDBHelper.close();
        }
    }

    public Movie insertMovie(Movie movie){

        ContentValues values = new ContentValues();
        values.put(MoviesContract.MoviesEntry.COL_TITLE, movie.getTitle());
        values.put(MoviesContract.MoviesEntry.COL_CAST, movie.getCast().toString());

        values.put(MoviesContract.MoviesEntry.COL_RATING, movie.getRating());
        values.put(MoviesContract.MoviesEntry.COL_REVIEWS_AMT, movie.getReviewsAmount());
        values.put(MoviesContract.MoviesEntry.COL_VIDEOS, movie.getVideos().toString());
        values.put(MoviesContract.MoviesEntry.COL_TITLE, movie.getTitle());
        long insertId = mSQLiteDatabase.insert(MoviesContract.MoviesEntry.TABLE_MOVIES, null, values);
        if(insertId != -1){
            movie.setId(insertId);
            Toast.makeText(mContext, "Successfully inserted data at row index "+insertId, Toast.LENGTH_SHORT).show();
        }
        return movie;
    }

    // cursor provides random read and write access to the set result which is displayed by the database query.

    public Cursor findAll(){
        Cursor cursor = mSQLiteDatabase.query(MoviesContract.MoviesEntry.TABLE_MOVIES, MoviesContract.MoviesEntry.PROJECTION, null, null, null, null, null);
        if(cursor != null){
            Toast.makeText(mContext, "Data successfully retrieved", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }

    public Cursor findMovie(String column, String searchVal){
        Cursor cursor = mSQLiteDatabase.query(MoviesContract.MoviesEntry.TABLE_MOVIES,
                MoviesContract.MoviesEntry.PROJECTION, column+" = ? ", new String[]{searchVal},null,null,null);
        if(cursor != null){
            Toast.makeText(mContext, searchVal+" successfully retrieved ", Toast.LENGTH_SHORT).show();
        }


        return cursor;


    }

    // this is to delete all the movies which are stored in a database. this does not delete it from the database but just app it self.
    // to retrieve data again, simply pull the movies from the database again.

    public int deleteAll(){
        int numRowsAffected = mSQLiteDatabase.delete(MoviesContract.MoviesEntry.TABLE_MOVIES, null, null);
        if(numRowsAffected>=0){
            Toast.makeText(mContext, "Deleting all data...", Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, "Data successfully deleted", Toast.LENGTH_SHORT).show();
        }
        return numRowsAffected;
    }

}
