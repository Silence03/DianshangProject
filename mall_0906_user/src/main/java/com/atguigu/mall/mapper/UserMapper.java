package com.atguigu.mall.mapper;

import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;

public interface UserMapper {

	T_MALL_USER_ACCOUNT selectUserByYhmcAndPwd(T_MALL_USER_ACCOUNT user);

	T_MALL_USER_ACCOUNT insert(T_MALL_USER_ACCOUNT user);

	int updateUserById(T_MALL_USER_ACCOUNT user);

}
