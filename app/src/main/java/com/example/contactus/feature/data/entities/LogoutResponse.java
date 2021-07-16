package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class LogoutResponse {
    
    @SerializedName("success")
    private boolean success;
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    @Override
    public String toString() {
        return
                "LogoutResponse{" +
                        "success = '" + success + '\'' +
                        "}";
    }
}