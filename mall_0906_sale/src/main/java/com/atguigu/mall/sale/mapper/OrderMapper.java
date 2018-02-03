package com.atguigu.mall.sale.mapper;

import java.util.Map;

import com.atguigu.mall.sale.bean.OBJECT_T_MALL_ORDER;

public interface OrderMapper {

	void insert_order(OBJECT_T_MALL_ORDER order);

	void insert_flow(Map<String, Object> flow_map);

	void insert_infos(Map<String, Object> info_map);

}
