package com.atguigu.mall.log.serviceimp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.mall.log.mapper.LogMapper;
import com.atguigu.mall.log.service.LogService;
import com.atguigu.mall.utils.MyJsonUtil;

@Service
public class LogServiceImpl implements LogService{
	
	@Autowired
	LogMapper logMapper;

	@Override
	public void saveLoginout(String text) {
		//userid=1&yh_mch=lilei&login
		Map<String,Object> map = new HashMap<>();
		String[] strings = text.split("&");
		for (int i = 0; i < strings.length; i++) {
			String[] strings2 = strings[i].split("=");
			if("userid".equals(strings2[0])) {
				int userid=Integer.parseInt(strings2[1]);
				map.put("userid", userid);
			}
			if("yh_mch".equals(strings2[0])) {
				String yh_mch=strings2[1];
				map.put("yh_mch", yh_mch);
			}
			if("inorout".equals(strings2[0])) {
				String inorout=strings2[1];
				map.put("inorout", inorout);
			}
			if("keywords".equals(strings2[0])) {
				String keywords=strings2[1];
				map.put("keywords", keywords);
			}
			if("order".equals(strings2[0])) {
				String order_json=strings2[1];
				OBJECT_T_MALL_ORDER order = MyJsonUtil.json_to_object(order_json, OBJECT_T_MALL_ORDER.class);
				map.put("order", order);
			}
			
		}
		
		map.put("text", text);
		
		if(text.contains("inorout")) {
			logMapper.insertLoginout(map);
		}else {
			if(text.contains("keywords")) {
				logMapper.insertKeyWords(map);
			}else {
				if(text.contains("order")) {
					logMapper.insertOrderLog(map);
				}
			}
		}
		
	}



}
