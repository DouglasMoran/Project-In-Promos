package com.inpromos.app.models;

public class ColorModel {

    private int color_id;
    private String color_name;
    private int color_resource;
    private String color_hexadecimal;
    private boolean isSelected;

    public ColorModel(String color_name, int color_id, int color_resource, String color_hexadecimal) {
        this.color_name = color_name;
        this.color_id = color_id;
        this.color_resource = color_resource;
        this.color_hexadecimal = color_hexadecimal;
    }

    public ColorModel() {
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public int getColor_resource() {
        return color_resource;
    }

    public void setColor_resource(int color_resource) {
        this.color_resource = color_resource;
    }

    public String getColor_hexadecimal() {
        return color_hexadecimal;
    }

    public void setColor_hexadecimal(String color_hexadecimal) {
        this.color_hexadecimal = color_hexadecimal;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
