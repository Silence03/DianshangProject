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
import com.atguigu.mall.sale.exception.MySaleException;
import com.atguigu.mall.sale.mapper.OrderMapper;
import com.atguigu.mall.sale.mapper.ShoppingCartMapper;
import com.atguigu.mall.sale.mapper.SkuMapper;
import com.atguigu.mall.sale.service.OrderService;
import com.atguigu.mall.sale.util.MyDateUtil;
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	SkuMapper skuMapper;
	
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

	
	@Override
	public void pay_order(OBJECT_T_MALL_ORDER order) throws MySaleException {
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			List<T_MALL_ORDER_INFO> list_info = list_flow.get(i).getList_info();
			for (int j = 0; j < list_info.size(); j++) {
				Integer kc = get_kc(list_info.get(j).getSku_id());
				if(kc>=list_info.get(j).getSku_shl()) {
					//若库存充足,直接更新订单
					Map<String,Object> map = new HashMap<>();
					map.put("sku_id", list_info.get(j).getSku_id());
					map.put("shl", list_info.get(j).getSku_shl());
					// 修改库存单元
					skuMapper.update_sku_by_skuid(map);
				}else {
					//否则抛异常，事务回滚
					throw new MySaleException("付款成功，订单出现异常，正在处理中...");
				}
				
			}
			//更新flow信息  修改包裹信息
			list_flow.get(i).setPsshj(MyDateUtil.getMyDate(1));;
			list_flow.get(i).setPsmsh("商品正在出库");
			list_flow.get(i).setYwy("小娜");
			list_flow.get(i).setLxfsh("12312312312");
			orderMapper.update_flow(list_flow.get(i));
		}
		
		//修改订单状态
		order.setJdh(3);
		order.setYjsdshj(MyDateUtil.getMyDate(3));
		orderMapper.update_order(order);
		
	}


	//获取库存数量
	private Integer get_kc(int sku_id) {
		Integer kc = 0;
		Integer sku_count = skuMapper.select_kc_count(sku_id);
		if(sku_count>0) {
			//若库存数量>10，则不用加锁查询
			kc = skuMapper.select_kc_by_skuid(sku_id);
		}else {
			//若库存<10 则加锁查询，防止saleover
			kc = skuMapper.select_kc_by_skuid_forupdate(sku_id);
		}
		return kc;
	}

}
