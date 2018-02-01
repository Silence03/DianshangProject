package com.atguigu.mall.sale.util;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.FactoryBean;


public class MyWsUtil<T> implements FactoryBean<T>{
	private String url;
	private Class<T> t;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	public static <T>T getMyWs(String url,Class<T> t) {
		JaxWsProxyFactoryBean jws = new JaxWsProxyFactoryBean();
		jws.setAddress(url);
		jws.setServiceClass(t);
		T create = (T) jws.create();
		return create;
	}

	@Override
	public T getObject() throws Exception {
		// TODO Auto-generated method stub
		return getMyWs(this.url,this.t);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
}
