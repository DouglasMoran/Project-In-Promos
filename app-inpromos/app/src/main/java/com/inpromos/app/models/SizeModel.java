package com.inpromos.app.models;

import java.io.Serializable;

public class SizeModel implements Serializable {

    private int size_id;
    private String size_name;
    private Double size_width;
    private Double size_height;
    private Double size_radius;

    public SizeModel(int size_id, String size_name, Double size_width, Double size_height) {
        this.size_id = size_id;
        this.size_name = size_name;
        this.size_width = size_width;
        this.size_height = size_height;
    }

    public SizeModel(int size_id, String size_name, Double size_width, Double size_height, Double size_radius) {
        this.size_id = size_id;
        this.size_name = size_name;
        this.size_width = size_width;
        this.size_height = size_height;
        this.size_radius = size_radius;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public Double getSize_width() {
        return size_width;
    }

    public void setSize_width(Double size_width) {
        this.size_width = size_width;
    }

    public Double getSize_height() {
        return size_height;
    }

    public void setSize_height(Double size_height) {
        this.size_height = size_height;
    }

    public Double getSize_radius() {
        return size_radius;
    }

    public void setSize_radius(Double size_radius) {
        this.size_radius = size_radius;
    }
}
