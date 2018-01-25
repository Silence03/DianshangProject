package com.atguigu.mall.service;

import java.util.List;

import com.atguigu.mall.bean.OBJECT_T_MALL_ATTR;

public interface AttrService {

	void saveattr(Integer flbh2,List<OBJECT_T_MALL_ATTR> list_attr2);

	List<OBJECT_T_MALL_ATTR> get_attrlist(Integer class_2_id);

}
