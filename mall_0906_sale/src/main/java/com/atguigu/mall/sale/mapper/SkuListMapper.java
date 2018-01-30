package com.atguigu.mall.sale.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.mall.sale.bean.DETAIL_T_MALL_SKU;
import com.atguigu.mall.sale.bean.OBJECCT_T_MALL_SKU;
import com.atguigu.mall.sale.bean.T_MALL_SKU;

public interface SkuListMapper {

	List<OBJECCT_T_MALL_SKU> select_skulist_by_class_2_id(Integer class_2_id);

	List<OBJECCT_T_MALL_SKU> select_skulist_by_attr(Map<String,Object> map);

	DETAIL_T_MALL_SKU select_sku_detail(Map<String, Object> map);

	List<T_MALL_SKU> select_sku_list_by_spu_id(Map<String, Object> map);
}
