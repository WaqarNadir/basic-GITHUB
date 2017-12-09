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

@Entity(name="Productdetail")
public class ProductDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable=true , name = "Color")
	private String Color;

	@Column(nullable=true , name = "Designno")
	private String DesignNo;

	@Column(nullable=true , name = "Imgpath")
	private String ImgPath;

	@Column(nullable=true , name = "IsScrap")
	private Integer IsScrap;


	@ManyToOne
	@JoinColumn(name="Prod_ID" ,nullable=true)
	private Product product;
	
		
	@Id
	@TableGenerator(
	        name="ProdDetailID", 
	        table="ID_GEN", 
	        pkColumnName="GEN_KEY", 
	        valueColumnName="GEN_VALUE", 
	        pkColumnValue="ProdDetailID", 
	        allocationSize=1)
	    @GeneratedValue(strategy=GenerationType.TABLE, generator="ProdDetailID")
	@Column(name = "Proddetail_ID")
	private Integer ProdDetail_ID;

	@Column(nullable=true , name = "Remarks")
	private String Remarks;
	
	public ProductDetail() {
		
	}
	

	public ProductDetail(String designNo, String color,
			Product product ) {
		super();
		DesignNo = designNo;
		Color = color;
		this.product = product ;
		
	}

	public String getColor() {
		return Color;
	}

	public String getDesignNo() {
		return DesignNo;
	}

	public String getImgPath() {
		return ImgPath;
	}

	public Integer getIsScrap() {
		return IsScrap;
	}


	public Product getProduct() {
		return product;
	}

	public Integer getProdDetail_ID() {
		return ProdDetail_ID;
	}

	public String getRemarks() {
		return Remarks;
	}

	public void setColor(String color) {
		Color = color;
	}

	public void setDesignNo(String designNo) {
		DesignNo = designNo;
	}

	public void setImgPath(String imgPath) {
		ImgPath = imgPath;
	}

	public void setIsScrap(Integer isScrap) {
		IsScrap = isScrap;
	}

	public void setProduct(Product prod_ID) {
		product = prod_ID;
	}

	public void setProdDetail_ID(Integer prodDetail_ID) {
		ProdDetail_ID = prodDetail_ID;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}


}
