package com.atguigu.mall.ws;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.server.UserServer;

public class TestUserWs {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean jws = new JaxWsProxyFactoryBean();
		jws.setAddress("http://localhost:8282/mall_0906_user/login?wsdl");
		jws.setServiceClass(UserServer.class);
		
		UserServer create = (UserServer) jws.create();
		T_MALL_USER_ACCOUNT user = new T_MALL_USER_ACCOUNT();
		user.setYh_mch("admin");
		user.setYh_mm("123");
		System.out.println(create.login(user));
	}
}
