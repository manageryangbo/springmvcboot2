package com.suneee.ep.promotion.model;

import java.math.BigDecimal;
import java.util.Date;

public class MarketDetail {
    private BigDecimal id;

    private BigDecimal decreaseMoney;

    private String gradeCode;

    private BigDecimal itemId;

    private BigDecimal rate;

    private String activityId;

    private Date createDate;

    private BigDecimal detailId;

    private Short status;

    private String isDeleteSuccess;

    private String isCreateSuccess;

    private Date updateDate;

    private String oprPerson;

    private String outerId;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getDecreaseMoney() {
        return decreaseMoney;
    }

    public void setDecreaseMoney(BigDecimal decreaseMoney) {
        this.decreaseMoney = decreaseMoney;
    }

    public String getGradeCode() {
        return gradeCode;
    }

    public void setGradeCode(String gradeCode) {
        this.gradeCode = gradeCode == null ? null : gradeCode.trim();
    }

    public BigDecimal getItemId() {
        return itemId;
    }

    public void setItemId(BigDecimal itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId == null ? null : activityId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getDetailId() {
        return detailId;
    }

    public void setDetailId(BigDecimal detailId) {
        this.detailId = detailId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public String getIsDeleteSuccess() {
        return isDeleteSuccess;
    }

    public void setIsDeleteSuccess(String isDeleteSuccess) {
        this.isDeleteSuccess = isDeleteSuccess == null ? null : isDeleteSuccess.trim();
    }

    public String getIsCreateSuccess() {
        return isCreateSuccess;
    }

    public void setIsCreateSuccess(String isCreateSuccess) {
        this.isCreateSuccess = isCreateSuccess == null ? null : isCreateSuccess.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getOprPerson() {
        return oprPerson;
    }

    public void setOprPerson(String oprPerson) {
        this.oprPerson = oprPerson == null ? null : oprPerson.trim();
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }
}