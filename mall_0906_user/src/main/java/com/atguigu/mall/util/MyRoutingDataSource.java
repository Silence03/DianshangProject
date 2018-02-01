package com.atguigu.mall.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MyRoutingDataSource extends AbstractRoutingDataSource{
	public static ThreadLocal<String> key = new ThreadLocal<>();
	public static String getKey() {
		return key.get();
	}
	public static void setKey(String key_in) {
		key.set(key_in);
	}
	@Override
	protected Object determineCurrentLookupKey() {
		
		return getKey();
	}

}
