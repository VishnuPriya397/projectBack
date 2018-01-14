package com.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import org.springframework.stereotype.Component;

@Component
@Entity


public class Supplier implements Serializable
{
	
	@Id
	private int sid;
	private String sname;
	
	@OneToMany(targetEntity=Product.class,fetch=FetchType.EAGER, mappedBy="supplier")
	private Set<Product> product=new HashSet<Product>(0);
    
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}
	
	
}
