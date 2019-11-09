package com.inpromos.app.models;

import java.util.List;

public class ProductModel {

    private int productId;
    private String productName;
    private String productGenre;
    private String productImage;
    private String productMaterial;
    private String productDescription;
    private int productStock;
    private PriceModel productPrice;
    private List<ColorModel> color;
    private SubCategoryModel subcategory;
    private boolean isExpanded;

    public ProductModel(int productId, String productName, String productGenre, String productImage, String productMaterial, String productDescription, int productStock, PriceModel productPrice, List<ColorModel> color, SubCategoryModel subcategory) {
        this.productId = productId;
        this.productName = productName;
        this.productGenre = productGenre;
        this.productImage = productImage;
        this.productMaterial = productMaterial;
        this.productDescription = productDescription;
        this.productStock = productStock;
        this.productPrice = productPrice;
        this.color = color;
        this.subcategory = subcategory;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductGenre() {
        return productGenre;
    }

    public void setProductGenre(String productGenre) {
        this.productGenre = productGenre;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductMaterial() {
        return productMaterial;
    }

    public void setProductMaterial(String productMaterial) {
        this.productMaterial = productMaterial;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }

    public PriceModel getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(PriceModel productPrice) {
        this.productPrice = productPrice;
    }

    public List<ColorModel> getColor() {
        return color;
    }

    public void setColor(List<ColorModel> color) {
        this.color = color;
    }

    public SubCategoryModel getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(SubCategoryModel subcategory) {
        this.subcategory = subcategory;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

}
