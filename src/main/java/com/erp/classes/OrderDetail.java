package com.erp.classes;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;


	@Column(name = "Construction",nullable=true)
	private String Construction;

	@Id
	@TableGenerator(
	        name="ordDetailID", 
	        table="ID_GEN", 
	        pkColumnName="GEN_KEY", 
	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="ordDetailID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="ordDetailID")
	@Column(name = "OrdDetail_ID")
	private Integer ordDetail_ID;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "Order_ID",nullable=true)
	private Order order;

	@Column(name = "Price",nullable=true)
	private double Price;

	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name = "ProdDetail_ID",nullable=true)
	private ProductDetail prodDetail;

	@Column(name = "Quantity",nullable=true)
	private double Quantity;

	@Column(name = "Remarks",nullable=true)
	private String Remarks;

	
	//-------------------------- setter ------------------------------------

	public String getConstruction() {
		return Construction;
	}

	public Integer getOrdDetail_ID() {
		return ordDetail_ID;
	}

	public Order getOrder() {
		return order;
	}

	public double getPrice() {
		return Price;
	}

	public ProductDetail getProdDetail() {
		return prodDetail;
	}

	public double getQuantity() {
		return Quantity;
	}

	public String getRemarks() {
		return Remarks;
	}
public OrderDetail(boolean createFullObject) {
	if(createFullObject) {
	setProdDetail(new ProductDetail());
//	getprodDetail().setProduct(new Product());
	}	// just to pass it in javascript for ajax call 
}

public OrderDetail() {
	// TODO Auto-generated constructor stub
}
	//-------------------------- setter ------------------------------------
	public void setConstruction(String construction) {
		Construction = construction;
	}

	public void setOrdDetail_ID(Integer ordDetail_ID) {
		this.ordDetail_ID = ordDetail_ID;
	}

	public void setOrder(Order order_ID) {
		order = order_ID;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public void setProdDetail(ProductDetail prodDetail_ID) {
		prodDetail = prodDetail_ID;
	}

	public void setQuantity(double quantity) {
		Quantity = quantity;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
}
