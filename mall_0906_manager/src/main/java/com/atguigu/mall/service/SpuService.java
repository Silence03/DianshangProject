package com.atguigu.mall.service;

import java.util.List;

import com.atguigu.mall.bean.T_MALL_PRODUCT;

public interface SpuService {

	void saveSpuProduct(T_MALL_PRODUCT tmp,List<String> list_img);

	List<T_MALL_PRODUCT> get_spu(Integer flbh2, Integer pp_id);

}
