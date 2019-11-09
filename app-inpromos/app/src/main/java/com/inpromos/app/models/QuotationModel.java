package com.inpromos.app.models;

import java.io.Serializable;
import java.util.List;

public class QuotationModel implements Serializable {

    private int quotationId;
    private String quotationDescription;
    private Double quotationSubtotal;
    private Double quotationTotal;
    private int userId;
    private List<QuotationProductModel> products;

    public QuotationModel(int quotationId, String quotationDescription, Double quotationSubtotal, Double quotationTotal, int userId, List<QuotationProductModel> products) {
        this.quotationId = quotationId;
        this.quotationDescription = quotationDescription;
        this.quotationSubtotal = quotationSubtotal;
        this.quotationTotal = quotationTotal;
        this.userId = userId;
        this.products = products;
    }



    public int getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(int quotationId) {
        this.quotationId = quotationId;
    }

    public String getQuotationDescription() {
        return quotationDescription;
    }

    public void setQuotationDescription(String quotationDescription) {
        this.quotationDescription = quotationDescription;
    }

    public Double getQuotationSubtotal() {
        return quotationSubtotal;
    }

    public void setQuotationSubtotal(Double quotationSubtotal) {
        this.quotationSubtotal = quotationSubtotal;
    }

    public Double getQuotationTotal() {
        return quotationTotal;
    }

    public void setQuotationTotal(Double quotationTotal) {
        this.quotationTotal = quotationTotal;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<QuotationProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<QuotationProductModel> products) {
        this.products = products;
    }

}
