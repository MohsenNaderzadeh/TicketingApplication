package com.example.contactus.feature.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Message implements Parcelable {


    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
    private sendStatus messageSendStatus = sendStatus.UNSEND;
    @SerializedName("studentId")
    private int studentId;
    @SerializedName("TicketId")
    private int ticketId;
    @SerializedName("CoworkerId")
    private int coworkerId;
    @SerializedName("MessageSendDate")
    private String messageSendDate;
    @SerializedName("MessageSendType")
    private int messageSendType;
    @SerializedName("MessageText")
    private String messageText;
    @SerializedName("isMessageaFile")
    private boolean isMessageaFile;
    @SerializedName("MessageId")
    private int messageId;

    public Message() {
    }

    protected Message(Parcel in) {
        int tmpMessageSendStatus = in.readInt();
        this.messageSendStatus = tmpMessageSendStatus == -1 ? null : sendStatus.values()[tmpMessageSendStatus];
        this.studentId = in.readInt();
        this.ticketId = in.readInt();
        this.coworkerId = in.readInt();
        this.messageSendDate = in.readString();
        this.messageSendType = in.readInt();
        this.messageText = in.readString();
        this.isMessageaFile = in.readByte() != 0;
        this.messageId = in.readInt();
    }

    public sendStatus getMessageSendStatus() {
        return messageSendStatus;
    }

    public void setMessageSendStatus(sendStatus messageSendStatus) {
        this.messageSendStatus = messageSendStatus;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCoworkerId() {
        return coworkerId;
    }

    public void setCoworkerId(int coworkerId) {
        this.coworkerId = coworkerId;
    }

    public String getMessageSendDate() {
        return messageSendDate;
    }

    public void setMessageSendDate(String messageSendDate) {
        this.messageSendDate = messageSendDate;
    }

    public int getMessageSendType() {
        return messageSendType;
    }

    public void setMessageSendType(int messageSendType) {
        this.messageSendType = messageSendType;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public boolean getIsMessageaFile() {
        return isMessageaFile;
    }

    public void setIsMessageaFile(boolean isMessageaFile) {
        this.isMessageaFile = isMessageaFile;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.messageSendStatus == null ? -1 : this.messageSendStatus.ordinal());
        dest.writeInt(this.studentId);
        dest.writeInt(this.ticketId);
        dest.writeInt(this.coworkerId);
        dest.writeString(this.messageSendDate);
        dest.writeInt(this.messageSendType);
        dest.writeString(this.messageText);
        dest.writeByte(this.isMessageaFile ? (byte) 1 : (byte) 0);
        dest.writeInt(this.messageId);
    }

    public void readFromParcel(Parcel source) {
        int tmpMessageSendStatus = source.readInt();
        this.messageSendStatus = tmpMessageSendStatus == -1 ? null : sendStatus.values()[tmpMessageSendStatus];
        this.studentId = source.readInt();
        this.ticketId = source.readInt();
        this.coworkerId = source.readInt();
        this.messageSendDate = source.readString();
        this.messageSendType = source.readInt();
        this.messageText = source.readString();
        this.isMessageaFile = source.readByte() != 0;
        this.messageId = source.readInt();
    }

    public enum sendStatus {
        SEND, UNSEND
    }
}

