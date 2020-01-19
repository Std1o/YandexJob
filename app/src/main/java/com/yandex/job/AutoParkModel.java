package com.yandex.job;

public class AutoParkModel {

    public String id;
    public String brand;
    public String color;
    public String price;
    public String year;
    public String photo;
    public String trans;

    public AutoParkModel() {

    }

    public AutoParkModel(String id, String brand, String color, String price, String year, String photo, String trans) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.price = price;
        this.year = year;
        this.photo = photo;
        this.trans = trans;
    }
}
