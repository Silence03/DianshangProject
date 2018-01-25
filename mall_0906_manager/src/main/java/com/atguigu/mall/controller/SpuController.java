package com.atguigu.mall.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.mall.bean.T_MALL_PRODUCT;
import com.atguigu.mall.service.SpuService;
import com.atguigu.mall.utils.MyUtils;

@Controller
public class SpuController {
	@Autowired
	SpuService spuService;
	
	@RequestMapping("/manager_spu")
	public String manager_spu() {
		return "manager_spu";
	}
	@RequestMapping("/go_spu_add")
	public String go_spu_add() {
		return "manager_spu_add";
	}
	@RequestMapping("/save_spu")
	public String save_spu(T_MALL_PRODUCT tmp,@RequestParam("files") MultipartFile[] files)  {
		System.out.println(tmp);
		//使用工具类上传图片返回图片名称
		List<String> list_img = MyUtils.uploadimg(files);
		spuService.saveSpuProduct(tmp,list_img);
		return "redirect:/go_spu_add.do";
	}
}
