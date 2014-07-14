package com.reed.rmi.test.validator.res4test;

import javax.validation.groups.Default;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import com.reed.rmi.validator.groups.ValidatorGroup4Add;


public class Udept4ValidTest {
	@Range(min=1,max=Integer.MAX_VALUE, 
			groups = {ValidatorGroup4Add.class,Default.class})
	private int id ;
	private int leaderId ;
	@Length(min=1,max=10,
			groups = {ValidatorGroup4Add.class,Default.class})
	private String name ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}
	
}
