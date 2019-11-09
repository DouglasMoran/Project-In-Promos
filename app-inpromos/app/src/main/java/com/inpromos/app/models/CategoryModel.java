package com.inpromos.app.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {

    private int categoryId;
    private String categoryName;
    private String categoryImgUrl;

    public CategoryModel(int categoryId, String categoryName, String categoryImgUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryImgUrl = categoryImgUrl;
    }

    public CategoryModel() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImgUrl() {
        return categoryImgUrl;
    }

    public void setCategoryImgUrl(String categoryImgUrl) {
        this.categoryImgUrl = categoryImgUrl;
    }

}
