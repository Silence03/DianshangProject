package com.atguigu.mall.sale.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.sale.mapper.ShoppingCartMapper;
import com.atguigu.mall.sale.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@Override
	public List<T_MALL_SHOPPINGCAR> get_list_cart(T_MALL_USER_ACCOUNT user) {
		
		return shoppingCartMapper.select_list_cart(user);
	}

	@Override
	public void update_list_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.update_list_cart(cart);
		
	}

	@Override
	public void insert_list_cart(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.insert_list_cart(cart);
		
	}

	@Override
	public void update_cartcookie2db(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.update_cartcookie2db(cart);
		
	}

	@Override
	public void update_cart_status(T_MALL_SHOPPINGCAR cart) {
		shoppingCartMapper.update_cart_status(cart);
		
	}

}
