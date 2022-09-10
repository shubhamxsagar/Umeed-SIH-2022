package com.example.umeed.Map;

import com.google.gson.annotations.SerializedName;

public class MapModel {

    @SerializedName("City")
    private String city;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("rating")
    private String rating;

    @SerializedName("url")
    private String url;

//    public MapModel(String city, String latitude, String longitude, String rating, String url) {
//        this.city = city;
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.rating = rating;
//        this.url = url;
//    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
