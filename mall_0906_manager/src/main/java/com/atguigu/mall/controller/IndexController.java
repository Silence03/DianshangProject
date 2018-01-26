package com.atguigu.mall.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/manager_index")
	public String managerIndex(String url,String title,Map<String,Object> map) {
		map.put("url", url);
		map.put("title", title);
		return "manager_index";
	}
}
