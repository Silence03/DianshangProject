package com.atguigu.mall.sale.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.mall.sale.bean.OBJECCT_T_MALL_SKU;

public interface SkuListMapper {

	List<OBJECCT_T_MALL_SKU> select_skulist_by_class_2_id(Integer class_2_id);

	List<OBJECCT_T_MALL_SKU> select_skulist_by_attr(Map<String,Object> map);
}
