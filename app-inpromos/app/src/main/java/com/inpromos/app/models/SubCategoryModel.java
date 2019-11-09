package com.inpromos.app.models;

public class SubCategoryModel {

    private int subCategoryId;
    private String subCatgoryName;
    private CategoryModel category;

    public SubCategoryModel(int subCategoryId, String subCatgoryName, CategoryModel category) {
        this.subCategoryId = subCategoryId;
        this.subCatgoryName = subCatgoryName;
        this.category = category;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCatgoryName() {
        return subCatgoryName;
    }

    public void setSubCatgoryName(String subCatgoryName) {
        this.subCatgoryName = subCatgoryName;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

}
