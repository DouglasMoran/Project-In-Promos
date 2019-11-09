package com.inpromos.app.models;

public class ColorModel {

    private int colorId;
    private String colorName;
    private String colorHexadecimal;

    public ColorModel(String colorName, int colorId, String colorHexadecimal) {
        this.colorName = colorName;
        this.colorId = colorId;
        this.colorHexadecimal = colorHexadecimal;
    }

    public ColorModel() {
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColorHexadecimal() {
        return colorHexadecimal;
    }

    public void setColorHexadecimal(String colorHexadecimal) {
        this.colorHexadecimal = colorHexadecimal;
    }

}
