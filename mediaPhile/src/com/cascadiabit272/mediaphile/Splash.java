package com.cascadiabit272.mediaphile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
public class Splash extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
 
        new Handler().postDelayed(new Runnable() {
 
            /* Show mediaPhile splash screen for time period indicated above before loading the Order screen */
 
            @Override
            public void run() {
                Intent doneSplashing = new Intent(Splash.this, AddMovie.class);
                startActivity(doneSplashing);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}