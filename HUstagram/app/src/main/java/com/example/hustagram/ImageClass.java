package com.example.hustagram;

import android.graphics.Bitmap;
import android.media.Image;

public class ImageClass {
    private String comment, date;
    private Bitmap picture;

    public ImageClass(String comment, String date, Bitmap picture){
        this.comment = comment;
        this.date = date;
        this.picture = picture;
    }

    public String getComment(){
        return comment;
    }

    public void setName(String comment){
        this.comment=comment;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }

    public Bitmap getPicture(){
        return picture;
    }

    public void setPicture(Bitmap picture){
        this.picture=picture;
    }
}
