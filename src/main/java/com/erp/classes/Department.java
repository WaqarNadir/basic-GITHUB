package com.erp.classes;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

/**
 * The persistent class for the Department database table.
 * 
 */
@Entity
@NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d")
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id

	@TableGenerator(
	        name="Dept_ID", 
	        table="ID_GEN", 
	        pkColumnName="GEN_KEY", 
	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="Dept_ID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="Dept_ID")
	@Column(name = "Dept_ID")
	private Integer dept_ID;

	@Column(name = "Name")
	private String name;
	@Column(name = "EstimatedCompletionTime",nullable=true)
	private double estimatedCompletionTime;

	public double getEstimatedCompletionTime() {
		return estimatedCompletionTime;
	}

	public void setEstimatedCompletionTime(double estimatedCompletionTime) {
		this.estimatedCompletionTime = estimatedCompletionTime;
	}

	@Column(name = "Remarks")
	private String remarks;

	// bi-directional many-to-one association to WorkType
	@OneToMany(mappedBy = "department")
	private List<WorkType> workTypes;

	public Department() {
	}

	public WorkType addWorkType(WorkType workType) {
		getWorkTypes().add(workType);
		workType.setDepartment(this);

		return workType;
	}

	public Integer getDept_ID() {
		return this.dept_ID;
	}

	public String getName() {
		return this.name;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public List<WorkType> getWorkTypes() {
		return this.workTypes;
	}

	public WorkType removeWorkType(WorkType workType) {
		getWorkTypes().remove(workType);
		workType.setDepartment(null);

		return workType;
	}

	public void setDept_ID(Integer dept_ID) {
		this.dept_ID = dept_ID;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setWorkTypes(List<WorkType> workTypes) {
		this.workTypes = workTypes;
	}

}