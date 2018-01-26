package com.atguigu.mall.sale.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.sale.bean.T_MALL_USER;
import com.atguigu.mall.sale.mapper.LoginMapper;
import com.atguigu.mall.sale.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	LoginMapper loginMapper;

	@Override
	public T_MALL_USER getUserByMcAndMm(T_MALL_USER user) {
		// TODO Auto-generated method stub
		return loginMapper.selectUserByMcAndMm(user);
	}

}
