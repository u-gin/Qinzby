package com.buildltd.buildapp.quinzby.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.buildltd.buildapp.quinzby.R;

public class SplashActivity extends AppCompatActivity {

    ImageView applog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        Animation fade_in_dialog = AnimationUtils.loadAnimation(this, R.anim.fade_in_dialog);
        applog = (ImageView) findViewById(R.id.applog);
        applog.startAnimation(fade_in_dialog);

        final Intent intent = new Intent(this,LoginActivity.class);

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                }
            }
        };

        timer.start();

    }
}
