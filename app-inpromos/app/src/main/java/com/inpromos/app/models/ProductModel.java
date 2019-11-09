package com.inpromos.app.models;

import java.io.Serializable;
import java.util.List;

public class ProductModel implements Serializable {

    private int productId;
    private String productName;
    private String productGenre;
    private String productImage;
    private String productMaterial;
    private String productDescription;
    private int categoryId;
    private int productStock;
    private PriceModel productPrice;
    private List<ColorModel> colors;
    private boolean isExpanded;

    public ProductModel(int productId, String productName, String productGenre, String productImage, String productMaterial, String productDescription, int productStock, PriceModel productPrice, List<ColorModel> colors, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productGenre = productGenre;
        this.productImage = productImage;
        this.productMaterial = productMaterial;
        this.productDescription = productDescription;
        this.productStock = productStock;
        this.productPrice = productPrice;
        this.colors = colors;
        this.categoryId = categoryId;
    }

    public ProductModel() {
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

    public List<ColorModel> getColors() {
        return colors;
    }

    public void setColors(List<ColorModel> colors) {
        this.colors = colors;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
