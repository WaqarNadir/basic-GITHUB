package com.erp.classes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name = "DailyProduction.LatestClosingStock", 
query = "SELECT DP FROM DailyProduction DP where DP.oIP = :oip order by DP.date DESC ")
public class DailyProduction implements Serializable {
	public OperationInProgress getOIP() {
		return oIP;
	}

	public void setOIP(OperationInProgress oIP) {
		this.oIP = oIP;
	}

	private static final long serialVersionUID = 1L;

	
	
	
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name ="OIP_ID")
	private OperationInProgress oIP;
	
	@Id
	@TableGenerator(
	        name="DP_ID", 
	        table="ID_GEN", 
	        pkColumnName="GEN_KEY", 
	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="DP_ID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="DP_ID")

	private Integer DP_ID;
	
	@Column
	private double DailyProduction;
	@Column
	private double stockInHand;
	@Column
	private Date date;
	@Column
	private String WorkStatus;
	
	private Double openingStock;
	
	private Double closingStock;


	public Double getOpeningStock() {
		return openingStock;
	}

	public void setOpeningStock(Double openingStock) {
		this.openingStock = openingStock;
	}

	public Double getClosingStock() {
		return closingStock;
	}

	public void setClosingStock(Double closingStock) {
		this.closingStock = closingStock;
	}

	public double getDailyProduction() {
		return DailyProduction;
	}

	public Date getDate() {
		return date;
	}

	public String getWorkStatus() {
		return WorkStatus;
	}

	public void setDailyProduction(double dailyProduction) {
		DailyProduction = dailyProduction;
	}

	public void setDate(Date orderDate) {
		this.date = orderDate;
	}

	public void setWorkStatus(String workStatus) {
		WorkStatus = workStatus;
	}

	public double getStockInHand() {
		return stockInHand;
	}

	public void setStockInHand(double stockInHand) {
		this.stockInHand = stockInHand;
	}

	

	public Integer getDP_ID() {
		return DP_ID;
	}

	public void setDP_ID(Integer dP_ID) {
		DP_ID = dP_ID;
	}

}
