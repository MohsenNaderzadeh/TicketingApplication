package com.example.contactus.feature.data.api;

import com.example.contactus.feature.data.entities.Token;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

    @POST("User/Login.php")
    @FormUrlEncoded
    Single<Token> userAuthenticate(@Field("username") String userName, @Field("password") String password);

    @POST("Supporter/Login.php")
    @FormUrlEncoded
    Single<Token> supporterAuthenticate(@Field("username") String userName, @Field("password") String password);

}
