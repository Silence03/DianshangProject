package com.atguigu.mall.sale.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.mall.sale.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.mall.sale.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.mall.sale.bean.T_MALL_ADDRESS;
import com.atguigu.mall.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.mall.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.sale.exception.MySaleException;
import com.atguigu.mall.sale.service.OrderService;
import com.atguigu.mall.sale.service.ShoppingCartService;
import com.atguigu.mall.sale.util.MyJsonUtil;
import com.atguigu.mall.server.AddressServer;
@Controller
@SessionAttributes("order")
public class OrderController {
	
	@Autowired
	AddressServer addressServer;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ActiveMQQueue queueDestination;
	
	//保存订单处理
	@RequestMapping("/save_order")
	public String save_order(String address_id,@ModelAttribute("order")OBJECT_T_MALL_ORDER order,HttpSession session) {
		T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		// 保存订单的业务
		String addressstr = addressServer.getAddressByid(Integer.parseInt(address_id));
		T_MALL_ADDRESS address = MyJsonUtil.json_to_object(addressstr, T_MALL_ADDRESS.class);
		orderService.save_order(order,address);
		//重新同步购物车的session
		session.setAttribute("list_cart", shoppingCartService.get_list_cart(user));
		
		return "redirect:/goto_pay.do";
	}
	//去往支付页面
	@RequestMapping("/goto_pay")
	public String goto_pay() {
		return "sale_pay";
	}
	//支付完成后进行的业务，即修改订单状态以及更新信息
	@RequestMapping("/pay_after")
	public String pay_after(@ModelAttribute("order")OBJECT_T_MALL_ORDER order) {
		//调用支付服务成功后调用订单通知业务(MQ消息队列)
		try {
			orderService.pay_order(order);
			
			//调用系统日志服务，写入订单日志
			String order_json = MyJsonUtil.object_to_json(order);
			jmsTemplate.send(queueDestination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage("order="+order_json);
				}
			});
			
			
		} catch (MySaleException e) {
			e.printStackTrace();
			return "sale_error";
		}
		return "sale_pay_success";
	}
	
	//点击去结算按钮，拆单功能实现方法
		@RequestMapping("/goto_checkout")
		public String goto_checkout(String sum,HttpSession session,Map<String,Object>map) {
			T_MALL_USER_ACCOUNT user = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
			List<T_MALL_SHOPPINGCAR> list_cart = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("list_cart");
			if(user==null) {
				return "redirect:/goto_sale_login.do";
			}
			//用户不为空，生成订单
			OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
			order.setZje(new BigDecimal(sum));
			order.setJdh(0);
			order.setYh_id(user.getId());
			
			
			Set<String> set_kcdz = new HashSet<>();
			//根据库存地址进行拆单
			for (int i = 0; i < list_cart.size(); i++) {
				if(list_cart.get(i).getShfxz().equals("1")) {
					set_kcdz.add(list_cart.get(i).getKcdz());
				}
			}
			
			Iterator<String> kcdz = set_kcdz.iterator();
			List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
			while(kcdz.hasNext()) {
				String kcdz1 = kcdz.next();
				//一个库存地址生成一个物流包裹
				OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();
				flow.setPsfsh("天天快递");
				flow.setYh_id(user.getId());
				flow.setMqdd("商品等待出库");
				//遍历循环添加每个包裹中的商品信息
				List<T_MALL_ORDER_INFO> list_info = new ArrayList<>();
				for (int i = 0; i < list_cart.size(); i++) {
					if(list_cart.get(i).getShfxz().equals("1") && list_cart.get(i).getKcdz().equals(kcdz1)) {
						T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
						//商品sku_id
						info.setSku_id(list_cart.get(i).getSku_id());
						//商品价格
						info.setSku_jg(list_cart.get(i).getSku_jg());
						//商品名称
						info.setSku_mch(list_cart.get(i).getSku_mch());
						//商品图片
						info.setShp_tp(list_cart.get(i).getShp_tp());
						//商品数量
						info.setSku_shl(list_cart.get(i).getTjshl());
						//库存地址
						info.setSku_kcdz(list_cart.get(i).getKcdz());
						//购物车id
						info.setGwch_id(list_cart.get(i).getId());
						
						//将购物车中商品添加到订单详情中
						list_info.add(info);
					}
				}
				//订单详情放入包裹中
				flow.setList_info(list_info);
				//物流包裹放到集合中
				list_flow.add(flow);
			}
			//把包裹放入订单中
			order.setList_flow(list_flow);
			map.put("order", order);
			map.put("list_address",MyJsonUtil.json_to_list(addressServer.getAddressByYhid(user), T_MALL_ADDRESS.class));
			
			return "sale_checkout";
		}
}
