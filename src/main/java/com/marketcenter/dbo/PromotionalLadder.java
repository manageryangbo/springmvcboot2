package com.marketcenter.dbo;

import java.math.BigDecimal;

public class PromotionalLadder {
    private Integer promLadderId;

    private Integer promId;

    private BigDecimal ladderFull;

    private Integer couponId;

    private Short minusType;

    private BigDecimal minusSale;

    private Integer minusNum;

    private Integer minusLadderFull;

    private BigDecimal discount;

    private BigDecimal eachPieceMoney;

    private BigDecimal thePiecesDiscount;

    private BigDecimal minusMoney;

    private Short changeBuyLimit;

    private String goodsCode;

    private BigDecimal changeBuyPrices;

    private Short changeBuyTotal;

    private Short orderLimitPerson;

    public Integer getPromLadderId() {
        return promLadderId;
    }

    public void setPromLadderId(Integer promLadderId) {
        this.promLadderId = promLadderId;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public BigDecimal getLadderFull() {
        return ladderFull;
    }

    public void setLadderFull(BigDecimal ladderFull) {
        this.ladderFull = ladderFull;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public Short getMinusType() {
        return minusType;
    }

    public void setMinusType(Short minusType) {
        this.minusType = minusType;
    }

    public BigDecimal getMinusSale() {
        return minusSale;
    }

    public void setMinusSale(BigDecimal minusSale) {
        this.minusSale = minusSale;
    }

    public Integer getMinusNum() {
        return minusNum;
    }

    public void setMinusNum(Integer minusNum) {
        this.minusNum = minusNum;
    }

    public Integer getMinusLadderFull() {
        return minusLadderFull;
    }

    public void setMinusLadderFull(Integer minusLadderFull) {
        this.minusLadderFull = minusLadderFull;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getEachPieceMoney() {
        return eachPieceMoney;
    }

    public void setEachPieceMoney(BigDecimal eachPieceMoney) {
        this.eachPieceMoney = eachPieceMoney;
    }

    public BigDecimal getThePiecesDiscount() {
        return thePiecesDiscount;
    }

    public void setThePiecesDiscount(BigDecimal thePiecesDiscount) {
        this.thePiecesDiscount = thePiecesDiscount;
    }

    public BigDecimal getMinusMoney() {
        return minusMoney;
    }

    public void setMinusMoney(BigDecimal minusMoney) {
        this.minusMoney = minusMoney;
    }

    public Short getChangeBuyLimit() {
        return changeBuyLimit;
    }

    public void setChangeBuyLimit(Short changeBuyLimit) {
        this.changeBuyLimit = changeBuyLimit;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public BigDecimal getChangeBuyPrices() {
        return changeBuyPrices;
    }

    public void setChangeBuyPrices(BigDecimal changeBuyPrices) {
        this.changeBuyPrices = changeBuyPrices;
    }

    public Short getChangeBuyTotal() {
        return changeBuyTotal;
    }

    public void setChangeBuyTotal(Short changeBuyTotal) {
        this.changeBuyTotal = changeBuyTotal;
    }

    public Short getOrderLimitPerson() {
        return orderLimitPerson;
    }

    public void setOrderLimitPerson(Short orderLimitPerson) {
        this.orderLimitPerson = orderLimitPerson;
    }
}