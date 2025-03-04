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

    public String getAlbumImage() {
        return album_image;
    }

    public String getAlbumName() {
        return album_name;
    }

    public String getArtistName() {
        return artist_name;
    }

    public double getDanceability() {
        return danceability;
    }

    public Integer getDurationMs() {
        return duration_ms;
    }

    public String getPlaylistImg() {
        return playlist_img;
    }
}
