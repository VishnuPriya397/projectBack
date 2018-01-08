package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Supplier implements Serializable {

	public Supplier(){
		
	}
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int sid;
	private String suppliername;
	private String supplieraddress;
	@OneToMany(targetEntity=Product.class, fetch = FetchType.EAGER, mappedBy = "supplier", cascade = CascadeType.ALL)
	 private Set<Product> products = new HashSet<Product>(0);
	
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	public String getSupplieraddress() {
		return supplieraddress;
	}
	public void setSupplieraddress(String supplieraddress) {
		this.supplieraddress = supplieraddress;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
