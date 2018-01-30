package com.atguigu.mall.sale.service;

import java.util.List;

import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;

public interface ShoppingCartService {

	List<T_MALL_SHOPPINGCAR> get_list_cart(T_MALL_USER_ACCOUNT user);

	void update_list_cart(T_MALL_SHOPPINGCAR cart);

	void insert_list_cart(T_MALL_SHOPPINGCAR cart);

}
