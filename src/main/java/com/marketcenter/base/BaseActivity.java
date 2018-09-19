package com.marketcenter.base;

import java.util.Date;

public class BaseActivity {

	/**
	 * 开始生效时间
	 */
	private Date startTime;

	/**
	 * 截止时间
	 */
	private Date endTime;

	/**
	 * 审核状态(1：待提交；2：待审核；3：已驳回；4：已生效未发布；5:已发布未开始；6:活动中；7:已结束)
	 */
	private Integer auditorStatus;

	private String remark;

	private Date releaseTime;

	private String vipLevel;
	/**
	 *
	 * 审核时间
	 */
	private Date auditorTime;

	private String enterpriseCode;

	private Integer enterpriseId;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getAuditorStatus() {
		return auditorStatus;
	}

	public void setAuditorStatus(Integer auditorStatus) {
		this.auditorStatus = auditorStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public Date getAuditorTime() {
		return auditorTime;
	}

	public void setAuditorTime(Date auditorTime) {
		this.auditorTime = auditorTime;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

}
