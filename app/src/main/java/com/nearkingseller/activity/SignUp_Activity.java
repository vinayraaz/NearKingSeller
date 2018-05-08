package com.nearkingseller.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nearkingseller.R;
import com.nearkingseller.api.ApiClient;
import com.nearkingseller.api.ApiInterface;
import com.nearkingseller.constants.CommonConstant;
import com.nearkingseller.responseModel.CountryResponse;
import com.nearkingseller.responseModel.LoginResponse;
import com.nearkingseller.responseModel.Productcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by admin on 12-Apr-18.
 */

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {
    TextView SignIn;
    AppCompatButton SignUp;
    EditText FirstName, CompanyName, Email, Mobile, Telephone, Password, Con_Password, PostCode;
    RadioButton Radio1,Radio2;
    int pos;
    ApiInterface apiService;
    String seller_type;
    Spinner Country_Spinner, Product_Spinner;
   // List<CountryParshingModel> countryParshingModels = new ArrayList<>();
    List<String> countryName = new ArrayList<>();
    List<String> productCategory = new ArrayList<>();
    // List<CountryParshingModel> countryParshingModels = new ArrayList<>();
    String select_country = "", select_pro_category = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        SignIn = (TextView) findViewById(R.id.link_signin);
        SignUp = (AppCompatButton) findViewById(R.id.btn_signup);
        SignIn.setOnClickListener(this);
        SignUp.setOnClickListener(this);

        FirstName = (EditText) findViewById(R.id.input_fname);
        CompanyName = (EditText) findViewById(R.id.input_company_name);
        Email = (EditText) findViewById(R.id.input_email);
        Mobile = (EditText) findViewById(R.id.input_mobile);
        Telephone = (EditText) findViewById(R.id.input_telephone);
        Password = (EditText) findViewById(R.id.input_password);
        Con_Password = (EditText) findViewById(R.id.input_c_password);
        PostCode = (EditText) findViewById(R.id.input_code);
        Radio1 = (RadioButton) findViewById(R.id.radio1);
        Radio2 = (RadioButton) findViewById(R.id.radio2);
        Country_Spinner = (Spinner) findViewById(R.id.spinner1);
        Product_Spinner = (Spinner) findViewById(R.id.spinner2);
        countryName.clear();
        productCategory.clear();
        countryName.add("Select Country");
        productCategory.add("Select product category");
        CountryAPICall();
        ProductCategoryAPICall();

        Country_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_country = Country_Spinner.getSelectedItem().toString();
                Log.i("Country", select_country);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Product_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                select_pro_category = Product_Spinner.getSelectedItem().toString();
                Log.i("Product Type", select_pro_category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Radio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Radio1.isChecked())
                {
                    Radio2.setChecked(false);
                    seller_type="Wholesale";
                    System.out.println("seller_type***"+seller_type);

                }
            }
        });
        Radio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(Radio2.isChecked())
                {
                    Radio1.setChecked(false);
                    seller_type="Retail";
                    System.out.println("seller_type***"+seller_type);

                }
            }
        });

    }

    private void ProductCategoryAPICall() {
        Call<List<Productcategory>> call = apiService.getProductCatType();
        call.enqueue(new Callback<List<Productcategory>>() {
            @Override
            public void onResponse(Call<List<Productcategory>> call, Response<List<Productcategory>> response) {
                if (response.isSuccessful()) {
                    for (int j = 0; j < response.body().size(); j++) {
                        productCategory.add(response.body().get(j).getName().toString());
                    }
                    ArrayAdapter<String> pcad = new ArrayAdapter<String>(SignUp_Activity.this, android.R.layout.simple_dropdown_item_1line, productCategory);
                    Product_Spinner.setAdapter(pcad);

                }
            }

            @Override
            public void onFailure(Call<List<Productcategory>> call, Throwable t) {

            }
        });
    }

    private void CountryAPICall() {

        Call<List<CountryResponse>> call = apiService.getCountry();
        call.enqueue(new Callback<List<CountryResponse>>() {
            @Override
            public void onResponse(Call<List<CountryResponse>> call, Response<List<CountryResponse>> response) {
                if (response.isSuccessful()) {


                    for (int i = 0; i < response.body().size(); i++) {
                        //countryParshingModels.add(new CountryParshingModel(response.body().get(i).getCountryId(), response.body().get(i).getName()));
                        countryName.add(response.body().get(i).getName());

                    }

                    ArrayAdapter<String> adp = new ArrayAdapter<String>(SignUp_Activity.this, android.R.layout.simple_dropdown_item_1line, countryName);
                    Country_Spinner.setAdapter(adp);
                } else {

                }

            }

            @Override
            public void onFailure(Call<List<CountryResponse>> call, Throwable t) {

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signup:
                validationMethod();
                break;
            case R.id.link_signin:
                Intent intent = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void validationMethod() {
        if (CompanyName.getText().toString().isEmpty() || CompanyName.getText().toString().length() == 0) {
            Toast.makeText(SignUp_Activity.this, "Enter company name", Toast.LENGTH_SHORT).show();
        } else if (FirstName.getText().toString().isEmpty() || FirstName.getText().toString().length() == 0) {
            Toast.makeText(SignUp_Activity.this, "Enter User name", Toast.LENGTH_SHORT).show();
        } else if (Email.getText().toString().isEmpty() || Email.getText().toString().length() == 0) {
            Toast.makeText(SignUp_Activity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
        } else if (Mobile.getText().toString().isEmpty() || Mobile.getText().toString().length() == 0 || Mobile.getText().length() < 10) {
            Toast.makeText(SignUp_Activity.this, "Enter 10 digits mobile no.", Toast.LENGTH_SHORT).show();
        }else if (Telephone.getText().toString().isEmpty() || Telephone.getText().toString().length() == 0 || Telephone.getText().length() < 10) {
            Toast.makeText(SignUp_Activity.this, "Enter valid telephone no.", Toast.LENGTH_SHORT).show();
        }
        else if (Password.getText().toString().isEmpty() || Password.getText().toString().length() <= 4) {
            Toast.makeText(SignUp_Activity.this, "Password must be more than 4 digits", Toast.LENGTH_SHORT).show();
        } else if (Con_Password.getText().toString().isEmpty() || Con_Password.getText().toString().length() <= 4) {
            Toast.makeText(SignUp_Activity.this, "Password must be more than 4 digits", Toast.LENGTH_SHORT).show();
        } else if (!(Con_Password.getText().toString()).equalsIgnoreCase(Password.getText().toString())) {
            Toast.makeText(SignUp_Activity.this, "Password mismatch", Toast.LENGTH_SHORT).show();
        } else if (PostCode.getText().toString().isEmpty() || PostCode.getText().toString().length() == 0 || PostCode.getText().toString().length() <6) {
            Toast.makeText(SignUp_Activity.this, "Enter valid postalcode", Toast.LENGTH_SHORT).show();
        }
        else {
            checkInternetConenction();
            SignUpAPICall();
            //Toast.makeText(SignUp_Activity.this, "API", Toast.LENGTH_SHORT).show();
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
        final Dialog alertD = new Dialog(SignUp_Activity.this);
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
                Intent i2 = new Intent(SignUp_Activity.this, SignIn_Activity.class);
                startActivity(i2);
                finish();
            }
        });
    }
    private void SignUpAPICall() {
        final String fname, companyname, email, mobile, telephone, pass, cpass, pcode;

        companyname = CompanyName.getText().toString();
        fname = FirstName.getText().toString();
        email = Email.getText().toString();
        mobile = Mobile.getText().toString();
        telephone = Telephone.getText().toString();
        pass = Password.getText().toString();
        cpass = Con_Password.getText().toString();
        pcode = PostCode.getText().toString();
        CommonConstant.UserEmail = email;
        CommonConstant.UserPass = pass;
        System.out.println("CommonConstant.UserPass "+CommonConstant.UserPass );
        System.out.println("CommonConstant.UserPass "+email);
        Call<LoginResponse> call = apiService.getRegister(companyname,fname,CommonConstant.UserEmail,mobile,telephone,CommonConstant.UserPass,/*cpass,*/pcode,seller_type);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().toString().equals("200")){
                        Toast.makeText(SignUp_Activity.this, "Registration Successfully ", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = getSharedPreferences("nearking_login", MODE_PRIVATE).edit();

                        editor.putString("user_email", CommonConstant.UserEmail);
                        editor.putString("user_pass", CommonConstant.UserPass);
                        editor.commit();

                        Intent intent = new Intent(SignUp_Activity.this, Home_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }



}
