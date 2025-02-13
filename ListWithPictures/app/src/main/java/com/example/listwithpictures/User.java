package com.example.listwithpictures;

public class User {
    private String name;
    private String phoneNumber;
    private String imageurl;


    public User(String name, String phoneNumber, String imageurl){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.imageurl = imageurl;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getImageurl(){
        return imageurl;
    }
}
