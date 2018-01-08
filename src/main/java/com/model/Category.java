package com.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Category {

	@Id
	@GeneratedValue
	private int cid;
	private String cname;
	@OneToMany(targetEntity=Product.class, fetch = FetchType.EAGER, mappedBy = "category", cascade =CascadeType.ALL)
	private Set<Product> products = new HashSet<Product>(0);
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}

}
