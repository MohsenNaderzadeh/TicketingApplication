package com.example.contactus.feature.data.api;

import com.example.contactus.BuildConfig;
import com.example.contactus.feature.data.TokenContainer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .addInterceptor(chain -> {
                        Request oldrequest = chain.request();
                        Request.Builder newRequest = oldrequest.newBuilder();
                        if (TokenContainer.getToken() != null && TokenContainer.isStudent()) {
                            newRequest.addHeader("Authorization", TokenContainer.getToken());
                        } else if (TokenContainer.getToken() != null && TokenContainer.isIsSupporter()) {
                            newRequest.addHeader("supporter_auth", TokenContainer.getToken());
                        }
                        newRequest.addHeader("Accept", "application/json");
                        newRequest.method(oldrequest.method(), oldrequest.body());
                        return chain.proceed(newRequest.build());
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.API_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}
