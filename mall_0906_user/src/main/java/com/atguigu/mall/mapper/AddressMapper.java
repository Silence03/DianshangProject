package com.atguigu.mall.mapper;

import java.util.List;

import com.atguigu.mall.bean.T_MALL_ADDRESS;
import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;

public interface AddressMapper {

	int insertAddressByYhid(T_MALL_ADDRESS address);

	int updateAddressByYhid(T_MALL_ADDRESS address);

	List<T_MALL_ADDRESS> selectAddressByYhid(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS selectAddressByid(Integer addr_id);

}
