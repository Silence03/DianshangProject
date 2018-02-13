package com.atguigu.mall.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.mall.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.mall.bean.OBJECCT_T_MALL_SKU;
import com.atguigu.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.mapper.SearchMapper;
import com.atguigu.mall.service.AttrService;
import com.atguigu.mall.service.SkuListService;
import com.atguigu.mall.utils.CacheUtil;
import com.atguigu.mall.utils.FactoryUtil;
import com.atguigu.mall.utils.MyPropertiesUtil;

@Controller
public class CacheController {
	
	@Autowired
	SearchMapper searchMapper;
	
	@Autowired
	AttrService attrService;
	
	@Autowired
	SkuListService skuListService;
	
	@RequestMapping("/go_cache")
	public String go_cache() {
		return "manager_cache";
	}
	@RequestMapping("/go_keywordscache")
	public String go_keywordscache() {
		return "manager_keywords";
	}
	/**
	 * 商品属性缓存刷新
	 */
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
	/**
	 * 商品关键字检索缓存刷新
	 */
	@RequestMapping("/refresh_keywords_cache")
	@ResponseBody
	public int refresh_keywords_cache(@RequestParam("keywords[]")Integer keywords[]) {
//		SqlSessionFactory sessionfactory = FactoryUtil.getMyFactory();
//		SqlSession session = sessionfactory.openSession();
//		
//		SearchMapper keywordsMapper = session.getMapper(SearchMapper.class);
		int count = 0;
		for (int i = 0; i < keywords.length; i++) {
			List<KEYWORDS_T_MALL_SKU> list_keywords = searchMapper.select_skulist_by_class_2_id(keywords[i]);
			System.out.println(list_keywords.size());
			//获取solr客户端连接
			HttpSolrServer solr = new HttpSolrServer(MyPropertiesUtil.getMyProperty("mySolr.properties", "solr_url"));
			//设置solr的解析方式(solr与java之间的数据传输方式)
			solr.setParser(new XMLResponseParser());
			//向solr导入数据
			try {
				solr.addBeans(list_keywords);
				solr.commit();
				count = count+list_keywords.size();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		return count;
	}
	
}
