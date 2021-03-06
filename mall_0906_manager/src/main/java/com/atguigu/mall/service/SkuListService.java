package com.atguigu.mall.service;

import java.util.List;

import com.atguigu.mall.bean.DETAIL_T_MALL_SKU;
import com.atguigu.mall.bean.OBJECCT_T_MALL_SKU;
import com.atguigu.mall.bean.T_MALL_SKU;
import com.atguigu.mall.bean.T_MALL_SKU_ATTR_VALUE;

public interface SkuListService {

	List<OBJECCT_T_MALL_SKU> get_skulist(Integer class_2_id);

	List<OBJECCT_T_MALL_SKU> select_skulist_by_attr(Integer class_2_id,List<T_MALL_SKU_ATTR_VALUE> list_av);

	DETAIL_T_MALL_SKU get_sku_detail(Integer sku_id, Integer spu_id);

	List<T_MALL_SKU> get_sku_list_by_spu_id(Integer spu_id);

}
