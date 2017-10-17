package com.erp.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * The persistent class for the BillOfMaterial database table.
 * 
 */
@Entity
@NamedQuery(name = "BillOfMaterial.findAll", query = "SELECT b FROM BillOfMaterial b")
public class BillOfMaterial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "Inventory_ID")
	private int inventory_ID;

	@Column(name = "Prod_ID")
	private int prod_ID;

	@Column(name = "ProdDetail_ID")
	private int prodDetail_ID;

	@Column(name = "Remarks")
	private String remarks;

	public BillOfMaterial() {
	}

	public int getInventory_ID() {
		return this.inventory_ID;
	}

	public int getProd_ID() {
		return this.prod_ID;
	}

	public int getProdDetail_ID() {
		return this.prodDetail_ID;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setInventory_ID(int inventory_ID) {
		this.inventory_ID = inventory_ID;
	}

	public void setProd_ID(int prod_ID) {
		this.prod_ID = prod_ID;
	}

	public void setProdDetail_ID(int prodDetail_ID) {
		this.prodDetail_ID = prodDetail_ID;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}