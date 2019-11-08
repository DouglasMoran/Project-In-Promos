package com.inpromos.app.models;

public class CategoryModel {

    private int category_id;
    private String category_name;
    private String category_img_path;

    public CategoryModel(int category_id, String category_name, String category_img_path) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.category_img_path = category_img_path;
    }

    public CategoryModel() {
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCategory_img_path() {
        return category_img_path;
    }

    public void setCategory_img_path(String category_img_path) {
        this.category_img_path = category_img_path;
    }

}
