package com.atguigu.mall.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.sale.bean.T_MALL_USER;
import com.atguigu.mall.sale.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	
	@RequestMapping("/goto_sale_login")
	public String toLogin() {
		return "sale_login";
	}
	//注销
	@RequestMapping("/goto_logout")
	public String goto_logout(HttpSession session) {
		session.invalidate();
		return "redirect:/goto_sale_index.do";
	}
	//去往注册页面
	@RequestMapping("/goto_regist")
	public String goto_regist() {
		return "sale_regist";
	}
	//用户登陆功能
	@RequestMapping("/login")
	public String dologin(T_MALL_USER user,Map<String,Object> map,HttpSession session,HttpServletResponse response)  {
		T_MALL_USER loginuser = loginService.getUserByMcAndMm(user);
		map.put("user", loginuser);
		if(loginuser==null) {
			return "sale_login";
		}else {
			//System.out.println("loginuser==>"+loginuser);
			String yh_nch = loginuser.getYh_nch();
			Cookie cookie = null;
			try {
				cookie = new Cookie("yh_nch",URLEncoder.encode(yh_nch, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			cookie.setMaxAge(60*60*24);
			cookie.setPath("/");
			
			response.addCookie(cookie);
			session.setAttribute("user", loginuser);
			
			return "redirect:/goto_sale_index.do";
		}
	}

}
