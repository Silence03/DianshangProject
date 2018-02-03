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
	<c:forEach items="${list_cart }" var="cart">
		<img alt="" src="upload/image/${cart.shp_tp }" width="70px"> 
		&nbsp;&nbsp;<input type="checkbox" onclick="check_item(${cart.sku_id},checked)"  ${cart.shfxz=="1"?"checked":"" }/> ${cart.sku_mch }
		&nbsp;&nbsp;${cart.tjshl }
		&nbsp;&nbsp;${cart.hj }
		<br/>
	</c:forEach>
	总金额：￥${sum }
	<hr/>
	<form action="goto_checkout.do" method="post">
		<input type="hidden" name="sum" value="${sum }"/>
		<input type="submit" value="去结算"/>
	</form>
</body>
</html>