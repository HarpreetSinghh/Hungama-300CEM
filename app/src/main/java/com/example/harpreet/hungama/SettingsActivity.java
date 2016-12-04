package com.example.harpreet.hungama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsPreferenceFragment())
                .commit();
    }
}
