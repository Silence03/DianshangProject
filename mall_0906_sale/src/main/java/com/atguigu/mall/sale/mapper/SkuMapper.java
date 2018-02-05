package com.atguigu.mall.sale.mapper;

import java.util.Map;

public interface SkuMapper {

	Integer select_kc_by_skuid(int sku_id);
	Integer select_kc_by_skuid_forupdate(int sku_id);
	Integer select_kc_count(int sku_id);
	void update_sku_by_skuid(Map<String, Object> map);

}
