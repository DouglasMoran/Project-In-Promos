package com.inpromos.app.models;

import java.io.Serializable;

public class OrderModel implements Serializable {

    private int order_id;
    private String order_title;
    private String order_date;
    private double order_cost;

    public OrderModel(int order_id, String order_title, String order_date, double order_cost) {
        this.order_id = order_id;
        this.order_title = order_title;
        this.order_date = order_date;
        this.order_cost = order_cost;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getOrder_title() {
        return order_title;
    }

    public void setOrder_title(String order_title) {
        this.order_title = order_title;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public double getOrder_cost() {
        return order_cost;
    }

    public void setOrder_cost(double order_cost) {
        this.order_cost = order_cost;
    }

}
