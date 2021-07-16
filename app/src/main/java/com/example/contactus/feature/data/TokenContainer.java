package com.example.contactus.feature.data;

public class TokenContainer {
    private static String token;
    private static boolean isStudent;
    private static boolean isSupporter;
    
    public static void updateToken(String token) {
        TokenContainer.token = token;
    }
    
    public static String getToken() {
        return token;
    }
    
    public static boolean isStudent() {
        return isStudent;
    }
    
    public static void setIsStudent(boolean isStudent) {
        TokenContainer.isStudent = isStudent;
    }
    
    public static boolean isIsSupporter() {
        return isSupporter;
    }
    
    public static void setIsSupporter(boolean isSupporter) {
        TokenContainer.isSupporter = isSupporter;
    }
}
