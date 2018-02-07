package com.atguigu.mall.sale.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.sale.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.mall.sale.util.MyHttpGetUtil;
import com.atguigu.mall.sale.util.MyJsonUtil;
import com.atguigu.mall.sale.util.PropertiyUtil;

@Controller
public class SearchController {
	

	@RequestMapping("/search")
	public String search(String keywords,Map<String,Object>map) {
		String doGet = null;
		try {
			doGet = MyHttpGetUtil.doGet(PropertiyUtil.getPropertity("solr.properties", "search_url")+keywords+".do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<KEYWORDS_T_MALL_SKU> skulist = MyJsonUtil.json_to_list(doGet, KEYWORDS_T_MALL_SKU.class);
		map.put("skulist", skulist);
		map.put("keywords", keywords);
		return "sale_searchlist";
	}
	

}
