package com.example.clinic_project.service;

public class Token {

    public static class MyToken{
        public static String getToken() { return token; }

        public static void setToken(String token) { MyToken.token = token; }

        public static String token;
        public MyToken(){
        }
    }

}

