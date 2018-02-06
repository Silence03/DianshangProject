<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css">

<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
<title>Insert title here</title>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height: 60px; background: #B3DFDA; padding: 10px">
		<h2>后台管理首页</h2>
	</div>
	<div data-options="region:'west',split:true,title:'商品管理'" style="width: 210px; padding: 10px;">
		<div class="easyui-accordion" style="width:200px;height:300px;">
			<div title="商品管理" data-options="iconCls:'icon-search'" style="padding:10px;">
				<ul class="easyui-tree">
					<li>
						<span>商品管理</span>
						<ul>
							<li >
								<span>商品信息管理</span>
								<ul>
									<li>商品信息查询</li>
									<li><a href="javascript:add_tab('go_spu_add.do','商品信息添加');">商品信息添加</a></li>
								</ul>
							</li>
						</ul>
						<ul>
							<li >
								<span>商品属性管理</span>
								<ul>
									<li><a href="javascript:add_tab('goto_attr_list.do','商品属性查询');">商品属性查询</a></li>
									<li><a href="javascript:add_tab('manager_attr_add.do','商品属性添加');">商品属性添加</a></li>
								</ul>
							</li>
						</ul>
						<ul>
							<li >
								<span>商品库存单元管理</span>
								<ul>
									<li>商品库存单元查询</li>
									<li><a href="javascript:add_tab('go_sku_add.do','商品库存单元添加');">商品库存单元添加</a></li>
								</ul>
							</li>
						</ul>
					</li>
				</ul>
			</div>
			<div title="缓存管理"  style="overflow:auto;padding:10px;">
				<ul class="easyui-tree">
					<li>
						<span>缓存管理</span>
							<ul>
								<li><a href="javascript:add_tab('go_cache.do','商品属性缓存刷新');">商品缓存管理</a></li>
							</ul>
					</li>
				</ul>
			</div>
			<div title="订单管理"  style="overflow:auto;padding:10px;"></div>
		</div>
		<!-- <a href="manager_spu.do">商品信息管理</a><br/>
		<a href="manager_attr.do">商品属性管理</a><br/>
		<a href="manager_sku.do">商品库存单元管理</a> -->
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'East'" style="width: 100px; padding: 10px;">
		east region
		</div>
	<div data-options="region:'south',border:false" style="height: 50px; background: #A9FACD; padding: 10px;">
		south region
	</div>
	<div data-options="region:'center',title:'Center'" >
		<div id="tt" class="easyui-tabs" style="height:500px"></div>
	</div>


<script type="text/javascript">
	$(function(){
		var url = "${url}";
		var title = "${title}";
		if(url!=""){
			add_tab(url,title);
		}
		
	});
	
	function add_tab(url,title){
		var flag = $('#tt').tabs('exists',title);
		if(flag){
			$('#tt').tabs('select',title);
		}else{
			$('#tt').tabs('add',{    
			    title:title,    
			    href:url,    
			    closable:true,    
			    tools:[{    
			        iconCls:'icon-mini-refresh',    
			        handler:function(){    
			            alert('refresh');    
			        }    
			    }]    
			});
		}
	
	}
</script>
</body>
</html>