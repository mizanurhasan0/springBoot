package com.ecommerce.org.dto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "products")
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String code;
	
	private String name;
	private String brand;
	@Column(name = "description")
	private String description;
	private int unitprice;
	private int quantity;
	private int active;
	@Column(name = "categoryid")
	private int categoryid;
	private int supplierid;
	private int purchases;
	private int views;
	
	
	public Products(String code, String name, String brand, String description, int unitprice, int quantity, int active,
			int categoryid, int supplierid, int purchases, int views) {
		super();
		this.code = code;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.unitprice = unitprice;
		this.quantity = quantity;
		this.active = active;
		this.categoryid = categoryid;
		this.supplierid = supplierid;
		this.purchases = purchases;
		this.views = views;
	}


	public Products() {
		this.code="PRD"+UUID.randomUUID().toString().substring(26).toUpperCase();
		this.views=0;
		this.active=0;
		this.supplierid=1;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getUnitprice() {
		return unitprice;
	}


	public void setUnitprice(int unitprice) {
		this.unitprice = unitprice;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public int getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}


	public int getSupplierid() {
		return supplierid;
	}


	public void setSupplierid(int supplierid) {
		this.supplierid = supplierid;
	}


	public int getPurchases() {
		return purchases;
	}


	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}


	public int getViews() {
		return views;
	}


	public void setViews(int views) {
		this.views = views;
	}


	@Override
	public String toString() {
		return "Products [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + ", description="
				+ description + ", unitprice=" + unitprice + ", quantity=" + quantity + ", active=" + active
				+ ", categoryid=" + categoryid + ", supplierid=" + supplierid + ", purchases=" + purchases + ", views="
				+ views + "]";
	}



	
	
}
