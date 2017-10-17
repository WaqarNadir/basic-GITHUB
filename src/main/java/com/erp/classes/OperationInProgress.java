package com.erp.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class OperationInProgress implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "DeptOD_ID")
	private DeptOperationDetails DeptOD_ID;
	
	@ManyToOne
	@JoinColumn(name = "OrderDetail_ID")
	private OrderDetail orderDetail;


	@Column(name = "remarks")
	private String remarks;

	@Column(name = "InitialCloth")
	private double InitialCloth;
	
	@Column(name = "Status")
	private String status;

	@Id
	@TableGenerator(
	        name="OIP_ID", 
	        table="ID_GEN", 
	        pkColumnName="GEN_KEY", 
	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="OIP_ID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="OIP_ID")
	@Column(name = "OIP_ID")
	private int OIP_ID;



	public int getOIP_ID() {
		return OIP_ID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TotalUsedCloth")
	private double TotalUsedCloth;

	public OperationInProgress() {
		
	}

	public DeptOperationDetails getDeptOD_ID() {
		return DeptOD_ID;
	}

	public double getInitialCloth() {
		return InitialCloth;
	}

	public int getOID_ID() {
		return OIP_ID;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public String getRemarks() {
		return remarks;
	}

	public double getTotalUsedCloth() {
		return TotalUsedCloth;
	}

	public void setDeptOD_ID(DeptOperationDetails deptOD_ID) {
		DeptOD_ID = deptOD_ID;
	}

	public void setInitialCloth(double initialCloth) {
		InitialCloth = initialCloth;
	}

	public void setOID_ID(int oID_ID) {
		OIP_ID = oID_ID;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setTotalUsedCloth(double totalUsedCloth) {
		TotalUsedCloth = totalUsedCloth;
	}

}
