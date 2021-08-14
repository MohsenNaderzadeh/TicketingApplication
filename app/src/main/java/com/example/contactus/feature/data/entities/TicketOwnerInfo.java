package com.example.contactus.feature.data.entities;

import com.google.gson.annotations.SerializedName;

public class TicketOwnerInfo {
    
    @SerializedName("success")
    private boolean success;
    
    @SerializedName("ownerInfo")
    private OwnerInfo ownerInfo;
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public OwnerInfo getOwnerInfo() {
        return ownerInfo;
    }
    
    public void setOwnerInfo(OwnerInfo ownerInfo) {
        this.ownerInfo = ownerInfo;
    }
    
    @Override
    public String toString() {
        return
                "TicketOwnerInfo{" +
                        "success = '" + success + '\'' +
                        ",ownerInfo = '" + ownerInfo + '\'' +
                        "}";
    }
}