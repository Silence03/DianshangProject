package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;

public interface SearchMapper {
	List<KEYWORDS_T_MALL_SKU> select_skulist_by_class_2_id(Integer class_2_id);
}
