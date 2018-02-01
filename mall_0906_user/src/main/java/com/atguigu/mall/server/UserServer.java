package com.atguigu.mall.server;

import javax.jws.WebService;

import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface UserServer {
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user);
	public String login3(T_MALL_USER_ACCOUNT user);
	public String regist(T_MALL_USER_ACCOUNT user);
	public String updateUserById(T_MALL_USER_ACCOUNT user);
}
