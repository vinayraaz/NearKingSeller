package com.nearkingseller.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nearkingseller.Navigation_Activity;
import com.nearkingseller.R;
import com.nearkingseller.api.ApiClient;
import com.nearkingseller.api.ApiInterface;
import com.nearkingseller.constants.CommonConstant;
import com.nearkingseller.responseModel.ProfileResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 12-Apr-18.
 */

public class Profile_Activity extends Navigation_Activity implements View.OnClickListener {
    LinearLayout Profile_Linear, Address_Linear, Paypal_Linear, Account_Linear, Change_Password_Linear;
    LinearLayout Profile_Linear_Value, Address_Linear_Value, Paypal_Linear_Value, Account_Linear_Value, Password_Linear_Value;
    ApiInterface apiService;
    EditText FName,LName,Email,Mobile,Company_Nmae;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout ll_main_data = (LinearLayout) findViewById(R.id.main_include);
        View child = getLayoutInflater().inflate(R.layout.profile_layout, null);
        ll_main_data.addView(child);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        //getSupportActionBar().setIcon(R.drawable.movilo_icon); //also displays wide logo
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for back icon

        Profile_Linear = (LinearLayout) findViewById(R.id.gen_profile_linear);
        Address_Linear = (LinearLayout) findViewById(R.id.add_linear);
        Paypal_Linear = (LinearLayout) findViewById(R.id.paypal_linear);
        Account_Linear = (LinearLayout) findViewById(R.id.account_linear);
        Change_Password_Linear = (LinearLayout) findViewById(R.id.change_pass_linear);

        Profile_Linear_Value = (LinearLayout) findViewById(R.id.profile_linear_value);
        Address_Linear_Value = (LinearLayout) findViewById(R.id.address_linear_values);
        Paypal_Linear_Value = (LinearLayout) findViewById(R.id.paypal_linear_value);
        Account_Linear_Value = (LinearLayout) findViewById(R.id.account_linear_value);
        Password_Linear_Value = (LinearLayout) findViewById(R.id.password_linear_value);

        FName =(EditText) findViewById(R.id.et_fname);
        LName =(EditText) findViewById(R.id.et_lname);
        Email =(EditText) findViewById(R.id.et_email);
        Mobile =(EditText) findViewById(R.id.et_mobile);
        Company_Nmae =(EditText) findViewById(R.id.et_com_name);

        Profile_Linear.setOnClickListener(this);
        Address_Linear.setOnClickListener(this);
        Paypal_Linear.setOnClickListener(this);
        Account_Linear.setOnClickListener(this);
        Change_Password_Linear.setOnClickListener(this);
        ProfileAPI();
    }

    private void ProfileAPI() {
        Call<ProfileResponse> call = apiService.getProfile(CommonConstant.UserEmail);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {

                if (response.isSuccessful()) {
                    CommonConstant.CustomerID = response.body().getCustomerId().toString();
                    ProfileResponse profileResponse = response.body();
                   // if (profileResponse.getFirstname().toString().equals())
                    FName.setText(profileResponse.getFirstname());
                    LName.setText(profileResponse.getTelephone());
                    System.out.println("Profile Response Customer ID" + response.body().getFirstname().toString());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.i("ERROR", t.toString());

            }
        });

    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gen_profile_linear:
                Profile_Linear.setBackgroundResource(R.color.green);
                Address_Linear.setBackgroundResource(R.color.navigation_bar);
                Paypal_Linear.setBackgroundResource(R.color.navigation_bar);
                Account_Linear.setBackgroundResource(R.color.navigation_bar);
                Change_Password_Linear.setBackgroundResource(R.color.navigation_bar);

                Profile_Linear_Value.setVisibility(View.VISIBLE);
                Address_Linear_Value.setVisibility(View.GONE);
                Paypal_Linear_Value.setVisibility(View.GONE);
                Account_Linear_Value.setVisibility(View.GONE);
                Password_Linear_Value.setVisibility(View.GONE);
                break;
            case R.id.add_linear:
                Profile_Linear.setBackgroundResource(R.color.navigation_bar);
                Address_Linear.setBackgroundResource(R.color.green);
                Paypal_Linear.setBackgroundResource(R.color.navigation_bar);
                Account_Linear.setBackgroundResource(R.color.navigation_bar);
                Change_Password_Linear.setBackgroundResource(R.color.navigation_bar);

                Profile_Linear_Value.setVisibility(View.GONE);
                Address_Linear_Value.setVisibility(View.VISIBLE);
                Paypal_Linear_Value.setVisibility(View.GONE);
                Account_Linear_Value.setVisibility(View.GONE);
                Password_Linear_Value.setVisibility(View.GONE);
                break;
            case R.id.paypal_linear:

                Profile_Linear.setBackgroundResource(R.color.navigation_bar);
                Address_Linear.setBackgroundResource(R.color.navigation_bar);
                Paypal_Linear.setBackgroundResource(R.color.green);
                Account_Linear.setBackgroundResource(R.color.navigation_bar);
                Change_Password_Linear.setBackgroundResource(R.color.navigation_bar);

                Profile_Linear_Value.setVisibility(View.GONE);
                Address_Linear_Value.setVisibility(View.GONE);
                Paypal_Linear_Value.setVisibility(View.VISIBLE);
                Account_Linear_Value.setVisibility(View.GONE);
                Password_Linear_Value.setVisibility(View.GONE);
                break;
            case R.id.account_linear:

                Profile_Linear.setBackgroundResource(R.color.navigation_bar);
                Address_Linear.setBackgroundResource(R.color.navigation_bar);
                Paypal_Linear.setBackgroundResource(R.color.navigation_bar);
                Account_Linear.setBackgroundResource(R.color.green);
                Change_Password_Linear.setBackgroundResource(R.color.navigation_bar);

                Profile_Linear_Value.setVisibility(View.GONE);
                Address_Linear_Value.setVisibility(View.GONE);
                Paypal_Linear_Value.setVisibility(View.GONE);
                Account_Linear_Value.setVisibility(View.VISIBLE);
                Password_Linear_Value.setVisibility(View.GONE);
                break;
            case R.id.change_pass_linear:
                Profile_Linear.setBackgroundResource(R.color.navigation_bar);
                Address_Linear.setBackgroundResource(R.color.navigation_bar);
                Paypal_Linear.setBackgroundResource(R.color.navigation_bar);
                Account_Linear.setBackgroundResource(R.color.navigation_bar);
                Change_Password_Linear.setBackgroundResource(R.color.green);

                Profile_Linear_Value.setVisibility(View.GONE);
                Address_Linear_Value.setVisibility(View.GONE);
                Paypal_Linear_Value.setVisibility(View.GONE);
                Account_Linear_Value.setVisibility(View.GONE);
                Password_Linear_Value.setVisibility(View.VISIBLE);
                break;
        }
    }
}
