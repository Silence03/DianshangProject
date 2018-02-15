<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/css.css"/>
<link rel="stylesheet" type="text/css" href="css/finishCart.css"/>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	
</script>
<title>Insert title here</title>
</head>
<body>
	
	<jsp:include page="sale_header.jsp"></jsp:include>
	<jsp:include page="sale_index_search.jsp"></jsp:include>
	
	<div class="cartPro">
			<span class="dec">该商品已加入购物车</span>
			<div class="lec">
				<a href="###"><img src="upload/image/${cart.shp_tp }"/></a>
			</div>
			<span class="lec_name">
				${cart.sku_mch }
			</span>
			<span class="lec_des">
				数量1
			</span>
			<div class="shop_des">
				<a href="###"><img src="images/shop_des.png"/></a>
			</div>
			<div class="shop_cart">
				<a href="goto_cart_list.do"><img src="images/shop_cart.png"/></a>
			</div>
		</div>
</body>
</html>