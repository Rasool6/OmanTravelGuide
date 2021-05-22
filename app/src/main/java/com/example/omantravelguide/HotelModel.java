package com.example.omantravelguide;

import java.io.Serializable;

public class HotelModel implements Serializable {

    public String id;
    public String hotel_name;
    public String lati;
    public String longi;
    public String rate;
    public String expert_rating;
    public String detail;
    public String address;
    public String imgUri;
    public String popular;

    public HotelModel(String hotel_name, String lati, String longi, String rate, String expert_rating, String detail, String address, String imgUri,String popular) {
        this.hotel_name = hotel_name;
        this.lati = lati;
        this.longi = longi;
        this.rate = rate;
        this.expert_rating = expert_rating;
        this.detail = detail;
        this.address = address;
        this.imgUri = imgUri;
        this.popular =  popular;
    }

    public HotelModel(String id, String hotel_name, String lati, String longi, String rate, String expert_rating, String detail, String address, String imgUri,String popular) {
        this.id = id;
        this.hotel_name = hotel_name;
        this.lati = lati;
        this.longi = longi;
        this.rate = rate;
        this.expert_rating = expert_rating;
        this.detail = detail;
        this.address = address;
        this.imgUri = imgUri;
        this.popular =  popular;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getExpert_rating() {
        return expert_rating;
    }

    public void setExpert_rating(String expert_rating) {
        this.expert_rating = expert_rating;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getPopular() {
        return popular;
    }

    public void setPopular(String popular) {
        this.popular = popular;
    }
}
