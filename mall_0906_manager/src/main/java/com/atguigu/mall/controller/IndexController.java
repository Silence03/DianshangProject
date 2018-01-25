package com.atguigu.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/manager_index")
	public String managerIndex() {
		return "manager_index";
	}
}
