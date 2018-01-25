<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>

</head>
<body>
	<h2>商品库存信息添加</h2>
	<form action="save_sku.do" method="post" enctype="multipart/form-data">
		一级分类<select name="flbh1" id="sku_class_1" onchange="get_class2(this.value)"></select>
		二级分类<select name="flbh2" id="sku_class_2" onchange="get_attrlist(this.value)"></select>
		商品品牌<select name="pp_id" id="sku_tm" onchange="get_spu()"></select>
		商品信息<select name="shp_id" id="sku_spu" onchange="show_sku_form()"></select><br/>
		<div id="attr_list"></div>
		<div id="sku_form"></div>
		<input type="submit" value="提交"/>
	</form>
	
<script type="text/javascript">
	$(function(){//一级分类
		$.getJSON("js/json/class_1.js",function(data){
			$.each(data,function(i,n){
				$("#sku_class_1").append("<option value="+n.id+">"+n.flmch1+"</option>");
			});
			//get_class2($("#sku_class_1")[0].value);
		});
	});
	function get_class2(class_1_id){
		//alert(class_1_id);
		//二级分类
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#sku_class_2").empty();
			$.each(data,function(i,n){
				$("#sku_class_2").append("<option value="+n.id+">"+n.flmch2+"</option>");
			});
		});
		//商品品牌
		$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
			$("#sku_tm").empty();
			$.each(data,function(i,n){
				$("#sku_tm").append("<option value="+n.id+">"+n.ppmch+"</option>");
			});
		});
	};
	function get_attrlist(class_2_id){
		$.get("get_attrlist.do",{class_2_id:class_2_id},function(data){
			$("#attr_list").html(data);
		});
		
	};
	//商品信息
	function get_spu(){
		var flbh2 = $("#sku_class_2").val();
		var pp_id = $("#sku_tm").val();
		$.get("get_spu.do",{flbh2:flbh2,pp_id:pp_id},function(data){
			$("#sku_spu").empty();
			$.each(data,function(i,n){
				$("#sku_spu").append("<option value="+n.id+">"+n.shp_mch+"</option>");
			});
		});
	}
	//显示sku
	function show_sku_form(){
		$.get("show_sku_form.do",{},function(data){
			$("#sku_form").html(data);
		});
	}

</script>
</body>
</html>