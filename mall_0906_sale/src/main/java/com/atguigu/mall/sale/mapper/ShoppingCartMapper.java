package com.atguigu.mall.sale.mapper;

import java.util.List;

import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;

public interface ShoppingCartMapper {

	List<T_MALL_SHOPPINGCAR> select_list_cart(T_MALL_USER_ACCOUNT user);

	void update_list_cart(T_MALL_SHOPPINGCAR cart);

	void insert_list_cart(T_MALL_SHOPPINGCAR cart);

	void update_cartcookie2db(T_MALL_SHOPPINGCAR cart);

	void update_cart_status(T_MALL_SHOPPINGCAR cart);

}
