package com.erp.classes;

import java.util.List;

public class OrderDetailWrapperClass {

	List<OrderDetail> orderDetailList;
	List<String> product;
	List<String> machineDetail;
	Order order;
	String Date;

	String partyName;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<String> getMachineDetail() {
		return machineDetail;
	}

	public void setMachineDetail(List<String> machineDetail) {
		this.machineDetail = machineDetail;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetail) {
		this.orderDetailList = orderDetail;
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
