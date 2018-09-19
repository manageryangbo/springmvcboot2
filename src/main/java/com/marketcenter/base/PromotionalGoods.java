package com.marketcenter.base;

import java.math.BigDecimal;

/**
 * 促销商品
 * 
 * @author yangbo
 *
 */
public class PromotionalGoods {
    private Integer promGoodsId;

	private Integer promId;

	private Integer couponId;

	private String goodsCode;

	private String goodsName;

	private Integer exclude; // 0 包含   1 排除
	
	private BigDecimal goodsPromPrice;
	
	private BigDecimal goodsPromStock; //商品促销库存(秒杀)

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

	public Integer getExclude() {
		return exclude;
	}

	public void setExclude(Integer exclude) {
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

	
}