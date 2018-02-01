package com.atguigu.mall.service;

import java.util.List;

import com.atguigu.mall.bean.T_MALL_ADDRESS;
import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;

public interface AddressService {

	int insertAddressByYhid(T_MALL_ADDRESS address);

	int updateAddressByYhid(T_MALL_ADDRESS address);

	List<T_MALL_ADDRESS> getAddressByYhid(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS getAddressByid(Integer addr_id);

}
