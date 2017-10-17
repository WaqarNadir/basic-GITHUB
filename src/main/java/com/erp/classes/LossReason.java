package com.erp.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the LossReason database table.
 * 
 */
@Entity
@NamedQuery(name = "LossReason.findAll", query = "SELECT l FROM LossReason l")
public class LossReason implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to DailyProduction
//	@ManyToOne
//	@JoinColumn(name = "DP_ID")
//	private DailyProduction dailyProduction;

	@Id
	@Column(name = "Loss_ID")
	private int loss_ID;

	@Column(name = "Reason")
	private String reason;

	@Column(name = "Remarks")
	private String remarks;

	public LossReason() {
	}

//	public DailyProduction getDailyProduction() {
//		return this.dailyProduction;
//	}

	public int getLoss_ID() {
		return this.loss_ID;
	}

	public String getReason() {
		return this.reason;
	}

	public String getRemarks() {
		return this.remarks;
	}

//	public void setDailyProduction(DailyProduction dailyProduction) {
//		this.dailyProduction = dailyProduction;
//	}

	public void setLoss_ID(int loss_ID) {
		this.loss_ID = loss_ID;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}