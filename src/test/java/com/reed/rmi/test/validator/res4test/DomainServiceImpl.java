package com.reed.rmi.test.validator.res4test;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.reed.rmi.validator.groups.ValidatorGroup4Add;
import com.reed.rmi.validator.groups.ValidatorGroup4Update;

public class DomainServiceImpl implements DomainService {

	@Override
	public int save(User4ValidTest bean) {
		System.out.println("save validate pass");
		return 1;
	}

	@Override
	public int saveCascade(User4ValidTest bean1, Udept4ValidTest bean2) {
		System.out.println("saveCascade validate pass");
		return 1;
	}

	@Override
	public int update(User4ValidTest bean) {
		System.out.println("update validate pass");
		return 1;
	}

	@Override
	public int updateCascade(User4ValidTest bean1, Udept4ValidTest bean2) {
		System.out.println("updateCascade validate pass");
		return 1;
	}

	/**
	 * not support validating basic-params
	 */
	@Override
	public int update(@Range(min=1,groups=ValidatorGroup4Update.class)int id, 
			@NotBlank(groups=ValidatorGroup4Update.class)String name, 
			@Email(groups=ValidatorGroup4Update.class) String email) {
		return 1;
	}

	@Override
	public int saveCascade(User4ValidTest bean1, User4ValidTest bean2) {
		return 1;
	}

	/**
	 * valid user&ddept ,but not serialNo\desc\strBuf
	 */
	@Override
	public int saveMulti(@Range(min=1,groups=ValidatorGroup4Add.class)int serialNo, 
			User4ValidTest user, @NotBlank(groups=ValidatorGroup4Add.class)String desc,
			Udept4ValidTest ddept,@NotBlank(groups=ValidatorGroup4Add.class) StringBuffer strBuf) {
		return 1;
	}

	@Override
	public int execute4defaultGroup(User4ValidTest bean1, Udept4ValidTest bean2) {
		return 1;
	}

}
