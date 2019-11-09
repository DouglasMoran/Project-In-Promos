package com.inpromos.app.models;

public class PriceModel {

    private int priceId;
    private Double price;
    private int productId;

    public PriceModel(int priceId, Double price, int productId) {
        this.priceId = priceId;
        this.price = price;
        this.productId = productId;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}


