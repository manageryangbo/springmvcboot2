package com.marketcenter.dbo;

import java.math.BigDecimal;

public class PromotionalGoods {
    private Integer promGoodsId;

    private Integer promId;

    private Integer couponId;

    private String goodsCode;

    private String goodsName;

    private Short exclude;

    private BigDecimal goodsPromPrice;

    private BigDecimal goodsPromStock;

    private BigDecimal goodsVipPrice;

    private String giftCode;

    private String giftName;

    private String limitGoodsCode;

    private Short limitGoodsType;

    private BigDecimal goodsPersonStock;

    private BigDecimal goodsDayStock;

    private BigDecimal goodsPersonDayStock;

    public Integer getPromGoodsId() {
        return promGoodsId;
    }

    public void setPromGoodsId(Integer promGoodsId) {
        this.promGoodsId = promGoodsId;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Short getExclude() {
        return exclude;
    }

    public void setExclude(Short exclude) {
        this.exclude = exclude;
    }

    public BigDecimal getGoodsPromPrice() {
        return goodsPromPrice;
    }

    public void setGoodsPromPrice(BigDecimal goodsPromPrice) {
        this.goodsPromPrice = goodsPromPrice;
    }

    public BigDecimal getGoodsPromStock() {
        return goodsPromStock;
    }

    public void setGoodsPromStock(BigDecimal goodsPromStock) {
        this.goodsPromStock = goodsPromStock;
    }

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String giftCode) {
        this.giftCode = giftCode;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getLimitGoodsCode() {
        return limitGoodsCode;
    }

    public void setLimitGoodsCode(String limitGoodsCode) {
        this.limitGoodsCode = limitGoodsCode;
    }

    public Short getLimitGoodsType() {
        return limitGoodsType;
    }

    public void setLimitGoodsType(Short limitGoodsType) {
        this.limitGoodsType = limitGoodsType;
    }

    public BigDecimal getGoodsPersonStock() {
        return goodsPersonStock;
    }

    public void setGoodsPersonStock(BigDecimal goodsPersonStock) {
        this.goodsPersonStock = goodsPersonStock;
    }

    public BigDecimal getGoodsDayStock() {
        return goodsDayStock;
    }

    public void setGoodsDayStock(BigDecimal goodsDayStock) {
        this.goodsDayStock = goodsDayStock;
    }

    public BigDecimal getGoodsPersonDayStock() {
        return goodsPersonDayStock;
    }

    public void setGoodsPersonDayStock(BigDecimal goodsPersonDayStock) {
        this.goodsPersonDayStock = goodsPersonDayStock;
    }
}