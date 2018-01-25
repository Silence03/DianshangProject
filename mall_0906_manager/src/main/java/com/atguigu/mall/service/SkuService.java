package com.atguigu.mall.service;

import com.atguigu.mall.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.bean.T_MALL_SKU;

public interface SkuService {

	void save_sku(T_MALL_SKU sku, OBJECT_T_MALL_SKU_ATTR_VALUE list_sku_av);
	
}
