package com.atguigu.mall.sale.bean;

import java.io.Serializable;
import java.util.List;

public class MODEL_OBJECT_T_MALL_ATTR  implements Serializable {
	List<OBJECT_T_MALL_ATTR> list_attr;

	public List<OBJECT_T_MALL_ATTR> getList_attr() {
		return list_attr;
	}

	public void setList_attr(List<OBJECT_T_MALL_ATTR> list_attr) {
		this.list_attr = list_attr;
	}
	
}
