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
	
</script>
<title>Insert title here</title>
</head>
<body>
		<div class="easyui-layout" data-options="fit:true">
		<h2>商品属性查询</h2>
		<div data-options="region:'north',split:true," style="height: 50px">
			一级分类<select class="easyui-combobox" name="flbh1" id="keywords_querylist_class_1" onchange="get_class2_attrlist(this.value)">
				<option>请选择</option>
			</select>
			
		</div>
		<div data-options="region:'west',split:true," style="width: 100px"></div>
		<div data-options="region:'center',">
			<div id="keywords_list_01"></div>
			<a href="javascript:refresh_keywords_cache();" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新关键字检索缓存</a>
		</div>
	</div>
	
<script type="text/javascript">

	function refresh_keywords_cache(){
		//1.获取选中的2级分类id和属性id
		//var class_2_id = $("#keywords_querylist_class_2").combobox("getValue");
		var keywords_array = $("#keywords_list_01").datagrid("getChecked");
		var keywords = new Array();
		$(keywords_array).each(function(i,n){
			keywords[i] = n.id;
		});
		//2.异步加载刷新缓存的请求
		$.get("refresh_keywords_cache.do",{keywords:keywords},function(data){
			//3.返回刷新缓存的个数
			alert("成功刷新"+data+"个关键字检索缓存数据");
		});
		
	}
	
	$(function(){//一级分类
		/*  $.getJSON("js/json/class_1.js",function(data){
			$.each(data,function(i,n){
				$("#keywords_querylist_class_1").append("<option value="+n.id+">"+n.flmch1+"</option>");
			});
			//get_class2($("#keywords_querylist_class_1")[0].value);
		}); */ 
		 $('#keywords_querylist_class_1').combobox({    
		    url:'js/json/class_1.js',    
		    valueField:'id',    
		    textField:'flmch1',
		    width:100,
		    onSelect:function get_class2_attrlist(){
		    	var class_1_id = $(this).combobox("getValue");
		    	get_attrlist_attr(class_1_id);
		    }
		});  
	});
	function get_class2_attrlist(class_1_id){
		get_attrlist_attr(class_1_id);
	};
	function get_attrlist_attr(class_1_id){
	/* 	$.get("get_keywords_list.do",{class_2_id:class_2_id},function(data){
			$("#keywords_list_01").html(data);
		}); */
		$('#keywords_list_01').datagrid({    
		    url:'js/json/class_2_'+class_1_id+'.js',  


		    columns:[[    
		        {field:'id',title:'属性id',width:100,checkbox:true},    
		        {field:'flmch2',title:'2级分类名称',width:100},    
		        {field:'flbh1',title:'分类编号1',width:150},   
		           
		    ]]    
		}); 
	};
</script>
</body>
</html>