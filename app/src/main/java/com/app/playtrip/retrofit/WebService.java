package com.app.playtrip.retrofit;


import com.app.playtrip.entities.Wrapper.ResponseWrapper;
import com.app.playtrip.entities.UserEnt;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface WebService {


    @FormUrlEncoded
    @POST("login")
    Call<ResponseWrapper<UserEnt>> loginUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("device_type") String device_type,
            @Field("device_token") String device_token
    );


}