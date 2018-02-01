package com.atguigu.mall.service;

import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;

public interface UserService {

	T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT login3(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT regist(T_MALL_USER_ACCOUNT user);

	int updateUserById(T_MALL_USER_ACCOUNT user);

}
