package com.example.harpreet.hungama;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);

        setTitle("Help");
    }


    public void showVideoDialog(View view) {
        HelpVideoDialogFragment dialogFragment = new HelpVideoDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Video_Tag");
    }
}
