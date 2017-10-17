package com.erp.classes;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ColumnDefault;

/**
 * The persistent class for the Order database table.
 * 
 */
@Entity
@Table(name = "\"Order\"")
// @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")

@NamedQuery(name = "Order.LastPKValue", query = "select Max(o.order_ID) from Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "Customer_ID", nullable = true)
	private Person person;

	@Column(name = "Date")
	private Date date;

	@Column(name = "DelieveryDate")
	private Date delieveryDate;

	@Column(name = "EstimatedDate")
	private Date estimatedDate;

	@Column(name = "LotNo")
	private String lotNo;

	@Id
	@TableGenerator(name = "orderID", table = "ID_GEN", pkColumnName = "GEN_KEY", valueColumnName = "GEN_VALUE", pkColumnValue = "orderID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "orderID")
	@Column(name = "Order_ID")
	private Integer order_ID;

	@Column(name = "OrderStatus")
	@ColumnDefault("'Open'")
	private String orderStatus;

	@Column(name = "Remarks")
	private String remarks;

	public Order() {
	}

	public Person getPerson() {
		return this.person;
	}

	public Date getDate() {
		return this.date;
	}

	public Date getDelieveryDate() {
		return this.delieveryDate;
	}

	public Date getEstimatedDate() {
		return this.estimatedDate;
	}

	public String getLotNo() {
		return this.lotNo;
	}

	public Integer getOrder_ID() {
		return this.order_ID;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDelieveryDate(Date delieveryDate) {
		this.delieveryDate = delieveryDate;
	}

	public void setEstimatedDate(Date estimatedDate) {
		this.estimatedDate = estimatedDate;
	}

	public void setLotNo(String lotNo) {
		this.lotNo = lotNo;
	}

	public void setOrder_ID(Integer Order_ID) {
		this.order_ID = order_ID;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}