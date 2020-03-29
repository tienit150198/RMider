package com.example.rmider.model;

import java.io.Serializable;

public class Maintain  implements Serializable  {

    private String key;
    private String ngay;
    private String Detail;
    private String soKm;
    private String Price;
    private String ngaytt;

    public Maintain() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getSoKm() {
        return soKm;
    }

    public void setSoKm(String soKm) {
        this.soKm = soKm;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getNgaytt() {
        return ngaytt;
    }

    public void setNgaytt(String ngaytt) {
        this.ngaytt = ngaytt;
    }
}
