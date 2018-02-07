package com.atguigu.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
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

public class TestSetSolr {
	public static void main(String[] args) throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		SearchMapper keywordsMapper = session.getMapper(SearchMapper.class);
		List<KEYWORDS_T_MALL_SKU> list_keywords = keywordsMapper.select_skulist_by_class_2_id(28);
		System.out.println(list_keywords.size());
		//获取solr客户端连接
		HttpSolrServer solr = new HttpSolrServer(MyPropertiesUtil.getMyProperty("mySolr.properties", "solr_url"));
		//设置solr的解析方式(solr与java之间的数据传输)
		solr.setParser(new XMLResponseParser());
		//向solr导入数据
		solr.addBeans(list_keywords);
		solr.commit();
		System.out.println(list_keywords);
	}
}
