package com.atguigu.mall.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.mall.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.mapper.UserMapper;
import com.atguigu.mall.service.UserService;
import com.atguigu.mall.util.MyRoutingDataSource;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;

	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		//使用数据源1
		//MyRoutingDataSource.setKey("1");
		T_MALL_USER_ACCOUNT selectUserByYhmcAndPwd = userMapper.selectUserByYhmcAndPwd(user);
		return selectUserByYhmcAndPwd;
	}

	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		// 使用数据源2
		//MyRoutingDataSource.setKey("2");
		T_MALL_USER_ACCOUNT selectUserByYhmcAndPwd = userMapper.selectUserByYhmcAndPwd(user);
		return selectUserByYhmcAndPwd;
	}

	@Override
	public T_MALL_USER_ACCOUNT login3(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByYhmcAndPwd(user);
	}

	@Override
	public T_MALL_USER_ACCOUNT regist(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		return userMapper.insert(user);
	}

	@Override
	public int updateUserById(T_MALL_USER_ACCOUNT user) {
		// TODO Auto-generated method stub
		return userMapper.updateUserById(user);
	}

}
