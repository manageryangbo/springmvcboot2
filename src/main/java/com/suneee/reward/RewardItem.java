/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: RewardItem
 * Author:   martin
 * Date:     2018/4/8 11:02
 * Description: 奖项实体类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.reward;

import java.math.BigDecimal;
import java.util.Date;

public class RewardItem {
    /**
     * rid唯一编码
     */
    private Integer rid;
    /**
     * 企业标识
     */
    private String enterpriseCode;
    /**
     * 抽奖活动Id
     */
    private Integer rewardId;
    /**
     * 奖项类型( 1积分 2商品 ...等)
     */
    private Integer itemType;
    /**
     * 奖项名称
     */
    private String itemName;
    /**
     * 奖项积分
     */
    private Integer itemIntegral;
    /**
     * 奖项标识(商品标识)
     */
    private String goodsCode;
    /**
     * 奖项标识(优惠券标识)
     */
    private String couponCode;
    /**
     * 中奖概率
     */
    private BigDecimal winRatio;
    /**
     * 剩余奖品数量
     */
    private Integer remainNum;
    /**
     * 中奖限制(每个用户限制数额)
     */
    private Integer winLimit;
    /**
     * 图路径(JPG/PNG)
     */
    private String rewadImg;

    private Date createTm;

    private Date updateTm;

    private Long createUser;

    private Long updateUser;

    private String couponActivityEndTm;

    private String couponReceiveEndTm;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public Integer getRewardId() {
        return rewardId;
    }

    public void setRewardId(Integer rewardId) {
        this.rewardId = rewardId;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemIntegral() {
        return itemIntegral;
    }

    public void setItemIntegral(Integer itemIntegral) {
        this.itemIntegral = itemIntegral;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public BigDecimal getWinRatio() {
        return winRatio;
    }

    public void setWinRatio(BigDecimal winRatio) {
        this.winRatio = winRatio;
    }

    public Integer getRemainNum() {
        return remainNum;
    }

    public void setRemainNum(Integer remainNum) {
        this.remainNum = remainNum;
    }

    public Integer getWinLimit() {
        return winLimit;
    }

    public void setWinLimit(Integer winLimit) {
        this.winLimit = winLimit;
    }

    public String getRewadImg() {
        return rewadImg;
    }

    public void setRewadImg(String rewadImg) {
        this.rewadImg = rewadImg;
    }

    public Date getCreateTm() {
        return createTm;
    }

    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    public Date getUpdateTm() {
        return updateTm;
    }

    public void setUpdateTm(Date updateTm) {
        this.updateTm = updateTm;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getCouponActivityEndTm() {
        return couponActivityEndTm;
    }

    public void setCouponActivityEndTm(String couponActivityEndTm) {
        this.couponActivityEndTm = couponActivityEndTm;
    }

    public String getCouponReceiveEndTm() {
        return couponReceiveEndTm;
    }

    public void setCouponReceiveEndTm(String couponReceiveEndTm) {
        this.couponReceiveEndTm = couponReceiveEndTm;
    }

    @Override
    public String toString() {
        return "RewardItem{" +
                "rid=" + rid +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", rewardId=" + rewardId +
                ", itemType=" + itemType +
                ", itemName='" + itemName + '\'' +
                ", itemIntegral=" + itemIntegral +
                ", goodsCode='" + goodsCode + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", winRatio=" + winRatio +
                ", remainNum=" + remainNum +
                ", winLimit=" + winLimit +
                ", rewadImg='" + rewadImg + '\'' +
                ", createTm=" + createTm +
                ", updateTm=" + updateTm +
                ", createUser=" + createUser +
                ", updateUser=" + updateUser +
                ", couponActivityEndTm='" + couponActivityEndTm + '\'' +
                ", couponReceiveEndTm='" + couponReceiveEndTm + '\'' +
                '}';
    }
}
