package com.atguigu.mall.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.mall.bean.MODEL_OBJECT_T_MALL_ATTR;
import com.atguigu.mall.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mall.service.AttrService;

@Controller
public class AttrController {
	@Autowired
	AttrService attrService;
	
	//去往属性查询列表页面
	@RequestMapping("/get_attr_list_json.do")
	@ResponseBody
	public List<OBJECT_T_MALL_ATTR> get_attr_list_json(Integer class_2_id,Map<String,Object> map) {
		List<OBJECT_T_MALL_ATTR> attrlist = attrService.get_attrlist(class_2_id);
		map.put("attrlist", attrlist);
		return attrlist;
	}
	//去往属性查询列表页面
	@RequestMapping("/get_attr_list.do")
	public String get_attr_list(Integer class_2_id,Map<String,Object> map) {
		List<OBJECT_T_MALL_ATTR> attrlist = attrService.get_attrlist(class_2_id);
		map.put("attrlist", attrlist);
		return "manager_attrquery_inner";
	}
	
	
	@RequestMapping("/manager_attr")
	public String manager_attr() {
		return "manager_attr";
	}
	@RequestMapping("/goto_attr_list.do")
	public String goto_attr_list() {
		return "manager_attr_list";
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
