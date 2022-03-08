package lab4;

import java.io.Serializable;

public class Component implements Serializable {
    private int imgUrl;
    private String name;
    private double rate;
    private int rateAmount;
    private String price;
    private int discount;

    public Component(int imgUrl, String name, double rate, int rateAmount, String price, int discount) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.rate = rate;
        this.rateAmount = rateAmount;
        this.price = price;
        this.discount = discount;
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

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRateAmount() {
        return rateAmount;
    }

    public void setRateAmount(int rateAmount) {
        this.rateAmount = rateAmount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
