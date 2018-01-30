package com.atguigu.mall.sale.service;

import com.atguigu.mall.sale.bean.T_MALL_USER;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;

public interface LoginService {

	T_MALL_USER_ACCOUNT getUserByMcAndMm(T_MALL_USER_ACCOUNT user);

}
