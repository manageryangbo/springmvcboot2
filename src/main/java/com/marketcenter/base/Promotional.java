package com.marketcenter.base;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 促销活动类
 * 
 * @author yangbo
 *
 */
public class Promotional extends BaseActivity {
	/**
	 * 
	 * id唯一编码
	 */
	private Integer promId;

	/**
	 * 
	 * 促销活动标识
	 */
	private String promCode;

	/**
	 * 
	 * 促销活动名称
	 */
	private String promName;

	/**
	 *
	 *
	 * 优先级(1,2,3 优先级越高，促销等级越高)
	 */
	private Integer priority;

	/**
	 *
	 *
	 * 是否可叠加(1 可叠加 0 不可叠加)
	 */
	private Integer overlays;

	/**
	 *
	 * 使用平台(pc,wap,offline)
	 */
	private String usePlatform;

	/**
	 *
	 *
	 * 促销类型(买赠buyPresent，特价specialOffer，折扣discount，多买overBuy ,
	 * 返利rebate，满减fullCut，满折fullDiscount，阶梯满ladderFull )
	 */
	private String saleType;

	/**
	 *
	 * 优惠条件(满 (N) 件)
	 */
	private Integer fullCondition;

	/**
	 *
	 *
	 * 批量特价
	 */
	private BigDecimal batchSpecialPrice;

	/**
	 *
	 * 批量减价
	 */
	private BigDecimal batchReducePrice;

	/**
	 *
	 * 批量折扣(折扣设置0-10)
	 */
	private BigDecimal batchDiscount;

	/**
	 *
	 * 优惠条件(满 (N) 元)
	 */
	private BigDecimal fullElement;

	/**
	 *
	 * 每满(N元)
	 */
	private BigDecimal eachFullMoney;

	/**
	 *
	 * 限购数量(是否限购)
	 */
	private Integer limits;

	/**
	 *
	 * 限购数量(限购N件)
	 */
	private Integer eachSingle;

	private BigDecimal firstSingleFull;

	/**
	 *
	 * 减现金(N元)
	 */
	private BigDecimal minusMoney;

	/**
	 *
	 * 折扣
	 */
	private BigDecimal discount;

	/**
	 *
	 * 优惠限制(前（N）笔订单，活动时间内每个买家可享受优惠的订单数量，如设置为1时表示首单享受优惠)
	 */
	private Integer discountLimit;

	/**
	 *
	 * 赠品id
	 */
	private Integer giftId;

	/**
	 *
	 * 制单人
	 */
	private String touchingUser;

	/**
	 *
	 * 制单时间
	 */
	private Date touchingTime;

	/**
	 *
	 * 审核人
	 */
	private String auditorUser;

	/**
	 *
	 * 是否审核后立即发布(1 审核后立即发布 2 定时发布)
	 */
	private Integer releases;

	private Integer allin;

	/**
	 *
	 *
	 * 优惠范围 1:商品活动,2:分类活动,3:品牌活动,4:全场活动
	 */
	private Integer prefRange;

	/**
	 *
	 *
	 * 优惠方式(1：不累计；2：每满按比例累计；3：阶梯满)
	 */
	private Integer prefWay;

	/**
	 *
	 * 多买优惠每满(N件)
	 */
	private Integer eachFullNum;

	/**
	 *
	 * 多买优惠(减N件)
	 */
	private Integer minusNum;

	/**
	 *
	 * 多买优惠(打N折)
	 */
	private BigDecimal minusSale;

	/**
	 *
	 * 批量设置类型（1特价 2减价 3批折扣）
	 */
	private Integer batchType;
	/**
	 *
	 * 多买优惠类型（1减价 2 打折）
	 */
	private Integer minusType;
	/**
	 *
	 * 是否累计限额(0：不限额；1：限制)
	 */
	private Integer limitQuota;
	/**
	 *
	 * 最高累计限额N元
	 */
	private BigDecimal limitQuotaPrice;
	/**
	 *
	 * 满(N)元优惠
	 */
	private BigDecimal fullMoney;
	/**
	 *
	 * 优惠券ID(返利)
	 */
	private Integer couponId;
	/**
	 *
	 * 优惠券标识(返利)
	 */
	private String couponCode;
	/**
	 *
	 * 赠品ID(返利)
	 */
	private Integer giftId2;
	/**
	 *
	 * 赠品标识(返利)
	 */
	private String giftCode;
	/**
	 *
	 * 加价(N)元
	 */
	private BigDecimal addMoney;

	private String auditOpinion;

	//是否限时（0：不限时，1：限时）
	private Integer timeLimitStatus;

	//限时的开始时间
	private Date timeLimitStart;

	//限时的结束时间
	private Date timeLimitEnd;

	public Integer getPromId() {
		return promId;
	}

	public void setPromId(Integer promId) {
		this.promId = promId;
	}

	public String getPromCode() {
		return promCode;
	}

	public void setPromCode(String promCode) {
		this.promCode = promCode;
	}

	public String getPromName() {
		return promName;
	}

	public void setPromName(String promName) {
		this.promName = promName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getOverlays() {
		return overlays;
	}

	public void setOverlays(Integer overlays) {
		this.overlays = overlays;
	}

	public String getUsePlatform() {
		return usePlatform;
	}

	public void setUsePlatform(String usePlatform) {
		this.usePlatform = usePlatform;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public Integer getFullCondition() {
		return fullCondition;
	}

	public void setFullCondition(Integer fullCondition) {
		this.fullCondition = fullCondition;
	}

	public BigDecimal getBatchSpecialPrice() {
		return batchSpecialPrice;
	}

	public void setBatchSpecialPrice(BigDecimal batchSpecialPrice) {
		this.batchSpecialPrice = batchSpecialPrice;
	}

	public BigDecimal getBatchReducePrice() {
		return batchReducePrice;
	}

	public void setBatchReducePrice(BigDecimal batchReducePrice) {
		this.batchReducePrice = batchReducePrice;
	}

	public BigDecimal getBatchDiscount() {
		return batchDiscount;
	}

	public void setBatchDiscount(BigDecimal batchDiscount) {
		this.batchDiscount = batchDiscount;
	}

	public BigDecimal getFullElement() {
		return fullElement;
	}

	public void setFullElement(BigDecimal fullElement) {
		this.fullElement = fullElement;
	}

	public BigDecimal getEachFullMoney() {
		return eachFullMoney;
	}

	public void setEachFullMoney(BigDecimal eachFullMoney) {
		this.eachFullMoney = eachFullMoney;
	}

	public Integer getLimits() {
		return limits;
	}

	public void setLimits(Integer limits) {
		this.limits = limits;
	}

	public Integer getEachSingle() {
		return eachSingle;
	}

	public void setEachSingle(Integer eachSingle) {
		this.eachSingle = eachSingle;
	}

	public BigDecimal getFirstSingleFull() {
		return firstSingleFull;
	}

	public void setFirstSingleFull(BigDecimal firstSingleFull) {
		this.firstSingleFull = firstSingleFull;
	}

	public BigDecimal getMinusMoney() {
		return minusMoney;
	}

	public void setMinusMoney(BigDecimal minusMoney) {
		this.minusMoney = minusMoney;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public Integer getDiscountLimit() {
		return discountLimit;
	}

	public void setDiscountLimit(Integer discountLimit) {
		this.discountLimit = discountLimit;
	}

	public Integer getGiftId() {
		return giftId;
	}

	public void setGiftId(Integer giftId) {
		this.giftId = giftId;
	}

	public String getTouchingUser() {
		return touchingUser;
	}

	public void setTouchingUser(String touchingUser) {
		this.touchingUser = touchingUser;
	}

	public Date getTouchingTime() {
		return touchingTime;
	}

	public void setTouchingTime(Date touchingTime) {
		this.touchingTime = touchingTime;
	}

	public String getAuditorUser() {
		return auditorUser;
	}

	public void setAuditorUser(String auditorUser) {
		this.auditorUser = auditorUser;
	}

	public Integer getReleases() {
		return releases;
	}

	public void setReleases(Integer releases) {
		this.releases = releases;
	}

	public Integer getAllin() {
		return allin;
	}

	public void setAllin(Integer allin) {
		this.allin = allin;
	}

	public Integer getPrefRange() {
		return prefRange;
	}

	public void setPrefRange(Integer prefRange) {
		this.prefRange = prefRange;
	}

	public Integer getPrefWay() {
		return prefWay;
	}

	public void setPrefWay(Integer prefWay) {
		this.prefWay = prefWay;
	}

	public Integer getEachFullNum() {
		return eachFullNum;
	}

	public void setEachFullNum(Integer eachFullNum) {
		this.eachFullNum = eachFullNum;
	}

	public Integer getMinusNum() {
		return minusNum;
	}

	public void setMinusNum(Integer minusNum) {
		this.minusNum = minusNum;
	}

	public BigDecimal getMinusSale() {
		return minusSale;
	}

	public void setMinusSale(BigDecimal minusSale) {
		this.minusSale = minusSale;
	}

	public Integer getBatchType() {
		return batchType;
	}

	public void setBatchType(Integer batchType) {
		this.batchType = batchType;
	}

	public Integer getMinusType() {
		return minusType;
	}

	public void setMinusType(Integer minusType) {
		this.minusType = minusType;
	}

	public Integer getLimitQuota() {
		return limitQuota;
	}

	public void setLimitQuota(Integer limitQuota) {
		this.limitQuota = limitQuota;
	}

	public BigDecimal getLimitQuotaPrice() {
		return limitQuotaPrice;
	}

	public void setLimitQuotaPrice(BigDecimal limitQuotaPrice) {
		this.limitQuotaPrice = limitQuotaPrice;
	}

	public BigDecimal getFullMoney() {
		return fullMoney;
	}

	public void setFullMoney(BigDecimal fullMoney) {
		this.fullMoney = fullMoney;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getGiftId2() {
		return giftId2;
	}

	public void setGiftId2(Integer giftId2) {
		this.giftId2 = giftId2;
	}

	public String getGiftCode() {
		return giftCode;
	}

	public void setGiftCode(String giftCode) {
		this.giftCode = giftCode;
	}

	public BigDecimal getAddMoney() {
		return addMoney;
	}

	public void setAddMoney(BigDecimal addMoney) {
		this.addMoney = addMoney;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public Integer getTimeLimitStatus() {
		return timeLimitStatus;
	}

	public void setTimeLimitStatus(Integer timeLimitStatus) {
		this.timeLimitStatus = timeLimitStatus;
	}

	public Date getTimeLimitStart() {
		return timeLimitStart;
	}

	public void setTimeLimitStart(Date timeLimitStart) {
		this.timeLimitStart = timeLimitStart;
	}

	public Date getTimeLimitEnd() {
		return timeLimitEnd;
	}

	public void setTimeLimitEnd(Date timeLimitEnd) {
		this.timeLimitEnd = timeLimitEnd;
	}
}