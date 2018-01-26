package com.atguigu.mall.sale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping("/goto_sale_index")
	public String sale_index(@CookieValue(value="yh_nch",required=false) String yh_nch) {
		System.out.println("yh_nch==>"+yh_nch);
		return "sale_index";
	}
}
