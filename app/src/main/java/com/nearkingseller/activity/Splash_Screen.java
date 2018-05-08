package com.nearkingseller.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nearkingseller.R;
import com.nearkingseller.constants.CommonConstant;

/**
 * Created by admin on 12-Apr-18.
 */

public class Splash_Screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    String UserEmail="",UserPass="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

       // getSupportActionBar();
        final SharedPreferences prefs = getSharedPreferences("nearking_login", MODE_PRIVATE);
        UserEmail = prefs.getString("user_email", "");
        UserPass = prefs.getString("user_pass", "");
        CommonConstant.UserEmail = UserEmail;
        CommonConstant.UserPass = UserPass;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (UserEmail == null || UserEmail.equals("") || UserEmail.equals("null")) {
                    Intent intent = new Intent(Splash_Screen.this, SignIn_Activity.class);
                    startActivity(intent);
                    finish();
                } else {

                    Intent Homeintent = new Intent(Splash_Screen.this, Home_Activity.class);
                    startActivity(Homeintent);
                    finish();
                    //checkInternetConenction();
                }
            }
        }, SPLASH_TIME_OUT);
    }


}
