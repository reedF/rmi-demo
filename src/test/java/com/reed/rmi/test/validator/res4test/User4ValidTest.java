package com.reed.rmi.test.validator.res4test;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;

import com.reed.rmi.validator.groups.ValidatorGroup4Add;
import com.reed.rmi.validator.groups.ValidatorGroup4Update;

@Service
public class User4ValidTest {
	@Range(min=1,max=0x7fffffff,groups=ValidatorGroup4Add.class)
	private int id ;
	// groups default [Default.class] when u not set
	@NotBlank
	@Length(max=10,groups=ValidatorGroup4Add.class)
	private String name ;
	//it'll not support in the other groups but your set-groups
	@NotNull(groups=ValidatorGroup4Add.class)
	private Boolean ismem ;
	@DateTimeFormat(iso = ISO.DATE)
	private Date birth ;
	//u can set multi-groups
	@NotNull(groups={ValidatorGroup4Add.class,ValidatorGroup4Update.class})
	@Pattern(regexp = "(^1[0-9]{10}$)", groups={ValidatorGroup4Add.class,ValidatorGroup4Update.class})
	private String mobilePhoneNo ;
	// u can set manu-message 
	@Email(message="invalid-email-addr",groups=ValidatorGroup4Add.class)
    private String email;
	
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
	public Boolean getIsmem() {
		return ismem;
	}
	public void setIsmem(Boolean ismem) {
		this.ismem = ismem;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobilePhoneNo() {
		return mobilePhoneNo;
	}
	public void setMobilePhoneNo(String mobilePhoneNo) {
		this.mobilePhoneNo = mobilePhoneNo;
	}
	
}
