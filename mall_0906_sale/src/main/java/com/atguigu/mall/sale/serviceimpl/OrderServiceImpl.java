package com.atguigu.mall.sale.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.sale.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.mall.sale.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.mall.sale.bean.T_MALL_ADDRESS;
import com.atguigu.mall.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.mall.sale.mapper.OrderMapper;
import com.atguigu.mall.sale.mapper.ShoppingCartMapper;
import com.atguigu.mall.sale.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	ShoppingCartMapper shoppingCartMapper;

	@Override
	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS address) {
		order.setDzh_id(address.getId());
		order.setDzh_mch(address.getYh_dz());
		order.setShhr(address.getShjr());
		order.setJdh(1);
		System.out.println(order);
		orderMapper.insert_order(order);
		
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		//orderMapper.insert_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			// 根据订单id保存物流包裹
			// psfsh 配送方式,
			// yh_id 用户id,
			// dd_id 订单id,
			// mqdd 目前地点,
			// mdd 目的地
			Map<String,Object> flow_map = new HashMap<>();
			flow_map.put("dd_id", order.getId());
			flow_map.put("flow",list_flow.get(i));
			flow_map.put("address", address);
			orderMapper.insert_flow(flow_map);
		
			
			//根据包裹id插入订单详情
			Map<String,Object> info_map = new HashMap<>();
			List<T_MALL_ORDER_INFO> list_info = list_flow.get(i).getList_info();
			info_map.put("dd_id", order.getId());
			info_map.put("flow_id", list_flow.get(i).getId());
			info_map.put("list_info", list_info);
			orderMapper.insert_infos(info_map);
			
			List<Integer> list_cart_id = new ArrayList<>();
			for (int j = 0; j < list_info.size(); j++) {
				list_cart_id.add(list_info.get(j).getGwch_id());
			}
			
			shoppingCartMapper.delete_cart_bycartid(list_cart_id);
		}
		
	}

}
