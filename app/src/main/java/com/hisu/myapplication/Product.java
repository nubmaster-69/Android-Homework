package com.hisu.myapplication;

public class Product {
    private int imgUrl;
    private String name;
    private String shop;

    public Product(int imgUrl, String name, String shop) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.shop = shop;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
