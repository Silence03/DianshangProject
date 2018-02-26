package com.atguigu.mall.log.mapper;

import java.util.Map;

public interface LogMapper {

	void insertLoginout(Map<String, Object> map);

	void insertKeyWords(Map<String, Object> map);

	void insertOrderLog(Map<String, Object> map);

}
