package com.atguigu.mall.sale.service;

import com.atguigu.mall.sale.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.mall.sale.bean.T_MALL_ADDRESS;

public interface OrderService {

	void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address);

}
