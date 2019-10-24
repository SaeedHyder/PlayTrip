package com.app.playtrip.retrofit;


import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.entities.User.User;
import com.app.playtrip.entities.Wrapper.ResponseWrapper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebService {


    @FormUrlEncoded
    @POST("login")
    Call<ResponseWrapper<DataUser>> loginUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_type") String device_type,
            @Field("device_token") String device_token
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseWrapper<DataUser>> signup(
            @Field("first_name") String first_name,
            @Field("last_name") String last_name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("password_confirmation") String password_confirmation,
            @Field("device_token") String device_token,
            @Field("device_type") String device_type
    );

    @FormUrlEncoded
    @POST("login")
    Call<ResponseWrapper<DataUser>> loginUser(
            @Field("email") String email
    );
}