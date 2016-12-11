package com.harpreet.moviereviews;


import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Startanimation extends Activity {

    MediaPlayer hungama;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    // nCalled when the activity is first created. */
    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startanimation);

        hungama=MediaPlayer.create(Startanimation.this,R.raw.hungama);
        hungama.start();
        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        // find the image by using image view and load the image from its id.
        ImageView iv = (ImageView) findViewById(R.id.splash);
        iv.clearAnimation();
        // Start animating the image
        iv.startAnimation(anim);

        // Create a thread and set time to sleep after that redirect to main app screen.

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;

                    // Splash screen pause time

                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }

                    // After pause time, redirect to another intent
                    Intent intent = new Intent(Startanimation.this,
                            HomeActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Startanimation.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Startanimation.this.finish();
                }

            }
        };
        // // start thread
        splashTread.start();

    }

}