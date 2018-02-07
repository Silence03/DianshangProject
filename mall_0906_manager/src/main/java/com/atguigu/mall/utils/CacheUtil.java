package com.atguigu.mall.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import redis.clients.jedis.Jedis;

public class CacheUtil {

	//根据key获取对应的值
	public static <T> List<T> getMyListByKey(String key, Class<T> t) {
		Jedis jedis = JedisPoolUtils.getJedis();
		if(jedis==null) {
			return null;
		}
		Set<String> zrange = jedis.zrange(key, 0, -1);
		Iterator<String> iterator = zrange.iterator();
		List<T> list = new ArrayList<>();
		while(iterator.hasNext()) {
			String next = iterator.next();
			T objecct_T_MALL_SKU = MyJsonUtil.json_to_object(next, t);
			list.add(objecct_T_MALL_SKU);
		}
		return list;
	}

	//向缓存中存储数据
	public static <T> void setMyListByKey(String key, List<T> list) {
		Jedis jedis = JedisPoolUtils.getJedis();
		if(jedis==null) {
			//打印日志
		}else {
			jedis.del(key);
			for (int i = 0; i < list.size(); i++) {
				jedis.zadd(key, i,MyJsonUtil.object_to_json(list.get(i)));
			}
		}
	}

	//求多个key之间的交集并返回新的key值
	public static String getinnerkey(String[] keys) {
		Jedis jedis = JedisPoolUtils.getJedis();
		String new_key="inter";
		if(jedis==null) {
			return null;
		}else {
			for (int i = 0; i < keys.length; i++) {
				new_key=new_key+"_"+keys[i];
			}
			//判断生成的key是否存在
			Boolean exists = jedis.exists(new_key);
			if(exists) {
				//若存在则直接返回key
				return new_key;
			}else {
				//若不存在则生成新的key返回
				jedis.zinterstore(new_key, keys);
			}
		}
		return new_key;
	}

}
