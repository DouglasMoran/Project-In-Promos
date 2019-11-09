package com.inpromos.app.models;

public class QuotationProductModel {

    private int productId;
    private int quantityItemSelected;
    private String quotationProductImage;

    public QuotationProductModel(int productId, int quantityItemSelected, String quotationProductImage) {
        this.productId = productId;
        this.quantityItemSelected = quantityItemSelected;
        this.quotationProductImage = quotationProductImage;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantityItemSelected() {
        return quantityItemSelected;
    }

    public void setQuantityItemSelected(int quantityItemSelected) {
        this.quantityItemSelected = quantityItemSelected;
    }

    public String getQuotationProductImage() {
        return quotationProductImage;
    }

    public void setQuotationProductImage(String quotationProductImage) {
        this.quotationProductImage = quotationProductImage;
    }

}
