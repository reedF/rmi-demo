package com.reed.rmi.test.validator;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reed.rmi.test.validator.res4test.DomainService;
import com.reed.rmi.test.validator.res4test.Udept4ValidTest;
import com.reed.rmi.test.validator.res4test.User4ValidTest;

@RunWith(JUnit4.class)
public class ValidatorTest extends TestCase {

	private static DomainService domainService;

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	static {
		@SuppressWarnings("resource")
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"classpath:validator.xml");
		domainService = appContext
				.getBean("domainService", DomainService.class);
	}

	// single-bean valid
	@Test(expected = Exception.class)
	public void testSave() {
		// expectedEx.expect(Exception.class);
		// expectedEx.expectMessage("");
		User4ValidTest bean = new User4ValidTest();
		bean.setId(-1);// out of range
		bean.setName("111111111111111");// too long
		bean.setIsmem(null);// not null constrains
		bean.setMobilePhoneNo("3333333333");// invalid-pattern
		bean.setEmail("123333");// invalid-pattern
		int res = domainService.save(bean);
		Assert.assertEquals("pass", res, 1);
	}

	@Test
	@Ignore("not support basic-param")
	public void testUpdateParams() {
		int id = -1;
		String name = "aaa";
		String email = "1111";
		int res = domainService.update(id, name, email);
		Assert.assertEquals("pass", res, 1);
	}

	// multi instances of the same class
	@Test
	public void testSaveCascade2() {
		expectedEx.expect(Exception.class);
		User4ValidTest user = new User4ValidTest();
		user.setId(1);
		user.setName("111111111");
		user.setIsmem(true);
		user.setMobilePhoneNo("13333333333");
		user.setEmail("123333@163.com");
		Udept4ValidTest dept = new Udept4ValidTest();
		dept.setId(1);
		dept.setName("00000000000000000000000");// too long
		int res = domainService.saveCascade(user, dept);
		Assert.assertEquals("pass", res, 1);
	}

	// multi instances of the different class
	@Test
	public void testSaveCascade() {
		User4ValidTest user = new User4ValidTest();
		user.setId(1);
		user.setName("111111111");
		user.setIsmem(true);
		user.setMobilePhoneNo("13333333333");
		user.setEmail("123333@163.com");
		User4ValidTest user2 = new User4ValidTest();
		user2.setId(1);
		user2.setName("111111111");
		user2.setIsmem(true);
		user2.setMobilePhoneNo("13333333333");
		user2.setEmail("123333@163.com");
		int res = domainService.saveCascade(user, user2);
		Assert.assertEquals("pass", res, 1);
	}

	// multi types
	@Test
	public void testMulti() {
		User4ValidTest user = new User4ValidTest();
		user.setId(1);
		// user.setName("111111111");
		user.setIsmem(true);
		user.setMobilePhoneNo("13333333333");
		user.setEmail("123333@163.com");
		Udept4ValidTest dept = new Udept4ValidTest();
		dept.setId(1);
		dept.setName("000000000");
		domainService.saveMulti(0, user, null, dept, null);
	}

	// default-group when u not set
	@Test
	public void testDefaultGroup() {
		User4ValidTest user = new User4ValidTest();
		user.setId(1);
		user.setName("111111111");
		user.setMobilePhoneNo("13333333333");
		user.setEmail("123333@163.com");
		Udept4ValidTest dept = new Udept4ValidTest();
		dept.setId(1);
		dept.setName("0000000000");
		int res = domainService.execute4defaultGroup(user, dept);
		Assert.assertEquals("pass", res, 1);
	}

}
