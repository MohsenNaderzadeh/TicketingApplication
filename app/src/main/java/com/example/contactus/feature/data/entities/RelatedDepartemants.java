package com.example.contactus.feature.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class RelatedDepartemants implements Parcelable {

    public static final Parcelable.Creator<RelatedDepartemants> CREATOR = new Parcelable.Creator<RelatedDepartemants>() {
        @Override
        public RelatedDepartemants createFromParcel(Parcel source) {
            return new RelatedDepartemants(source);
        }

        @Override
        public RelatedDepartemants[] newArray(int size) {
            return new RelatedDepartemants[size];
        }
    };
    @SerializedName("DepartemantName")
    private String departemantName;
    @SerializedName("DepartemantId")
    private int departemantId;

    public RelatedDepartemants(String departemantName, int departemantId) {
        this.departemantName = departemantName;
        this.departemantId = departemantId;
    }

    protected RelatedDepartemants(Parcel in) {
        this.departemantName = in.readString();
        this.departemantId = in.readInt();
    }

    public String getDepartemantName() {
        return departemantName;
    }

    public void setDepartemantName(String departemantName) {
        this.departemantName = departemantName;
    }

    public int getDepartemantId() {
        return departemantId;
    }

    public void setDepartemantId(int departemantId) {
        this.departemantId = departemantId;
    }

    @Override
    public String toString() {
        return this.departemantName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.departemantName);
        dest.writeInt(this.departemantId);
    }

    public void readFromParcel(Parcel source) {
        this.departemantName = source.readString();
        this.departemantId = source.readInt();
    }
}