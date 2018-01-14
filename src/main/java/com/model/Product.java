package com.model;

import java.io.Serializable;


import javax.persistence.*;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Entity


public class Product implements Serializable {

	@Id
	@GeneratedValue
	private int pid;
	@Transient
	MultipartFile pimage;
	private String imagName;
	private String pdescription;
	private String pname;
	private double price;
	private int pstock;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cid")
	private Category category;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="sid")
	private Supplier supplier;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public MultipartFile getPimage() {
		return pimage;
	}
	public void setPimage(MultipartFile pimage) {
		this.pimage = pimage;
	}
	public String getImagName() {
		return imagName;
	}
	public void setImagName(String imagName) {
		this.imagName = imagName;
	}
	public String getPdescription() {
		return pdescription;
	}
	public void setPdescription(String pdescription) {
		this.pdescription = pdescription;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPstock() {
		return pstock;
	}
	public void setPstock(int pstock) {
		this.pstock = pstock;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
}