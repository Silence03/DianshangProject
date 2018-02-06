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
			一级分类<select class="easyui-combobox" name="flbh1" id="cache_querylist_class_1" onchange="get_class2_attr(this.value)">
				<option>请选择</option>
			</select>
			二级分类<select class="easyui-combobox" name="flbh2" id="cache_querylist_class_2" onchange="get_attrlist_attr(this.value)">
				<option>请选择</option>
			</select>
		</div>
		<div data-options="region:'west',split:true," style="width: 100px"></div>
		<div data-options="region:'center',">
			<div id="cache_list_01"></div>
			<a href="javascript:refresh_attr_cache();" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">刷新商品属性缓存</a>
		</div>
	</div>
	
<script type="text/javascript">

	function refresh_attr_cache(){
		//1.获取选中的2级分类id和属性id
		var class_2_id = $("#cache_querylist_class_2").combobox("getValue");
		var attr_array = $("#cache_list_01").datagrid("getChecked");
		var attrs = new Array();
		$(attr_array).each(function(i,n){
			attrs[i] = n.id;
		});
		//2.异步加载刷新缓存的请求
		$.get("refresh_attr_cache.do",{class_2_id:class_2_id,attrs:attrs},function(data){
			//3.返回刷新缓存的个数
			alert("成功刷新"+data+"个缓存数据");
		});
		
	}
	
	$(function(){//一级分类
		/*  $.getJSON("js/json/class_1.js",function(data){
			$.each(data,function(i,n){
				$("#cache_querylist_class_1").append("<option value="+n.id+">"+n.flmch1+"</option>");
			});
			//get_class2($("#cache_querylist_class_1")[0].value);
		}); */ 
		 $('#cache_querylist_class_1').combobox({    
		    url:'js/json/class_1.js',    
		    valueField:'id',    
		    textField:'flmch1',
		    width:100,
		    onSelect:function get_class2_attr(){
		    	var class_1_id = $(this).combobox("getValue");
		    	$('#cache_querylist_class_2').combobox({    
				    url:'js/json/class_2_'+class_1_id+'.js',    
				    valueField:'id',    
				    textField:'flmch2',
				    width:100,
				    onSelect: function(){
				    	var class_2_id = $(this).combobox("getValue");
				    	get_attrlist_attr(class_2_id);
				    }
				 });
		    }
		});  
	});
	function get_class2_attr(class_1_id){
		//alert(class_1_id);
		//二级分类
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#cache_querylist_class_2").empty();
			$.each(data,function(i,n){
				$("#cache_querylist_class_2").append("<option value="+n.id+">"+n.flmch2+"</option>");
			});
		});
	};
	function get_attrlist_attr(class_2_id){
	/* 	$.get("get_cache_list.do",{class_2_id:class_2_id},function(data){
			$("#cache_list_01").html(data);
		}); */
		$('#cache_list_01').datagrid({    
		    url:'get_attr_list_json.do',  
		    queryParams: {
		    	class_2_id: class_2_id
			},

		    columns:[[    
		        {field:'id',title:'属性id',width:100,checkbox:true},    
		        {field:'shxm_mch',title:'属性名称',width:100},    
		        {field:'list_value',title:'属性值名称',width:150,
		        	formatter: function(list_value,row,index){
		        		var json="";
		        		$.each(list_value,function(i,n){
		        			json=json+" "+n.shxzh+n.shxzh_mch;
		        		});
		        		return json;
					}
				},   
		        {field:'chjshj',title:'创建时间',width:150,
					formatter: function(value,row,index){
						return new Date(value).toLocaleString();
					}	
		        }    
		    ]]    
		}); 
	};
</script>
</body>
</html>