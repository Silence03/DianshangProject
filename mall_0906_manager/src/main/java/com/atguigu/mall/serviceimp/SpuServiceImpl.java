package com.atguigu.mall.serviceimp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mall.bean.T_MALL_PRODUCT;
import com.atguigu.mall.mapper.SpuMapper;
import com.atguigu.mall.service.SpuService;
@Service
public class SpuServiceImpl implements SpuService {
	@Autowired
	SpuMapper spuMapper;

	@Override
	public void saveSpuProduct(T_MALL_PRODUCT tmp,List<String> list_img) {
		tmp.setShp_tp(list_img.get(0));
		//保存商品信息返回主键值
		spuMapper.insertSpuProduct(tmp);
		
		//保存商品图片信息
		Map<String,Object> parammap = new HashMap<>();
		parammap.put("spu_id", tmp.getId());
		parammap.put("list_img", list_img);
		spuMapper.insertSpu_tp(parammap);
	}

	//查询商品信息
	@Override
	public List<T_MALL_PRODUCT> get_spu(Integer flbh2, Integer pp_id) {
		Map<String,Object> map = new HashMap<>();
		map.put("flbh2", flbh2);
		map.put("pp_id", pp_id);
		return spuMapper.selectshpxx(map);
	}

}
