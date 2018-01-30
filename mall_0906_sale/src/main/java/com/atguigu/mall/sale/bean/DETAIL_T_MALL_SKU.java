package com.atguigu.mall.sale.bean;

import java.util.List;

public class DETAIL_T_MALL_SKU extends T_MALL_SKU{
	T_MALL_PRODUCT spu;
	List<T_MALL_PRODUCT_IMAGE> list_img;
	List<OBJECT_T_MALL_AV_NAME> list_av_name;
	public T_MALL_PRODUCT getSpu() {
		return spu;
	}
	public void setSpu(T_MALL_PRODUCT spu) {
		this.spu = spu;
	}
	public List<T_MALL_PRODUCT_IMAGE> getList_img() {
		return list_img;
	}
	public void setList_img(List<T_MALL_PRODUCT_IMAGE> list_img) {
		this.list_img = list_img;
	}
	public List<OBJECT_T_MALL_AV_NAME> getList_av_name() {
		return list_av_name;
	}
	public void setList_av_name(List<OBJECT_T_MALL_AV_NAME> list_av_name) {
		this.list_av_name = list_av_name;
	}
	
	
}
