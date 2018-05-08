package com.nearkingseller.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nearkingseller.R;
import com.nearkingseller.api.ApiClient;
import com.nearkingseller.api.ApiInterface;
import com.nearkingseller.constants.CommonConstant;
import com.nearkingseller.responseModel.LoginResponse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 12-Apr-18.
 */

public class SignIn_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView SignUP;
    EditText Email,Password;
    AppCompatButton SignIn;
    ApiInterface apiService;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_layout);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        SignUP = (TextView) findViewById(R.id.link_signup);
        Email = (EditText) findViewById(R.id.input_email);
        Password = (EditText) findViewById(R.id.input_password);
        SignIn = (AppCompatButton) findViewById(R.id.btn_signin);
        SignUP.setOnClickListener(this);
        SignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_signin:
                validationMethod();
                break;
            case R.id.link_signup:
                Intent intent = new Intent(SignIn_Activity.this, SignUp_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void validationMethod() {
        final String email = Email.getText().toString();
        if (!isValidEmail(email)) {
            Email.setError("Invalid Email");
        }

        final String pass = Password.getText().toString();
        if (!isValidPassword(pass)) {
            Password.setError("Invalid Password");
        }
        else {
            checkInternetConenction();
            LoginAPICall(email,pass);

        }

    }

    private boolean checkInternetConenction() {
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            InternetError();
            // Toast.makeText(this, "Unable to load data \n Something seems to be off about the network connectivity. Can you check and reload?", Toast.LENGTH_LONG).show();

            return false;
        }
        return false;
    }

    private void InternetError() {
        final Dialog alertD = new Dialog(SignIn_Activity.this);
        alertD.setCancelable(false);
        alertD.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertD.setContentView(R.layout.internet_activity);

        alertD.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        alertD.show();
        final TextView InternetCheck = (TextView) alertD.findViewById(R.id.internet_error);
        InternetCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.cancel();
                Intent i2 = new Intent(SignIn_Activity.this, SignIn_Activity.class);
                startActivity(i2);
                finish();
            }
        });
    }

    private void LoginAPICall(String email, String pass) {
        CommonConstant.UserEmail = email;
        CommonConstant.UserPass = pass;
        Call<LoginResponse> call = apiService.getLogin(email,pass);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().toString().equals("200")){
                        Toast.makeText(SignIn_Activity.this,"Login Suucess fully",Toast.LENGTH_SHORT).show();


                        SharedPreferences.Editor editor = getSharedPreferences("nearking_login", MODE_PRIVATE).edit();

                        editor.putString("user_email", CommonConstant.UserEmail);
                        editor.putString("user_pass", CommonConstant.UserPass);
                       editor.commit();

                        Intent intent = new Intent(SignIn_Activity.this, Home_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.i("ERROR",t.toString());

            }
        });

    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() > 2) {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
