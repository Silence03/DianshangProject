package com.atguigu.mall.mapper;

import java.util.Map;

import com.atguigu.mall.bean.T_MALL_SKU;

public interface SkuMapper {

	void insert_sku(T_MALL_SKU sku);

	void insert_sku_av(Map<String, Object> map);

}
