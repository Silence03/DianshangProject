package com.atguigu.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.atguigu.bean.T_mall_class_1;
import com.atguigu.bean.T_mall_class_2;
import com.atguigu.bean.T_mall_trade_mark;
import com.atguigu.mapper.T_mall_class_1Mapper;
import com.atguigu.mapper.T_mall_class_2Mapper;
import com.atguigu.mapper.T_mall_trade_markMapper;
import com.google.gson.Gson;

public class TestJson {
	@Test
	public void testjson() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		//一级分类
		T_mall_class_1Mapper t_mall_class_1Mapper = session.getMapper(T_mall_class_1Mapper.class);
		List<T_mall_class_1> class_1list = t_mall_class_1Mapper.selectallclass_1();
		Gson gson = new Gson(); 
		String class_1_string = gson.toJson(class_1list);
		System.out.println(class_1_string);
		//导入到文件中
		FileOutputStream out = new FileOutputStream("E:/json/class_1.js");
		out.write(class_1_string.getBytes());
		///////////////////////////////////////////////////////
		//2级分类
		for (T_mall_class_1 t_mall_class_1 : class_1list) {
			T_mall_class_2Mapper t_mall_class_2Mapper = session.getMapper(T_mall_class_2Mapper.class);
			List<T_mall_class_2> selectallclass_2 = t_mall_class_2Mapper.selectallclass_2(t_mall_class_1.getId());
			String class_2_string = gson.toJson(selectallclass_2);
			System.out.println("2级==>"+class_2_string);
			//写入文件
			out = new FileOutputStream("E:/json/class_2_"+t_mall_class_1.getId()+".js");
			out.write(class_2_string.getBytes());
		}
		///////////////////////////////////////////////////////
		//商标
		System.out.println("--------------------");
		for (T_mall_class_1 t_mall_class_1 : class_1list) {
			T_mall_trade_markMapper t_mall_trade_markMapper = session.getMapper(T_mall_trade_markMapper.class);
			List<T_mall_trade_mark> maker_list = t_mall_trade_markMapper.selectallmaker_2(t_mall_class_1.getId());
			String maker_str = gson.toJson(maker_list);
			System.out.println("商标==>"+maker_str);
			out = new FileOutputStream("E:/json/tm_class_1_"+t_mall_class_1.getId()+".js");
			out.write(maker_str.getBytes());
		}
		
		
		
		out.close();
		
	}
}
