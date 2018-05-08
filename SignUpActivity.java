package com.buildltd.buildapp.quinzby.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.buildltd.buildapp.quinzby.R;
import com.buildltd.buildapp.quinzby.helpers.CheckNetworkInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    Button noAccount,signup_button;
    TextView welcome,quinzby_text, homecare;
    TextInputEditText tv_name, tv_username, tv_email, tv_password;
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mReference;
    CheckNetworkInfo mCheckNetworkInfo;


    String username, password, full_name, email;

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
        setContentView(R.layout.activity_sign_up);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReference = mFirebaseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();

        mCheckNetworkInfo = new CheckNetworkInfo(getApplicationContext());

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
        signup_button = findViewById(R.id.signup_button);
        tv_username = findViewById(R.id.tv_username);
        tv_email = findViewById(R.id.tv_email);
        tv_name = findViewById(R.id.tv_name);
        tv_password = findViewById(R.id.tv_password);



        //welcome.setTypeface(typeface3);
        quinzby_text.setTypeface(typeface1);
        homecare.setTypeface(typeface3);
        signup_button.setTypeface(typeface3);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = tv_username.getText().toString().trim();
                password = tv_password.getText().toString().trim();
                email = tv_email.getText().toString().trim();
                full_name = tv_name.getText().toString().trim();

                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(password)||TextUtils.isEmpty(email)||TextUtils.isEmpty(full_name)){
                    Snackbar.make(getWindow().getDecorView(), "Please fill out all the fields", Snackbar.LENGTH_LONG).show();
                }else{
                    if (!mCheckNetworkInfo.isConnected()){
                        Snackbar.make(getWindow().getDecorView(), "No internet connection", Snackbar.LENGTH_LONG).show();
                    }else{
                        mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(SignUpActivity.this,ProfileSetUpActivity.class);
                                    intent.putExtra("full_name", full_name);
                                    intent.putExtra("email", email);
                                    intent.putExtra("username", password);
                                    startActivity(intent);
                                }else{
                                    Snackbar.make(getWindow().getDecorView(), "A problem occurred, please try again later", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }

            }
        });

    }
}
