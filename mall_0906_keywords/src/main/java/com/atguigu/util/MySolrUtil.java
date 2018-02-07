package com.atguigu.util;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;

public class MySolrUtil {
	public static HttpSolrServer getMySolr(String properties,String solr_url) {
		//获取solr客户端连接
		HttpSolrServer solr = new HttpSolrServer(MyPropertiesUtil.getMyProperty(properties, solr_url));
		solr.setParser(new XMLResponseParser());
		
		return solr;
	}
	public static <T> List<T> getMySolrData(HttpSolrServer solr,Class<T> t,String keywords) {
		//设置solr
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery(keywords);
		solrQuery.setRows(50);
		QueryResponse query = null;
		try {
			query = solr.query(solrQuery);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<T> list_keywords = query.getBeans(t);
		return list_keywords;
	}
}
