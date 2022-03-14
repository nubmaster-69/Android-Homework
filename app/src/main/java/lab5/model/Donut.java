package lab5.model;

import java.io.Serializable;

public class Donut implements Serializable {

    private int imageResource;
    private String name;
    private String desc;
    private double price;

    public Donut() {
    }

    public Donut(int imageResource, String name, String desc, double price) {
        this.imageResource = imageResource;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}