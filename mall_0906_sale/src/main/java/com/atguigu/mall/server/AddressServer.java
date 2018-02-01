package com.atguigu.mall.server;

import javax.jws.WebService;

import com.atguigu.mall.sale.bean.T_MALL_ADDRESS;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressServer {
	public String insertAddressByYhid(T_MALL_ADDRESS address);
	public String updateAddressByYhid(T_MALL_ADDRESS address);
	public String getAddressByYhid(T_MALL_USER_ACCOUNT user);
	public String getAddressByid(Integer addr_id);
}
