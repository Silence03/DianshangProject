package com.atguigu.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.mall.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mall.service.AttrService;

@Controller
public class AttrController {
	@Autowired
	AttrService attrService;
	
	@RequestMapping("/manager_attr")
	public String manager_attr() {
		return "manager_attr";
	}
	@RequestMapping("/manager_attr_add")
	public String manager_attr_add() {
		return "manager_attr_add";
	}
	@RequestMapping("/save_attr.do")
	public String save_attr(Integer flbh2,MODEL_OBJECT_T_MALL_ATTR list_attr) {
		//list_attr[0].list_value[0].shxzh属性值
		//list_attr[0].shxm_mch属性名
		//list_attr[0].list_value[0].shxzh_mch属性值名
		List<OBJECT_T_MALL_ATTR> list_attr2 = list_attr.getList_attr();
		attrService.saveattr(flbh2,list_attr2);
		return "redirect:/manager_attr_add.do";
	}
	
}
