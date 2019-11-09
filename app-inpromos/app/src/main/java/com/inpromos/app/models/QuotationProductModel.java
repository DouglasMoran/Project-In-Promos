package com.inpromos.app.models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class QuotationProductModel implements Serializable {

    private int productId;
    private int quantityItemSelected;
    private String quotationProductImage;
    private byte[] quotationProductPreview;

    public QuotationProductModel(int productId, int quantityItemSelected, String quotationProductImage) {
        this.productId = productId;
        this.quantityItemSelected = quantityItemSelected;
        this.quotationProductImage = quotationProductImage;
    }

    public QuotationProductModel(int productId, int quantityItemSelected, String quotationProductImage, byte[] quotationProductPreview) {
        this.productId = productId;
        this.quantityItemSelected = quantityItemSelected;
        this.quotationProductImage = quotationProductImage;
        this.quotationProductPreview = quotationProductPreview;
    }

    public QuotationProductModel() {
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

    public byte[] getQuotationProductPreview() {
        return quotationProductPreview;
    }

    public void setQuotationProductPreview(byte[] quotationProductPreview) {
        this.quotationProductPreview = quotationProductPreview;
    }
}
