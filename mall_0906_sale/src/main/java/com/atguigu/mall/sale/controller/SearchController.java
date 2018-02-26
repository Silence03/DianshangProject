package com.atguigu.mall.sale.controller;

import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpSession;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.mall.sale.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.mall.sale.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mall.sale.util.MyHttpGetUtil;
import com.atguigu.mall.sale.util.MyJsonUtil;
import com.atguigu.mall.sale.util.PropertiyUtil;

@Controller
public class SearchController {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ActiveMQQueue queueDestination;

	@RequestMapping("/search")
	public String search(String keywords,Map<String,Object>map,HttpSession session) {
		String doGet = null;
		try {
			doGet = MyHttpGetUtil.doGet(PropertiyUtil.getPropertity("solr.properties", "search_url")+keywords+".do");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<KEYWORDS_T_MALL_SKU> skulist = MyJsonUtil.json_to_list(doGet, KEYWORDS_T_MALL_SKU.class);
		map.put("skulist", skulist);
		map.put("keywords", keywords);
		
		
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		if(loginuser!=null) {
			//调用系统日志服务，写入搜索关键字日志
			int userid = loginuser.getId();
			String yh_mch = loginuser.getYh_mch();
			jmsTemplate.send(queueDestination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage("userid="+userid+"&yh_mch="+yh_mch+"&keywords="+keywords);
				}
			});
		}
		
		
		return "sale_searchlist";
	}
	

}
