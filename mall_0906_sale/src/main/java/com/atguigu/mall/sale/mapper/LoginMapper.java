package com.atguigu.mall.sale.mapper;

import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;

public interface LoginMapper {

	T_MALL_USER_ACCOUNT selectUserByMcAndMm(T_MALL_USER_ACCOUNT user);

}
