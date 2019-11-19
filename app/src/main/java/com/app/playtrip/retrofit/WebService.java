package com.app.playtrip.retrofit;


import com.app.playtrip.entities.FollowingEnt;
import com.app.playtrip.entities.User.DataUser;
import com.app.playtrip.entities.User.User;
import com.app.playtrip.entities.Wrapper.ResponseSimple;
import com.app.playtrip.entities.Wrapper.ResponseWrapper;
import com.app.playtrip.entities.Data;
import com.app.playtrip.entities.banners.BannersInnerData;
import com.app.playtrip.entities.trending.TrendingEntity;
import com.app.playtrip.entities.video.VideoInnerData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    @GET("videos")
    Call<ResponseWrapper<Data<VideoInnerData>>> getVideos(
        @Query("video_type") String videoType
    );
    @GET("videos")
    Call<ResponseWrapper<Data<VideoInnerData>>> getUserVideos(
            @Query("user_id") String user_id
    );

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
            @Field("title") RequestBody title,
            @Field("caption") RequestBody caption,
            @Part("user_id") RequestBody user_id,
            @Part("location_id") RequestBody location_id,
            @Part("video_length") RequestBody video_url,
            @Part("status") RequestBody status

            );

    @DELETE("videos/{id}")
    Call<ResponseSimple> deleteVideo(@Path("id") long itemId);


    @GET("users/get-users-by-type")
Call<ResponseWrapper<Data<TrendingEntity>>> getTrendingVideos(@Query("user_type") String user_type);//t[trendy,recent]

    @GET("users/get-user-by-id/{id}")
    Call<ResponseWrapper<User>> getUserDetail(@Path("id") String id);


    @GET("users/get-user-follower-profiles")
    Call<ResponseWrapper<Data<FollowingEnt>>> getFollower(@Query("user_id") String user_id);


    @GET("users/get-user-followed-profiles")
    Call<ResponseWrapper<Data<FollowingEnt>>> getFollowed(@Query("user_id") String user_id);


}