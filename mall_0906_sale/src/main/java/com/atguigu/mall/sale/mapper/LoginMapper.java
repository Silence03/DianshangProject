package com.atguigu.mall.sale.mapper;

import com.atguigu.mall.sale.bean.T_MALL_USER;

public interface LoginMapper {

	T_MALL_USER selectUserByMcAndMm(T_MALL_USER user);

}
