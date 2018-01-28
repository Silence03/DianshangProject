package com.atguigu.mall.sale.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.mall.sale.bean.OBJECT_T_MALL_ATTR;


public interface AttrMapper {

	void insert_attr(Map<String, Object> map);

	void insert_arrt_value(Map<String, Object> map2);

	List<OBJECT_T_MALL_ATTR> selectattrlist(Integer class_2_id);

	

}
