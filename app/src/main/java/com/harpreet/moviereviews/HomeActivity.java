package com.harpreet.moviereviews;
/*
 This class is the starting screen that enables facebook login
 */
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.Calendar;

import android.media.MediaPlayer;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class HomeActivity extends AppCompatActivity {

    CallbackManager mCallbackManager;
    LoginButton mLoginButton;
    ProfileTracker mProfileTracker;
    AccessTokenTracker mAccessTokenTracker;
    AQuery mAQuery;// library for downloading profile image
    boolean loggedIn;
    SharedPreferences savedLoggingPrefs;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.home);

        // here we have set up click listener. first thing we need is the time when we want the notification to show. in order to pick the time, we can use instance of the class named calender.
        // repeating notification works by creating and registerating broadcast reciever which can be triggerd by the alarm manager. alarm manager is a service built into the android system.


        findViewById(R.id.getnotified).setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view){

                Calendar calendar = Calendar.getInstance();

                // HOUR OF DAY == 24 hour clock is used
                // Minute and Second == which minute and second i want the alarm to trigger.
                calendar.set(Calendar.HOUR_OF_DAY,05);
                calendar.set(Calendar.MINUTE,25);
                calendar.set(Calendar.SECOND,30);

                Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);

                // the alarm service takes pending intent as a parameter so we must provide intent. 100 is the unique reference code.
                // this flag update current indicates the pending intent we just created now can be updated in the future. like if we want to change the time into the new one.

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                // alarm which will repeat and trigger my notification. we used the method 'setrepeating' .
                // AlarmManager.RTC_WAKEUP == this type ensures that the alarm will be triggerd even if the device goes into the sleep mode.
                // Interval_day == this is where want want the notification to show on daily basis
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


            }
        });


        //main title

        setTitle("Welcome to Desi Hungama");

        savedLoggingPrefs = getSharedPreferences("logged_in_settings", 0);
        loggedIn =savedLoggingPrefs.getBoolean("key_logged_in", false);

        mCallbackManager = CallbackManager.Factory.create();
        mAQuery = new AQuery(this);

      
        mLoginButton = (LoginButton) findViewById(R.id.fbkBtnLogin);
        mLoginButton.setReadPermissions("user_friends","email","public_profile");

        // To register the custom callback, i have used registerCallback method. this creates and registers the call back.

        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {

            // this is the onSuccess method, LoginResult is passed as a parameter.
            // It retrieves the access token it contains which uses getAccessToken and use it to display user's profile

            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();

                if(profile == null){
                    mProfileTracker = new ProfileTracker() {
                        @Override
                        protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                            if(currentProfile != null)
                                displayProfile(currentProfile);
                            if(oldProfile != null)
                                displayProfile(oldProfile);
                        }
                    };
                    mAccessTokenTracker = new AccessTokenTracker() {
                        @Override
                        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                        }
                    };
                    mAccessTokenTracker.startTracking();
                    mProfileTracker.startTracking();
                }else{
                    displayProfile(profile);
                }
                loggedIn = true;
                savedLoggingPrefs.edit().putBoolean("key_logged_in", loggedIn).commit();

            }

            // if This is the onCancel method.
            // if the user wants to cancel the login attempt which they made, we display a message saying 'Login Cancelled'

            @Override
            public void onCancel() {

                //Toast is a view containing a small message for the user to see. Toast class can show you these.

                // Length_short is the show the text notification for short amount of time.

                Toast.makeText(HomeActivity.this, "Login Cancelled", Toast.LENGTH_SHORT).show();
                loggedIn = false;
                savedLoggingPrefs.edit().putBoolean("key_logged_in", loggedIn).commit();
            }

            // This is very similar to onCancel method, here we use onError method.

            @Override
            public void onError(FacebookException error) {
                // Length_short is the show the text notification for short amount of time.
                Toast.makeText(HomeActivity.this, "Unable to login", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Error")
                        .setMessage("Error: "+error.getMessage())
                        .create().show();
                loggedIn = false;
                savedLoggingPrefs.edit().putBoolean("key_logged_in", loggedIn).commit();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        FloatingActionButton nextScreenBtn = (FloatingActionButton)findViewById(R.id.floatingActionButton);
        if(loggedIn){
            
            nextScreenBtn.setVisibility(View.VISIBLE);
        }else{
            nextScreenBtn.setVisibility(View.INVISIBLE); 
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAccessTokenTracker!= null){
            mAccessTokenTracker.stopTracking();
        }

        if(mProfileTracker != null){
            mProfileTracker.stopTracking();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        savedLoggingPrefs.edit().putBoolean("key_logged_in", loggedIn).commit();
    }

    private void displayProfile(Profile p){
        String imageUriString = p.getProfilePictureUri(850,900).toString();
        mAQuery.id(R.id.imgProfile).image(imageUriString);
    }

    public void goToMain(View view) {
        if (loggedIn) {
            startActivity(new Intent(HomeActivity.this, MainActivity.class));

        }else{
            new AlertDialog.Builder(this)
                    .setTitle("Unauthorized User")
                    .setMessage("You have to log in with your valid facebook username and password before you would be allowed entry")
                    .create().show();
        }
    }


    // Login button which is on the main screen. the button is named 'signinlogin'.
    // if the user clicks on the button from the homeactivity, it will take the user to the next activity which is 'loginactivity'

    public void onButtonClick(View view)
    {
        if(view.getId() == R.id.signinlogin)
        {
            Intent myIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }
    }


}
