package com.atguigu.mall.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.mall.bean.OBJECCT_T_MALL_SKU;
import com.atguigu.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.service.AttrService;
import com.atguigu.mall.service.SkuListService;
import com.atguigu.mall.utils.CacheUtil;

@Controller
public class CacheController {
	
	@Autowired
	AttrService attrService;
	
	@Autowired
	SkuListService skuListService;
	
	@RequestMapping("/go_cache")
	public String go_cache() {
		return "manager_cache";
	}
	@RequestMapping("/refresh_attr_cache")
	@ResponseBody
	public int refresh_attr_cache(Integer class_2_id,@RequestParam("attrs[]")Integer attrs[]) {
		
		int count = 0;
		for (int i = 0; i < attrs.length; i++) {
			int attr_id = attrs[i];
			List<Integer> value_list = attrService.getValueIdByAttrId(attr_id);
			for (int j = 0; j < value_list.size(); j++) {
				Integer value_id = value_list.get(j);
				//生成key
				String key = "attr_"+class_2_id+"_"+attr_id+"_"+value_id;
				//根据属性查询对应的商品集合
				List<T_MALL_SKU_ATTR_VALUE> list_av = new ArrayList<>();
				T_MALL_SKU_ATTR_VALUE av = new T_MALL_SKU_ATTR_VALUE();
				av.setShxm_id(attr_id);
				av.setShxzh_id(value_id);
				list_av.add(av);
				List<OBJECCT_T_MALL_SKU> attrlist = skuListService.select_skulist_by_attr(class_2_id, list_av);
				//同步缓存 将key及对应的集合刷新入缓存
				CacheUtil.setMyListByKey(key, attrlist);
				count = count +attrlist.size();
			}
		}
		return count;
	}
	
}
