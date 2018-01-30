package com.atguigu.mall.sale.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.sale.service.ShoppingCartService;
import com.atguigu.mall.sale.util.MyJsonUtil;

@Controller
public class ShoppingCartController {
	
	@Autowired
	ShoppingCartService shoppingCartService;

	// 添加购物车功能
	@RequestMapping("/add_cart")
	public String add_cart(@CookieValue(value="list_cart_cookie",required=false)String list_cart_cookie,T_MALL_SHOPPINGCAR cart, HttpSession session,HttpServletResponse response) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 调用购物车添加业务
		if (user == null) {
			// 操作cookie
			if (list_cart_cookie == null || list_cart_cookie.equals("")) {
				// cookie为空
				// 添加新数据
				list_cart.add(cart);

			} else {
				// cookie不空
				// list_cart_cookie , 转化成java购物车对象
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
				// 判断是否重复
				boolean b = if_new_cart(list_cart, cart);
				if (b) {
					// 未添加过
					list_cart.add(cart);
				} else {
					// 添加过，更新
					for (int i = 0; i < list_cart.size(); i++) {
						if(list_cart.get(i).getSku_id()==cart.getSku_id()) {
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl()+1);
							list_cart.get(i).setHj(list_cart.get(i).getHj()+cart.getSku_jg());
						}
					}
				}

			}

			// 覆盖客户端cookie，更新浏览器本地cookie数据
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);
		} else {//用户不为空
			// 操作session
			List<T_MALL_SHOPPINGCAR> list_cart_user = shoppingCartService.get_list_cart(user);
			for (T_MALL_SHOPPINGCAR mall_cart : list_cart_user) {
				if(mall_cart.getSku_id()==cart.getSku_id()) {//已经存在，更新
					shoppingCartService.update_list_cart(cart);
					break;
				}else {
					shoppingCartService.insert_list_cart(cart);
					break;
				}
			}
		}
		return "sale_cart_success";
	}

	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		for (T_MALL_SHOPPINGCAR t_MALL_SHOPPINGCAR : list_cart) {
			if(t_MALL_SHOPPINGCAR.equals(cart)) {
				return false;
			}
		}
		return true;
	}
}
