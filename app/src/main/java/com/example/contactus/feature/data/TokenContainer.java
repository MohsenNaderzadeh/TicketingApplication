package com.example.contactus.feature.data;

public class TokenContainer {
    private static String token;
    private static boolean isUser;

    public static void updateToken(String token) {
        TokenContainer.token = token;
    }

    public static String getToken() {
        return token;
    }

    public static boolean isIsUser() {
        return isUser;
    }

    public static void setIsUser(boolean isUser) {
        TokenContainer.isUser = isUser;
    }
}
