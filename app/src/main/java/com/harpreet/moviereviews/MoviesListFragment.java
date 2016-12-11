package com.harpreet.moviereviews;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * This class is a fragment that contains the listView to display the movies
 */
public class MoviesListFragment extends Fragment {

    private static final String TAG = MoviesListFragment.class.getSimpleName();
    MoviesDataSource mMoviesDataSource;
    ListView mListView;
    ArrayList<Movie> mMovies;


    public MoviesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        mMovies = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        mMoviesDataSource.open();
        Log.i(TAG, "Database opened");
        Toast.makeText(getActivity(), "Database opened", Toast.LENGTH_SHORT).show();

        updateDisplay();

    }

    private void updateDisplay() {
        String[] from = {MoviesContract.MoviesEntry.COL_TITLE};
        int[] to = {android.R.id.text1};
        Cursor cursor = mMoviesDataSource.findAll();
        if (cursor.getCount() > 0) {

            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, from, to, 0);
            mListView.setAdapter(cursorAdapter);
            while (cursor.moveToNext()) {
                Movie movie = new Movie();
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_TITLE)));
                movie.setRating(cursor.getDouble(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_RATING)));
                movie.setReviewsAmount(cursor.getInt(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_REVIEWS_AMT)));

                //code to extract and parse each actor from  String to list to each Actor to name and uri to Actors
                List<String> actorNamesAndUris = convertStringToList(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_CAST)));
                if (!actorNamesAndUris.isEmpty()) {
                   String[] splitNameandUri= null;
                   ArrayList<Actor> retrievedActors = new ArrayList<>();
                   Actor retrievedActor = null;
                    for (int i = 0; i < actorNamesAndUris.size(); i++) {
                        splitNameandUri = actorNamesAndUris.get(i).split("<<>>");

                            String actorName = splitNameandUri[0];

                            String actorImageUri = splitNameandUri[1];

                            retrievedActor = new Actor(actorName, actorImageUri);

                        retrievedActors.add(retrievedActor);
                    }
                    movie.setCast(retrievedActors);
                }

                movie.setVideos(convertStringToList(cursor.getString(cursor.getColumnIndex(MoviesContract.MoviesEntry.COL_VIDEOS))));
                mMovies.add(movie);
            }
        } else {
            CursorAdapter cursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor, from, to, 0);
            mListView.setAdapter(cursorAdapter);
        }


    }

    public List<String> convertStringToList(String s1) {
        String replace = s1.replace("[", "");
        String replace1 = replace.replace("]", "");
        List<String> resultList = new ArrayList<String>(Arrays.asList(replace1.split(",")));
        return resultList;
    }

    @Override
    public void onStop() {
        super.onStop();
        mMoviesDataSource.close();
        Log.i(TAG, "Database closed");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int selectedMenuItemId = item.getItemId();
        final ArrayList<String> mMovieTitles = new ArrayList<>();
        for (Movie eachMovie : mMovies) {
            mMovieTitles.add(eachMovie.getTitle());
        }
        switch (selectedMenuItemId) {
            case R.id.action_rate:
                startActivity(new Intent(getActivity(), RateUsActivity.class));
                break;
            case R.id.action_help:
                startActivity(new Intent(getActivity(), HelpActivity.class));
                break;

            case R.id.log_out:
                Toast.makeText(getApplicationContext(),"You have successfully Logged out! ", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), HomeActivity.class));
                break;


            case R.id.action_insert_dummy_data:
                insertDummyData();
                updateDisplay();
                break;

            case R.id.action_search:
                final EditText searchEntryEt = new EditText(getActivity());
                searchEntryEt.setHint("Enter a movie title to search for");

                new AlertDialog.Builder(getActivity())
                        .setView(searchEntryEt)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String searchItem = searchEntryEt.getText().toString();
                                if (mMovieTitles.contains(searchEntryEt.getText().toString())) {

                                    Toast.makeText(getActivity(), "Movie titled " + searchItem + " found", Toast.LENGTH_SHORT).show();
                                    Cursor cursor = mMoviesDataSource.findMovie(MoviesContract.MoviesEntry.COL_TITLE, searchItem);
                                    mListView.setAdapter(new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, cursor,
                                            new String[]{MoviesContract.MoviesEntry.COL_TITLE}, new int[]{android.R.id.text1}, 0));
                                    //cursor.close();
                                } else {
                                    Toast.makeText(getActivity(), "Movie titled " + searchItem + " not found", Toast.LENGTH_SHORT).show();
                                }


                                dialog.dismiss();
                            }
                        })
                        .create().show();

                break;

            case R.id.action_settings:

                startActivity(new Intent(getActivity(), SettingsActivity.class));


                break;

            case R.id.action_delete_all:
                new AlertDialog.Builder(getActivity())
                        .setTitle("Delete Movies")
                        .setMessage("Do you want to delete all movies?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mMoviesDataSource.deleteAll();
                                updateDisplay();
                                dialog.dismiss();

                            }
                        })
                        .create().show();

                break;
        }


        return true;
    }


    private void insertDummyData() {
        Movie movie = new Movie("Devdas", 7.6, 62,
                new ArrayList<Actor>(
                        Arrays.asList(
                                new Actor[]{
                                        new Actor("Shah Rukh Khan", "http://s3.india.com/wp-content/uploads/2015/12/Shah-Rukh-Khan.jpg"),
                                        new Actor("Madhuri Dixit", "https://upload.wikimedia.org/wikipedia/commons/a/a9/Madhuri_Dixit_Dedh_Ishqiya.jpg"),
                                        new Actor("Aishwarya Rai Bachchan", "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyMjEyODkzN15BMl5BanBnXkFtZTcwODkxMTY1Mg@@._V1_.jpg")
                                }
                        )
                ),
                new ArrayList<String>(
                        Arrays.asList(
                                new String[]{
                                        "https://www.youtube.com/watch?v=8tuHQWGMQwY",
                                        "https://www.youtube.com/watch?v=J9J7jguUFp4",
                                }
                        )
                )


        );
        mMoviesDataSource.insertMovie(movie);

        movie = new Movie("Jodhaa Akbar", 7.6, 33,
                new ArrayList<Actor>(
                        Arrays.asList(
                                new Actor[]{
                                        new Actor("Hrithik Roshan", "http://www.contactphonenumberaddress.com/wp-content/uploads/2015/08/Hrithik-Roshan-Going-to-Hollywood.jpg"),
                                        new Actor("Aishwarya Rai Bachchan", "https://images-na.ssl-images-amazon.com/images/M/MV5BMjEyMjEyODkzN15BMl5BanBnXkFtZTcwODkxMTY1Mg@@._V1_.jpg"),
                                        new Actor("Sonu Sood", "http://lifenlesson.com/wp-content/uploads/2015/12/Sonu-Sood.jpg")
                                }
                        )
                ),
                new ArrayList<String>(
                        Arrays.asList(
                                new String[]{
                                        "https://www.youtube.com/watch?v=lKACNzDVE3o",
                                        "https://www.youtube.com/watch?v=vYvl3CIX0zQ",
                                }
                        )
                )


        );
        mMoviesDataSource.insertMovie(movie);

        movie = new Movie("Well Done Abba!", 7.3, 6,
                new ArrayList<Actor>(
                        Arrays.asList(
                                new Actor[]{
                                        new Actor("Boman Irani", "https://images-na.ssl-images-amazon.com/images/M/MV5BMjQ3NGE4NjAtMDE3MC00OWI4LTg3MzEtYmJhNDQ3YTA1NDUyXkEyXkFqcGdeQXVyMjYwMDk5NjE@._V1_SY999_SX666_AL_.jpg"),
                                        new Actor("Minissha Lamba", "http://www.contactphonenumberaddress.com/wp-content/uploads/2015/11/Minissha-Lamba.jpg"),
                                        new Actor("Sammir Dattani", "http://cdn.bollywoodmdb.com/celebrities/images-1/sammir-dattani.jpg")
                                }
                        )
                ),
                new ArrayList<String>(
                        Arrays.asList(
                                new String[]{
                                        "https://www.youtube.com/watch?v=mxjhYyGteKI",
                                        "https://www.youtube.com/watch?v=kvSpl0UZwFs",
                                }
                        )
                )


        );
        mMoviesDataSource.insertMovie(movie);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragmentmovieslist, container, false);
        mListView = (ListView) rootView.findViewById(R.id.listView);
        //mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(getActivity(),
                        MovieDetailActivity.class).putExtra("key_movie", mMovies.get(position)));
            }
        });


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Movies");
        mMoviesDataSource = new MoviesDataSource(getActivity());
    }
}
