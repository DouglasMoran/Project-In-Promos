package com.inpromos.app.models;

public class ProductModel {

    private int product_id;
    private String product_name;
    private String prodyct_description;
    private Double product_price;
    private int product_quantity;

    public ProductModel(int product_id, String product_name, String prodyct_description, Double product_price, int product_quantity) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.prodyct_description = prodyct_description;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
    }

    public ProductModel() {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProdyct_description() {
        return prodyct_description;
    }

    public void setProdyct_description(String prodyct_description) {
        this.prodyct_description = prodyct_description;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(int product_quantity) {
        this.product_quantity = product_quantity;
    }
}
