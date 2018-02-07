package com.atguigu.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.bean.T_mall_class_1;
import com.atguigu.bean.T_mall_class_2;
import com.atguigu.bean.T_mall_trade_mark;
import com.atguigu.mapper.SearchMapper;
import com.atguigu.mapper.T_mall_class_1Mapper;
import com.atguigu.mapper.T_mall_class_2Mapper;
import com.atguigu.mapper.T_mall_trade_markMapper;
import com.atguigu.util.MyPropertiesUtil;
import com.google.gson.Gson;

public class TestQuerySolr {
	public static void main(String[] args) throws Exception {
		
		//获取solr客户端连接
		HttpSolrServer solr = new HttpSolrServer(MyPropertiesUtil.getMyProperty("mySolr.properties", "solr_url"));
		solr.setParser(new XMLResponseParser());
		//设置solr
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.setRows(50);
		//查询数据
		QueryResponse query = solr.query(solrQuery);
		List<KEYWORDS_T_MALL_SKU> list_keywords = query.getBeans(KEYWORDS_T_MALL_SKU.class);
		System.out.println(list_keywords);
	}
}
