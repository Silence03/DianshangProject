package com.atguigu.mall.serviceimp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.bean.OBJECT_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.bean.T_MALL_SKU;
import com.atguigu.mall.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.mapper.SkuMapper;
import com.atguigu.mall.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService{

	@Autowired
	SkuMapper skuMapper;
	
	@Override
	public void save_sku(T_MALL_SKU sku, OBJECT_T_MALL_SKU_ATTR_VALUE list_sku_av) {
		//插入sku表信息数据
		skuMapper.insert_sku(sku);
		//插入商品库存信息数据
		List<T_MALL_SKU_ATTR_VALUE> list_sku_av2 = list_sku_av.getList_sku_av();
		List<T_MALL_SKU_ATTR_VALUE> sku_av_list = new ArrayList<>();
		for (T_MALL_SKU_ATTR_VALUE sku_av : list_sku_av2) {
			if(sku_av.getShxm_id()!=null) {
				sku_av_list.add(sku_av);
			}
		}
		Map<String,Object> map = new HashMap<>();
		map.put("shp_id", sku.getShp_id());
		map.put("sku_id", sku.getId());
		map.put("sku_av_list", sku_av_list);
		skuMapper.insert_sku_av(map);
		
	}

}
