package com.dao;

public class UserDao1 {
    private static UserDao1 instance = new UserDao1();
    private UserDao1(){};
    public static UserDao1 getInstance() {
        return instance;
    }
    public void showMessage(){
        System.out.println("Hello World!");
    }


}
