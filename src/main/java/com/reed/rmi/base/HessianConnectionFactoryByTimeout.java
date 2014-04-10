package com.reed.rmi.base;

import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * 继承HessianProxyFactoryBean,扩展对connection timeout的配置
 * 
 * @author reed
 * 
 */
public class HessianConnectionFactoryByTimeout extends HessianProxyFactoryBean {

	private HessianProxyFactory proxyFactory = new HessianProxyFactory();

	public void setConnectTimeout(long timeout) {
		this.proxyFactory.setConnectTimeout(timeout);
		super.setProxyFactory(proxyFactory);
	}
}
