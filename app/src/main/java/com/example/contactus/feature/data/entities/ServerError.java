package com.example.contactus.feature.data.entities;

public class ServerError {
    private final String message;

    public ServerError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
