package com.app.playtrip.retrofit;


import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.entities.Wrapper.ResponseWrapper;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.banners.BannersInnerData;
import com.app.playtrip.entities.video.VideoInnerData;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @GET("videos")
    Call<ResponseWrapper<Data<VideoInnerData>>> getVideos();

    @GET("banners")
    Call<ResponseWrapper<Data<BannersInnerData>>> getBanners();

    @FormUrlEncoded
    @POST("videos")
    Call<ResponseWrapper<DataUser>> uploadVideo(
            @Field("title[en]") String title,
            @Field("caption[en]") String caption,
            @Field("user_id") String user_id,
            @Field("location_id") String location_id,
            @Field("video_url") String video_url,
            @Field("thumbnail_image") String thumbnail_image,
            @Field("video_length") String video_length,
            @Field("status") String status
    );

    @Multipart
    @POST("videos")
    Call<ResponseWrapper<DataUser>> uploadVideo(
            @Part MultipartBody.Part filePic,
            @Part ("video_url") RequestBody fileVideo,
            @Part("title") RequestBody title,
            @Part("caption") RequestBody caption,
            @Part("user_id") RequestBody user_id,
            @Part("location_id") RequestBody location_id,
            @Part("video_length") RequestBody video_url,
            @Part("status") RequestBody status

            );





}