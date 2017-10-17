package com.erp.classes;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the WorkType database table.
 * 
 */
@Entity
@NamedQuery(name = "WorkType.findAll", query = "SELECT w FROM WorkType w")
public class WorkType implements Serializable {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to Department
	@ManyToOne
	@JoinColumn(name = "Dept_ID")
	private Department department;

	@Column(name = "EndTime")
	private Timestamp endTime;

	@Column(name = "Name")
	private String name;

	@Column(name = "Remarks")
	private String remarks;

	@Column(name = "StartTime")
	private Timestamp startTime;

	@Id
	@Column(name = "WT_ID")
	private Integer wtId;

	public WorkType() {
	}

	public Department getDepartment() {
		return this.department;
	}

	public Timestamp getEndTime() {
		return this.endTime;
	}

	public String getName() {
		return this.name;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public Timestamp getStartTime() {
		return this.startTime;
	}

	public Integer getWtId() {
		return this.wtId;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public void setWtId(Integer wtId) {
		this.wtId = wtId;
	}

}