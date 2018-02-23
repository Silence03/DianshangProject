package com.atguigu.mall.sale.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.sale.bean.DETAIL_T_MALL_SKU;
import com.atguigu.mall.sale.bean.OBJECCT_T_MALL_SKU;
import com.atguigu.mall.sale.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mall.sale.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_SKU;
import com.atguigu.mall.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.sale.service.AttrService;
import com.atguigu.mall.sale.service.SkuListService;
import com.atguigu.mall.sale.util.CacheUtil;
import com.atguigu.mall.sale.util.JedisPoolUtils;
import com.atguigu.mall.sale.util.MyJsonUtil;

import redis.clients.jedis.Jedis;

@Controller
public class SkuListController {
	@Autowired
	AttrService attrService;
	@Autowired
	SkuListService skuListService;
	
	//根据属性检索商品(数组方式传递参数)
//	@RequestMapping("/get_sku_list_by_attr")
//	public String get_sku_list_by_attr(Integer class_2_id,@RequestParam("attr_param[]") String[] attr_param, Map<String,Object>map) {
//		//List<OBJECCT_T_MALL_SKU> skulist = skuListService.select_skulist_by_attr(class_2_id, list_sku_av.getList_sku_av());
//		//map.put("skulist", skulist);
//		return "sale_sku_list_inner";
//	}
	//根据属性检索商品(json方式传参)
	@RequestMapping("/get_sku_list_by_attr")
	public String get_sku_list_by_attr(String order,Integer class_2_id,OBJECT_T_MALL_SKU_ATTR_VALUE list_sku_av, Map<String,Object>map) {
		//mysql数据库查询
		//List<OBJECCT_T_MALL_SKU> skulist = skuListService.select_skulist_by_attr(class_2_id, list_sku_av.getList_sku_av());
		
		
		List<OBJECCT_T_MALL_SKU> skulist = null;
		//1.缓存查询
		List<T_MALL_SKU_ATTR_VALUE> list_sku_av2 = list_sku_av.getList_sku_av();
		String new_key = "";
		if(list_sku_av2!=null && list_sku_av2.size()>0) {
			String[] keys = new String[list_sku_av2.size()];//一个属性名id(key)对应属性值集合(value)
			for (int i = 0; i < list_sku_av2.size(); i++) {
				String key = "attr_"+class_2_id+"_"+list_sku_av2.get(i).getShxm_id()+"_"+list_sku_av2.get(i).getShxzh_id();
				keys[i]=key;
			}
			new_key = CacheUtil.getinnerkey(keys);
			skulist = CacheUtil.getMyListByKey(new_key, OBJECCT_T_MALL_SKU.class);
		}
		
		if(skulist==null || skulist.size()==0) {
			//2.mysql查询
			skulist = skuListService.select_skulist_by_attr(class_2_id, list_sku_av.getList_sku_av(),order);
			
			//同步缓存
			CacheUtil.setMyListByKey(new_key, skulist);
		}
		
		
		map.put("skulist", skulist);
		map.put("class_2_id", class_2_id);
		//map.put("order", order);
		return "sale_sku_list_inner";
	}
	@RequestMapping("/goto_attr_list")
	public String goto_attr_list(Integer class_2_id,Map<String,Object>map) {
		//商品spu列表
		//List<OBJECCT_T_MALL_SKU> sku_list = skuListService.get_skulist(class_2_id);
		List<OBJECCT_T_MALL_SKU> sku_list = null;
		//缓存检索(缓存redis使用zset存储)
		String key = "class_2_"+class_2_id;
		sku_list = CacheUtil.getMyListByKey(key,OBJECCT_T_MALL_SKU.class);
		
		if(sku_list==null || sku_list.size()==0) {
			//若缓存中未获取到值则走mysql数据库
			sku_list = skuListService.get_skulist(class_2_id);
			//同步缓存
			CacheUtil.setMyListByKey(key,sku_list);
		}
		
		//后台检索属性列表
		List<OBJECT_T_MALL_ATTR> attr_list = attrService.get_attrlist(class_2_id);
		map.put("attrlist", attr_list);
		map.put("skulist", sku_list);
		map.put("class_2_id", class_2_id);
		return "sale_sku_list";
	}
	//点击去往商品详情页
	@RequestMapping("/goto_sku_detail")
	public String goto_sku_detail(Integer sku_id,Integer spu_id,Map<String,Object> map) {
		DETAIL_T_MALL_SKU obj_sku = skuListService.get_sku_detail(sku_id, spu_id);//商品详情数据
		List<T_MALL_SKU> list_sku = skuListService.get_sku_list_by_spu_id(spu_id);//与该商品同一个spuid下的所有商品
		map.put("obj_sku", obj_sku);
		map.put("list_sku", list_sku);
		return "sale_sku_detail";
	}
	

}
