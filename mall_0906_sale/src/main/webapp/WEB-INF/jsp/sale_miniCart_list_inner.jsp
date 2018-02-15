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
	<div class="cart_pro">
				<h6>最新加入的商品</h6>
			<c:forEach items="${list_cart }" var="cart">
				<div class="one">
					<img src="upload/image/${cart.shp_tp }" width="70px"/>
					<span class="one_name">
						${cart.sku_mch }
					</span>
					<span class="one_prece">
						<b>￥${cart.hj }</b><br />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;删除
					</span>
				</div>
			</c:forEach>	
				<div class="gobottom">
					共<span>${cart_count }</span>件商品&nbsp;&nbsp;&nbsp;&nbsp;
					共计￥<span>${sum }</span>
					<button class="goprice">去购物车</button>
				</div>
		</div>


</body>
</html>