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
	function check_item(sku_id,checked){
		var shfxz="0";
		if(checked){
			shfxz="1";
		}
		$.get("change_cart_status.do",{sku_id:sku_id,shfxz:shfxz},function(data){
			$("#sale_cart_list_inner").html(data);
		});
	}
</script>
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">购物车列表</h2>
	<div id="sale_cart_list_inner">
		<jsp:include page="sale_cart_list_inner.jsp"></jsp:include>
	</div>
</body>
</html>