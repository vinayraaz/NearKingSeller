package com.nearkingseller.api;

import com.nearkingseller.responseModel.CountryResponse;
import com.nearkingseller.responseModel.LoginResponse;
import com.nearkingseller.responseModel.Productcategory;
import com.nearkingseller.responseModel.ProfileResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by vinay on 12/8/2017.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("seller/login/")
    Call<LoginResponse> getLogin(@Field("username") String UserId, @Field("password") String pass);

    @GET("seller/country/")
    Call<List<CountryResponse>> getCountry();

    @GET("seller/categories/")
    Call<List<Productcategory>> getProductCatType();

    @FormUrlEncoded
    @POST("seller/registration/")
    Call<LoginResponse> getRegister(@Field("company_name") String company_name, @Field("first_name") String first_name, @Field("email") String email,
                                    @Field("phone") String phone, @Field("telephone") String telephone, @Field("password") String password,
                                    /*@Field("cnf_password") String cnf_password,*/ @Field("postcode") String postcode,@Field("seller_type") String seller_type);

//http://54.169.90.233:9000/seller/profile/vinaykumar.jjbytes@gmail.com
    @GET("seller/profile/{email}")
    Call<ProfileResponse> getProfile(@Path("email") String email);

    /*company_name:VinayShop
first_name:vinayKumar
email:vinay@gmail.com
phone:9560329235
telephone:0612998877
password:123456
cnf_password:123456
postcode:560068
seller_type:Retail*/
}


