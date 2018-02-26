package com.atguigu.mall.sale.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.sale.bean.DETAIL_T_MALL_SKU;
import com.atguigu.mall.sale.bean.OBJECCT_T_MALL_SKU;
import com.atguigu.mall.sale.bean.T_MALL_SKU;
import com.atguigu.mall.sale.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mall.sale.mapper.SkuListMapper;
import com.atguigu.mall.sale.service.SkuListService;

@Service
public class SkuListServiceImpl implements SkuListService {
	@Autowired
	SkuListMapper skuListMapper;

	@Override
	public List<OBJECCT_T_MALL_SKU> get_skulist(Integer class_2_id) {

		return skuListMapper.select_skulist_by_class_2_id(class_2_id);
	}

	// 点击属性检索商品
	@Override
	public List<OBJECCT_T_MALL_SKU> select_skulist_by_attr(Integer class_2_id,
			List<T_MALL_SKU_ATTR_VALUE> list_sku_av,String order) {
		Map<String, Object> map = new HashMap<>();
		// 动态拼接sql字符串
		StringBuffer sql = new StringBuffer();
		if(list_sku_av==null || list_sku_av.size()==0) {
			map.put("sql", "");
		}else {
			if (list_sku_av!=null && list_sku_av.size() == 1) {
				
				sql.append(
						"AND sku.id IN(SELECT sku_id FROM t_mall_sku_attr_value WHERE t_mall_sku_attr_value.shxm_id="+list_sku_av.get(0).getShxm_id()+" AND t_mall_sku_attr_value.shxzh_id="+list_sku_av.get(0).getShxzh_id()+")");
			}
			if(list_sku_av!=null && list_sku_av.size() > 1) {
				sql.append(" AND sku.id IN( " + " SELECT sku0.sku_id FROM ");
				for (int i = 0; i < list_sku_av.size(); i++) {
					sql.append(" ( SELECT sku_id FROM t_mall_sku_attr_value WHERE t_mall_sku_attr_value.shxm_id="+list_sku_av.get(i).getShxm_id()+" AND t_mall_sku_attr_value.shxzh_id="+list_sku_av.get(i).getShxzh_id()+") sku"+i+"");
					if(list_sku_av.size()-1!=i) {
						sql.append(",");
					}
				}
				sql.append(" WHERE ");
				for (int i = 0; i < list_sku_av.size()-1; i++) {
					sql.append(" sku"+i+".sku_id=sku"+(i+1)+".sku_id ");
					if(list_sku_av.size()-2!=i) {
						sql.append(" and ");
					}
				}
				sql.append(" )");
			}
			map.put("sql", sql.toString());
		}
		
		
		map.put("class_2_id", class_2_id);
		map.put("order", order);
		List<OBJECCT_T_MALL_SKU> list_sku = skuListMapper.select_skulist_by_attr(map);
		return list_sku;
	}

	@Override
	public DETAIL_T_MALL_SKU get_sku_detail(Integer sku_id, Integer spu_id) {
		Map<String,Object> map = new HashMap<>();
		map.put("sku_id", sku_id);
		map.put("spu_id", spu_id);
		DETAIL_T_MALL_SKU sku_detail = skuListMapper.select_sku_detail(map);
		return sku_detail;
	}

	@Override
	public List<T_MALL_SKU> get_sku_list_by_spu_id(Integer spu_id) {
		Map<String,Object> map = new HashMap<>();
		map.put("spu_id", spu_id);
		return skuListMapper.select_sku_list_by_spu_id(map);
	}

}
