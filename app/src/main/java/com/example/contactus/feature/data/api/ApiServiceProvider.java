package com.example.contactus.feature.data.api;

public class ApiServiceProvider {
    private static ApiService apiService;


    public static ApiService getApiService() {
        if (apiService == null) {
            apiService = RetrofitProvider.getRetrofit().create(ApiService.class);
        }
        return apiService;
    }
}
