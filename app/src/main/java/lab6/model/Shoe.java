package lab6.model;

import java.io.Serializable;

public class Shoe implements Serializable {
    private int imageResource;
    private String brand;
    private String name;
    private double price;
    private double discount;
    private String desc;

    public Shoe(int imageResource, String brand, String name, double price, double discount, String desc) {
        this.imageResource = imageResource;
        this.brand = brand;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.desc = desc;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}