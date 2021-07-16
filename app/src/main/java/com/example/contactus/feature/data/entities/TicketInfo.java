package com.example.contactus.feature.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TicketInfo implements Parcelable {

    public static final Parcelable.Creator<TicketInfo> CREATOR = new Parcelable.Creator<TicketInfo>() {
        @Override
        public TicketInfo createFromParcel(Parcel source) {
            return new TicketInfo(source);
        }

        @Override
        public TicketInfo[] newArray(int size) {
            return new TicketInfo[size];
        }
    };
    @SerializedName("TicketId")
    private int ticketId;
    @SerializedName("TicketOwnerId")
    private int ticketOwnerId;
    @SerializedName("TicketRelatedAdministrativeDepartemantId")
    private int ticketRelatedAdministrativeDepartemantId;
    @SerializedName("TicketTitle")
    private String ticketTitle;
    @SerializedName("TicketStatus")
    private int ticketStatus;
    @SerializedName("TicketSubmitDate")
    private String ticketSubmitDate;
    @SerializedName("TicketLastMessage")
    private Message TicketLastMessage;
    
    public TicketInfo() {
    }
    
    public TicketInfo(int ticketId, int ticketOwnerId, int ticketRelatedAdministrativeDepartemantId, String ticketTitle, int ticketStatus, String ticketSubmitDate, Message ticketLastMessage) {
        this.ticketId = ticketId;
        this.ticketOwnerId = ticketOwnerId;
        this.ticketRelatedAdministrativeDepartemantId = ticketRelatedAdministrativeDepartemantId;
        this.ticketTitle = ticketTitle;
        this.ticketStatus = ticketStatus;
        this.ticketSubmitDate = ticketSubmitDate;
        TicketLastMessage = ticketLastMessage;
    }
    
    protected TicketInfo(Parcel in) {
        this.ticketId = in.readInt();
        this.ticketOwnerId = in.readInt();
        this.ticketRelatedAdministrativeDepartemantId = in.readInt();
        this.ticketTitle = in.readString();
        this.ticketStatus = in.readInt();
        this.ticketSubmitDate = in.readString();
    }
    
    public Message getTicketLastMessage() {
        return TicketLastMessage;
    }

    public void setTicketLastMessage(Message ticketLastMessage) {
        TicketLastMessage = ticketLastMessage;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketOwnerId() {
        return ticketOwnerId;
    }

    public void setTicketOwnerId(int ticketOwnerId) {
        this.ticketOwnerId = ticketOwnerId;
    }

    public int getTicketRelatedAdministrativeDepartemantId() {
        return ticketRelatedAdministrativeDepartemantId;
    }

    public void setTicketRelatedAdministrativeDepartemantId(int ticketRelatedAdministrativeDepartemantId) {
        this.ticketRelatedAdministrativeDepartemantId = ticketRelatedAdministrativeDepartemantId;
    }

    public String getTicketTitle() {
        return ticketTitle;
    }

    public void setTicketTitle(String ticketTitle) {
        this.ticketTitle = ticketTitle;
    }

    public int getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(int ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public String getTicketSubmitDate() {
        return ticketSubmitDate;
    }

    public void setTicketSubmitDate(String ticketSubmitDate) {
        this.ticketSubmitDate = ticketSubmitDate;
    }

    @Override
    public String toString() {
        return
                "TicketInfo{" +
                        "ticketId = '" + ticketId + '\'' +
                        ",ticketOwnerId = '" + ticketOwnerId + '\'' +
                        ",ticketRelatedAdministrativeDepartemantId = '" + ticketRelatedAdministrativeDepartemantId + '\'' +
                        ",ticketTitle = '" + ticketTitle + '\'' +
                        ",ticketStatus = '" + ticketStatus + '\'' +
                        ",ticketSubmitDate = '" + ticketSubmitDate + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ticketId);
        dest.writeInt(this.ticketOwnerId);
        dest.writeInt(this.ticketRelatedAdministrativeDepartemantId);
        dest.writeString(this.ticketTitle);
        dest.writeInt(this.ticketStatus);
        dest.writeString(this.ticketSubmitDate);
    }

    public void readFromParcel(Parcel source) {
        this.ticketId = source.readInt();
        this.ticketOwnerId = source.readInt();
        this.ticketRelatedAdministrativeDepartemantId = source.readInt();
        this.ticketTitle = source.readString();
        this.ticketStatus = source.readInt();
        this.ticketSubmitDate = source.readString();
    }
}