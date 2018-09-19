package com.marketcenter.dbo;

public class PromLogo {
    private Integer promLogoId;

    private Integer promId;

    private Short logoFlag;

    private String imagePath;

    private String imagePosition;

    private String textTitle;

    private String textColor;

    private String textBackColor;

    private Short textFlag;

    public Integer getPromLogoId() {
        return promLogoId;
    }

    public void setPromLogoId(Integer promLogoId) {
        this.promLogoId = promLogoId;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Short getLogoFlag() {
        return logoFlag;
    }

    public void setLogoFlag(Short logoFlag) {
        this.logoFlag = logoFlag;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePosition() {
        return imagePosition;
    }

    public void setImagePosition(String imagePosition) {
        this.imagePosition = imagePosition;
    }

    public String getTextTitle() {
        return textTitle;
    }

    public void setTextTitle(String textTitle) {
        this.textTitle = textTitle;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getTextBackColor() {
        return textBackColor;
    }

    public void setTextBackColor(String textBackColor) {
        this.textBackColor = textBackColor;
    }

    public Short getTextFlag() {
        return textFlag;
    }

    public void setTextFlag(Short textFlag) {
        this.textFlag = textFlag;
    }
}