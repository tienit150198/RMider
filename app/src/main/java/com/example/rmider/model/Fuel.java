package com.example.rmider.model;

public class Fuel {
    private String  name;
    private int     picture;
    private int     price;
    private int     count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Fuel(String name, int picture, int price) {
        this.name = name;
        this.picture = picture;
        this.price = price;
    }
}
