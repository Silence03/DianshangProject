<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){//一级分类
	$.getJSON("js/json/class_1.js",function(data){
		$.each(data,function(i,n){
			$("#sale_index_class_1").append("<li value="+n.id+"  onmouseover=get_class2("+n.id+")>"+n.flmch1+"</li>");
		});
		//get_class2($("#sale_index_class_1")[0].value);
	});
});
function get_class2(class_1_id){
	//alert(class_1_id);
	//二级分类
	$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
		$("#sale_index_class_2").empty();
		$.each(data,function(i,n){
			$("#sale_index_class_2").append("<li value="+n.id+"><a href='goto_attr_list.do?class_2_id="+n.id+"'>"+n.flmch2+"</a></li>");
		});
	});
}
</script>
<title>Insert title here</title>
</head>
<body>
	<ul name="flbh1" id="sale_index_class_1" style="width:70px;float: left;"></ul>
	<ul name="flbh2" id="sale_index_class_2" style="width:80px;float: left;"></ul>
	
</body>
</html>