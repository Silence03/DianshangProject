package com.atguigu.mall.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.bean.T_MALL_ADDRESS;
import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.mapper.AddressMapper;
import com.atguigu.mall.service.AddressService;

@Service
public class AddressServiceimpl implements AddressService {
	
	@Autowired
	AddressMapper addressMapper;

	@Override
	public int insertAddressByYhid(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		return addressMapper.insertAddressByYhid(address);
	}

	@Override
	public int updateAddressByYhid(T_MALL_ADDRESS address) {
		// TODO Auto-generated method stub
		return addressMapper.updateAddressByYhid(address);
	}

	@Override
	public List<T_MALL_ADDRESS> getAddressByYhid(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		return addressMapper.selectAddressByYhid(user);
	}

	@Override
	public T_MALL_ADDRESS getAddressByid(Integer addr_id) {
		// TODO Auto-generated method stub
		return addressMapper.selectAddressByid(addr_id);
	}

}
