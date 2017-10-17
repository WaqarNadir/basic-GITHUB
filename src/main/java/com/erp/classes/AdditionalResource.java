package com.erp.classes;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "AdditionalResource.findAll", query = "SELECT d FROM AdditionalResource d")
public class AdditionalResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "AdditionalCloth")
	private Double AdditionalCloth;

	@Id
	@Column(name = "AR_ID")
	private int AR_ID;

	@Column(name = "Date")
	private Date Date;

	@ManyToOne
	@JoinColumn(name = "DP_ID")
	private DailyProduction DP_ID;

	@Column(name = "Reason")
	private String Reason;

	

	public Double getAdditionalCloth() {
		return AdditionalCloth;
	}

	public int getAR_ID() {
		return AR_ID;
	}

	public Date getDate() {
		return Date;
	}

	public DailyProduction getDP_ID() {
		return DP_ID;
	}

	public String getReason() {
		return Reason;
	}

	public void setAdditionalCloth(Double additionalCloth) {
		AdditionalCloth = additionalCloth;
	}

	public void setAR_ID(int aR_ID) {
		AR_ID = aR_ID;
	}

	public void setDate(Date date) {
		Date = date;
	}

	public void setDP_ID(DailyProduction dP_ID) {
		DP_ID = dP_ID;
	}

	public void setReason(String reason) {
		Reason = reason;
	}
}
