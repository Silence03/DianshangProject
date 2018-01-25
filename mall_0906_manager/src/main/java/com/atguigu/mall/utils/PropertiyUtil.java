package com.atguigu.mall.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiyUtil {
	public static String getPropertity(String propertity,String key) {
		Properties properties = new Properties();
		InputStream inputStream = PropertiyUtil.class.getClassLoader().getResourceAsStream(propertity);
		try {
			properties.load(inputStream);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		String val = properties.getProperty(key);
		return val;
	}
}
