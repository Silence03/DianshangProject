package com.atguigu.mall.serviceimp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mall.bean.T_MALL_VALUE;
import com.atguigu.mall.mapper.AttrMapper;
import com.atguigu.mall.service.AttrService;
@Service
public class AttrServiceImpl implements AttrService {
	@Autowired
	AttrMapper attrMapper;

	@Override
	public void saveattr(Integer flbh2,List<OBJECT_T_MALL_ATTR> list_attr2) {
		for (OBJECT_T_MALL_ATTR attr : list_attr2) {
			
			//增加attr属性表数据
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("flbh2", flbh2);
			map.put("attr", attr);
			attrMapper.insert_attr(map);
			
			//增加value属性值表数据
			Map<String, Object> map2 = new HashMap<String, Object>();
			List<T_MALL_VALUE> list_value = attr.getList_value();
			List<T_MALL_VALUE> attrvalue = new ArrayList<T_MALL_VALUE>();
			//判断属性名和属性值是否为空
			for (T_MALL_VALUE mallvalue : list_value) {
				if(mallvalue.getShxzh()!=null && mallvalue.getShxzh_mch()!=null) {
					attrvalue.add(mallvalue);
				}
			}
			map2.put("attrvalue", attrvalue);
			map2.put("shxm_id", attr.getId());
			attrMapper.insert_arrt_value(map2);
		
		}
		
		
	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attrlist(Integer class_2_id) {
		
		return attrMapper.selectattrlist(class_2_id);
	}

	@Override
	public List<Integer> getValueIdByAttrId(int attr_id) {
		// TODO Auto-generated method stub
		return attrMapper.selectValueIdByAttrId(attr_id);
	}

}
