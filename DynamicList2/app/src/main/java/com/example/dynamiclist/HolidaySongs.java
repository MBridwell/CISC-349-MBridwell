package com.example.dynamiclist;

public class HolidaySongs {
    private String album_image;
    private String album_name;
    private String artist_name;
    private double danceability;
    private Integer duration_ms;
    private String playlist_img;

    public HolidaySongs(String album_image, String album_name, String artist_name, double danceability, Integer durantion_ms, String playlist_img){
        this.album_image = album_image;
        this.album_name = album_name;
        this.artist_name = artist_name;
        this.danceability = danceability;
        this.duration_ms = durantion_ms;
        this.playlist_img = playlist_img;
    }

    public String getalbum_image() {
        return album_image;
    }

    public void setalbum_image(String album_image){
        this.album_image=album_image;
    }

    public String getalbum_name() {
        return album_name;
    }
    public void setalbum_name(String album_name){
        this.album_name = album_name;
    }

    public String getartist_name() {
        return artist_name;
    }

    public void setartist_name(String artist_name){
        this.artist_name=artist_name;
    }

    public Double getdanceability() {
        return danceability;
    }
    public void setdanceability(double danceability){
        this.danceability = danceability;
    }
    public Integer getduration_ms() {
        return duration_ms;
    }
    public void setduration_ms(Integer duration_ms){
        this.duration_ms = duration_ms;
    }

    public String getplaylist_img() {
        return playlist_img;
    }

    public void setplaylist_img(String playlist_img){
        this.playlist_img = playlist_img;
    }

}
