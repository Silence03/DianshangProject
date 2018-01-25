package com.atguigu.mall.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.mall.bean.T_MALL_PRODUCT;

public interface SpuMapper {

	int insertSpuProduct(T_MALL_PRODUCT tmp);

	void insertSpu_tp(Map<String,Object> parammap);

	List<T_MALL_PRODUCT> selectshpxx(Map<String, Object> map);

	
}
