package com.erp.classes;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.springframework.stereotype.Component;

/**
 * The persistent class for the Product database table.
 * 
 */
@Component
@Entity
@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	private int parentRef;

	
	private double price;

	
	private Integer prod_ID;

	
	private String prodType;

	
	private String remarks;

	
	private int supplier_ID;

	
	private String UOM;
	
	
	public Integer getIsFinal() {
		return isFinal;
	}

	public void setIsFinal(Integer isFinal) {
		this.isFinal = isFinal;
	}

	private Integer isFinal;

	public Product() {
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}
	
	@Column(name = "ParentRef")
	public int getParentRef() {
		return this.parentRef;
	}
	@Column(name = "Price")
	public double getPrice() {
		return this.price;
	}

	@Id
	@TableGenerator(
	        name="ProdID", 
	        		 table="ID_GEN", 
	     	        pkColumnName="GEN_KEY", 
	     	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="ProdID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="ProdID")
	@Column(name = "Prod_ID")
	public Integer getProd_ID() {
		return this.prod_ID;
	}

	@Column(name = "ProdType")
	public String getProdType() {
		return this.prodType;
	}
	@Column(name = "Remarks")
	public String getRemarks() {
		return this.remarks;
	}
	@Column(name = "Supplier_ID")
	public int getSupplier_ID() {
		return this.supplier_ID;
	}
	@Column(name = "UOM")
	public String getUOM() {
		return this.UOM;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentRef(int parentRef) {
		this.parentRef = parentRef;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setProd_ID(Integer prod_ID) {
		this.prod_ID = prod_ID;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setSupplier_ID(int supplier_ID) {
		this.supplier_ID = supplier_ID;
	}

	public void setUOM(String UOM) {
		this.UOM = UOM;
	}
	
	
	@Override
	public String toString() {
		
		return "Name = "+ this.getName() +"\n ParentRef="+this.getParentRef() +"\n Prod ID="+this.getProd_ID() +"\n ProdType= "+getProdType() +"\n Remarks= "+getRemarks()+"\n Supplier_ID="+getSupplier_ID()+"\n UOM = "+getUOM()+"\nPrice ="+getPrice();
		
	}

	public Product(String name, Integer parentRef) {
		super();
		this.name = name;
		this.parentRef = parentRef;
		
	}

}