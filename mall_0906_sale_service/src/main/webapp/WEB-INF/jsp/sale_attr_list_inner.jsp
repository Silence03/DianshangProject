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
	//排序
	function get_sku_order(order){
		var old_order = $("#order_param").val();
		if(old_order==order){
			$("#order_param").val(order+"desc");
		}else{
			$("#order_param").val(order);
		}
		
		
		get_sku_list_json();
	}
	//按照属性筛选
	function get_param_up(shxm_id,shxzh_id,shxzhmch){
		//$("#attr_param").append("<input type='text' name='attr_param' value="+shxm_id+"_"+shxzh_id+" />"+shxzhmch+"");
		$("#attr_param").append("<input  type='text' name='attr_param' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}' />"+shxzhmch+"&nbsp;&nbsp;");
		
		get_sku_list_json();
	}
	function get_sku_list_json(){
		var class_2_id = "${class_2_id}";
		var inputs = $("#attr_param input[name='attr_param']");
		var attr_param="class_2_id="+class_2_id;
		$.each(inputs,function(i,n){
			var json = jQuery.parseJSON(n.value);
			attr_param=attr_param+"&list_sku_av["+i+"].shxm_id="+json.shxm_id+"&list_sku_av["+i+"].shxzh_id="+json.shxzh_id;
		});
		
		attr_param = attr_param + "&order="+$("#order_param").val();
		
		$.get("get_sku_list_by_attr.do",attr_param,function(data){
			$("#sku_list_inner").html(data);
		}); 
	};
	function get_sku_list_array(){
		var class_2_id = "${class_2_id}";
		var inputs = $("#attr_param input[name='attr_param']");
		var param = new Array();
		$.each(inputs,function(i,n){
			param[i]=n.value;
		});
		
		$.get("get_sku_list_by_attr.do",{
			class_2_id:class_2_id,
			attr_param:param
		},function(data){
			$("#sku_list_inner").html(data);
		}); 
	};
</script>
<title>Insert title here</title>
</head>
<body>
	<div class="Sscreen">
		<div class="title">
			平板电视 商品筛选 共1205个商品
		</div>
		<div id="attr_param"></div>
		<input id="order_param" type="text" value=" order by jg"/>
		<c:forEach items="${attrlist }" var="attr"  varStatus="status">
			<div id="attr_${attr.id }" class="list">
				<span>${attr.shxm_mch}:</span>
				<c:forEach items="${attr.list_value }" var="val">
					 <a href="javascript:get_param_up(${attr.id },${val.id },'${val.shxzh }${val.shxzh_mch }');">${val.shxzh }${val.shxzh_mch }</a>
				</c:forEach>
				<br/>
			</div>
		</c:forEach>
		
		<div class="list">
			<span class="list_span" id="list_beas"><a href="javascript:get_sku_order(' order by sku_xl ');">销量</a></span>
			<span class="list_span"><a href="javascript:get_sku_order(' order by jg ');">价格</a></span>
			<span class="list_span"><a href="javascript:get_sku_order(' order by sku_xl ');">评论数</a></span>
			<span class="list_span"><a href="javascript:get_sku_order(' order by sku.chjshj ');">上架时间</a></span>
		</div>
	</div>



</body>
</html>