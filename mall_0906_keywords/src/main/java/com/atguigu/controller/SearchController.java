package com.atguigu.controller;

import java.util.List;

import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.util.MySolrUtil;

@Controller
public class SearchController {
	
	@RequestMapping("/search/{keywords}")
	@ResponseBody
	public List<KEYWORDS_T_MALL_SKU> search(@PathVariable String keywords) {
		HttpSolrServer solr = MySolrUtil.getMySolr("mySolr.properties", "solr_url");
		List<KEYWORDS_T_MALL_SKU> mySolrData = MySolrUtil.getMySolrData(solr, KEYWORDS_T_MALL_SKU.class, keywords);
		return mySolrData;
	}
}
