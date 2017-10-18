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
public class DeptOperationDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "BaseProduction")
	private double baseProduction;

	@ManyToOne(cascade = CascadeType.MERGE )
	@JoinColumn(name = "Dept_ID",nullable=true)
	private Department dept;
	
	@Id
	@TableGenerator(
	        name="deptOD_ID", 
	        table="ID_GEN", 
	        pkColumnName="GEN_KEY", 
	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="deptOD_ID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="deptOD_ID")
	@Column(name = "DeptOD_ID")
	private int deptOD_ID;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "OperationStatus")
	private String operationStatus;

	@Column(name = "SequenceNo")
	private int sequenceNo;
	@Column(name = "Color")
	private int color;
		

		return color;
	}
	public void setColor(int color) {
		this.color = color;

	public DeptOperationDetails() {
		// TODO Auto-generated constructor stub
	}

	public double getBaseProduction() {
		return baseProduction;
	}

	public Department getDept() {
		return dept;
	}

	public int getDeptOD_ID() {
		return deptOD_ID;
	}

	public String getName() {
		return name;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public int getSequenceNo() {
		return sequenceNo;
	}

	public void setBaseProduction(double basProduction) {
		baseProduction = basProduction;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setDeptOD_ID(int deptOD_ID) {
		this.deptOD_ID = deptOD_ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public void setSequenceNo(int sequenceNo) {
		this.sequenceNo = sequenceNo;
	}

}
