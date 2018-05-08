package com.buildltd.buildapp.quinzby.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.buildltd.buildapp.quinzby.R;
import com.buildltd.buildapp.quinzby.fragments.ForgotPasswordFragment;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button noAccount,login_button;
    TextView forgot_password, welcome,quinzby_text,homecare;
    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        getWindow().setWindowAnimations(R.style.dialog_animation_fade);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        String fPath1 = "fonts/Billabong.ttf";
        String fPath2 = "fonts/CaviarDreams_Italic.ttf";
        String fPath3 = "fonts/OpenSans-Regular.ttf";
        String fPath4 = "fonts/Caviar_Dreams_Bold.ttf";
        String fPath5 = "fonts/OpenSans-Light.ttf";

        Typeface typeface1 = Typeface.createFromAsset(getAssets(), fPath1);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), fPath2);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), fPath3);
        Typeface typeface4 = Typeface.createFromAsset(getAssets(), fPath4);
        Typeface typeface5 = Typeface.createFromAsset(getAssets(), fPath5);

        //welcome = findViewById(R.id.welcome);
        quinzby_text = findViewById(R.id.quinzy_text);
        homecare = findViewById(R.id.homecare);

        forgot_password = findViewById(R.id.forgot_password);
        login_button = findViewById(R.id.login_button);

        noAccount = findViewById(R.id.noAccount);
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getSupportFragmentManager();
                ForgotPasswordFragment forgotPasswordFragment = new ForgotPasswordFragment();
                forgotPasswordFragment.show(fm, "forgot password");
            }
        });

        forgot_password.setTypeface(typeface3);
        //welcome.setTypeface(typeface3);
        quinzby_text.setTypeface(typeface1);
        homecare.setTypeface(typeface3);
        login_button.setTypeface(typeface3);
        noAccount.setTypeface(typeface3);
    }
}
