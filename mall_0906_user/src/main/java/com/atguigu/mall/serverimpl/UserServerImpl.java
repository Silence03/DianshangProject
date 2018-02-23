package com.atguigu.mall.serverimpl;


import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.server.UserServer;
import com.atguigu.mall.service.UserService;
import com.atguigu.mall.util.MyRoutingDataSource;
import com.google.gson.Gson;


public class UserServerImpl implements UserServer {
	
	@Autowired
	UserService userService;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		//使用数据源1
		MyRoutingDataSource.setKey("1");
		return userService.login(user);
	}

	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// 使用数据源2
		MyRoutingDataSource.setKey("2");
		return userService.login2(user);
	}

	@Override
	@Path("/login3")
	@GET
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String login3(@BeanParam T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT loginuser = userService.login3(user);
		return new Gson().toJson(loginuser);
	}

	@Override
	@Path("/regist")
	@GET
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String regist(T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT registuser = userService.regist(user);
		return new Gson().toJson(registuser);
	}

	@Override
	@Path("/update")
	@GET
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	public String updateUserById(T_MALL_USER_ACCOUNT user) {
		int i = userService.updateUserById(user);
		return new Gson().toJson(i);
	}

}
