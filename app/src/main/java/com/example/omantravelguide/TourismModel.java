package com.example.omantravelguide;

import java.io.Serializable;

public class TourismModel implements Serializable {

    public String toursim_id;
    public String name;
    public String price;
    public String tourDetail;
    public String imgUrl;
    public String include;
    public String notInclude;


    public TourismModel(String toursim_id, String name, String price, String tourDetail, String imgUrl, String include, String notInclude) {
        this.toursim_id = toursim_id;
        this.name = name;
        this.price = price;
        this.tourDetail = tourDetail;
        this.imgUrl = imgUrl;
        this.include = include;
        this.notInclude = notInclude;
    }

    public TourismModel(String name, String price, String tourDetail, String imgUrl, String include, String notInclude) {
        this.name = name;
        this.price = price;
        this.tourDetail = tourDetail;
        this.imgUrl = imgUrl;
        this.include = include;
        this.notInclude = notInclude;
    }

    public String getToursim_id() {
        return toursim_id;
    }

    public void setToursim_id(String toursim_id) {
        this.toursim_id = toursim_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTourDetail() {
        return tourDetail;
    }

    public void setTourDetail(String tourDetail) {
        this.tourDetail = tourDetail;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getNotInclude() {
        return notInclude;
    }

    public void setNotInclude(String notInclude) {
        this.notInclude = notInclude;
    }
}
