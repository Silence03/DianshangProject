package com.atguigu.mall.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyUtils {

	// 保存图片并返回图片名称集合
	public static List<String> uploadimg(MultipartFile[] files) {
		String path = PropertiyUtil.getPropertity("upload.properties", "window_path");
		List<String> list = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {

				String filename = System.currentTimeMillis() + files[i].getOriginalFilename();
				try {
					files[i].transferTo(new File(path + File.separator + filename));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				list.add(filename);
			}
		}
		return list;
	}

}
