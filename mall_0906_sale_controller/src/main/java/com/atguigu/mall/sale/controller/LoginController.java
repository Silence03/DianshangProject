package com.atguigu.mall.sale.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.sale.service.LoginService;
import com.atguigu.mall.sale.service.ShoppingCartService;
import com.atguigu.mall.sale.util.MyJsonUtil;
import com.atguigu.mall.server.UserServer;

@Controller
public class LoginController {
	
	@Autowired
	UserServer userServer;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ActiveMQQueue queueDestination;
	
	@RequestMapping("/goto_sale_login")
	public String toLogin() {
		return "sale_login";
	}
	//注销
	@RequestMapping("/goto_logout")
	public String goto_logout(HttpSession session) {
		
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		//调用系统日志服务，写入日志
		int userid = loginuser.getId();
		String yh_mch = loginuser.getYh_mch();
		jmsTemplate.send(queueDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("userid="+userid+"&yh_mch="+yh_mch+"&inorout=logout");
			}
		});
		//注销session
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
	public String dologin(String dataSource_type,@CookieValue(value="list_cart_cookie",required=false)String list_cart_cookie,T_MALL_USER_ACCOUNT user,Map<String,Object> map,HttpSession session,HttpServletResponse response)  {
		
		//调用用户ws服务
//		String url = PropertiyUtil.getPropertity("ws.properties", "user_url");
//		UserServer userserver = MyWsUtil.getMyWs(url, UserServer.class);
//		T_MALL_USER_ACCOUNT loginuser = userserver.login(user);
		T_MALL_USER_ACCOUNT loginuser =null;
		if(dataSource_type.equals("1")) {
			loginuser = userServer.login(user);
		}else {
			loginuser = userServer.login2(user);
		}
		
		
		//T_MALL_USER_ACCOUNT loginuser = loginService.getUserByMcAndMm(user);
		
		
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
			
			List<T_MALL_SHOPPINGCAR> list_cart_db = shoppingCartService.get_list_cart(loginuser);
			response.addCookie(cookie);
			session.setAttribute("user", loginuser);
			//session.setAttribute("list_cart", list_cart_user);
			List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
			//db数据为空
			if(list_cart_db==null || list_cart_db.size()==0) {
				//1.cookie为空
				if(list_cart_cookie==null || list_cart_cookie.length()==0) {
				}else {
					//2.cookie不为空
					list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
					for (int i = 0; i < list_cart.size(); i++) {
						list_cart.get(i).setYh_id(loginuser.getId());
						shoppingCartService.insert_list_cart(list_cart.get(i));
					}
				}
			}else {
				//db数据库不为空
				//1.cookie为空
				if(list_cart_cookie==null || list_cart_cookie.length()==0) {
				}else {
					//2.cookie不为空
					list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
					for (int i = 0; i < list_cart.size(); i++) {
						// 判断是否重复
						boolean b = if_new_cart(list_cart_db, list_cart.get(i));
						if (b) {
							// 未添加过
							list_cart.get(i).setYh_id(loginuser.getId());
							shoppingCartService.insert_list_cart(list_cart.get(i));
						} else {
							// 添加过，更新
							list_cart.get(i).setYh_id(loginuser.getId());
							shoppingCartService.update_cartcookie2db(list_cart.get(i));
						}
					}
				}
			}
			//清空cookie,同步session
			Cookie cookie2 = new Cookie("list_cart_cookie", "");
			cookie2.setPath("/");
			response.addCookie(cookie2);
			session.setAttribute("list_cart", shoppingCartService.get_list_cart(loginuser));
			
			//调用系统日志服务，写入日志
			int userid = loginuser.getId();
			String yh_mch = loginuser.getYh_mch();
			jmsTemplate.send(queueDestination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage("userid="+userid+"&yh_mch="+yh_mch+"&inorout=login");
				}
			});

			
			
			
			return "redirect:/goto_sale_index.do";
		}
	}
	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart) {
			if(t_MALL_SHOPPINGCAR.getSku_id()==cart.getSku_id()) {
				return false;
			}
		}
		return true;
	}

}
