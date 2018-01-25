package com.atguigu.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.mall.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mall.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.bean.T_MALL_PRODUCT;
import com.atguigu.mall.bean.T_MALL_SKU;
import com.atguigu.mall.service.AttrService;
import com.atguigu.mall.service.SkuService;
import com.atguigu.mall.service.SpuService;

@Controller
public class SkuController {
	@Autowired
	AttrService attrService;
	@Autowired
	SpuService spuService;
	
	@Autowired
	SkuService skuService;
	
	@RequestMapping("/manager_sku")
	public String manager_sku() {
		return "manager_sku";
	}
	@RequestMapping("/go_sku_add")
	public String go_sku_add() {
		return "manager_sku_add";
	}
	//根据flbh2查询属性列表
	@RequestMapping("/get_attrlist")
	public String get_attrlist(Integer class_2_id,ModelMap map) {
		List<OBJECT_T_MALL_ATTR> attrlist = attrService.get_attrlist(class_2_id);
		map.put("attrlist", attrlist);
		return "manager_attrlist_inner";
	}
	//添加sku信息
	@RequestMapping("/save_sku")
	public String save_sku(T_MALL_SKU sku,OBJECT_T_MALL_SKU_ATTR_VALUE list_sku_av ) {
		skuService.save_sku(sku,list_sku_av);
		return "redirect:/go_sku_add.do";
	}
	@RequestMapping("/show_sku_form")
	public String show_sku_form() {
		return "manager_sku_form";
	}
	@ResponseBody
	@RequestMapping("/get_spu")
	public List<T_MALL_PRODUCT> get_spu(Integer flbh2,Integer pp_id) {
		List<T_MALL_PRODUCT> list = spuService.get_spu(flbh2,pp_id);
		return list;
	}
	
}
