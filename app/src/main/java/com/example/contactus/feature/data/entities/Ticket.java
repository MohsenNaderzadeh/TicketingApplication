package com.example.contactus.feature.data.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Ticket implements Parcelable {
    private int id;
    private String title;
    private String description;
    private String status;
    public static final Parcelable.Creator<Ticket> CREATOR = new Parcelable.Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel source) {
            return new Ticket(source);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public Ticket(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private List<Message> messageList;

    protected Ticket(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.status = in.readString();
        this.messageList = new ArrayList<Message>();
        in.readList(this.messageList, Message.class.getClassLoader());
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.status);
        dest.writeList(this.messageList);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.title = source.readString();
        this.description = source.readString();
        this.status = source.readString();
        this.messageList = new ArrayList<Message>();
        source.readList(this.messageList, Message.class.getClassLoader());
    }
}
