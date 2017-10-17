package com.erp.classes;

import java.util.List;

public class OrderDetailWrapperClass {
	
	List<OrderDetail> orderDetail;
	List<String> product;
	String Date;
	
	String partyName;
	
	
	public List<OrderDetail> getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(List<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}
	public List<String> getProduct() {
		return product;
	}
	public void setProduct(List<String> prodType) {
		this.product = prodType;
	}

	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
}
