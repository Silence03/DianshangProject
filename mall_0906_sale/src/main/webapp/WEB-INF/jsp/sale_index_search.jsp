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
	<div class="search">
		<div class="logo"><img src="images/logo.jpg" alt=""></div>
		<div class="search_on">
			<div class="se">
				<form action="search.do">
					<input type="text" name="keywords" class="lf"/>
					<input type="submit" class="clik" value="搜索"/>
				</form>
			</div>
			<div class="se">
				<a href="">取暖神奇</a>
				<a href="">1元秒杀</a>
				<a href="">吹风机</a>
				<a href="">玉兰油</a>
			</div>
		</div>
		
			<jsp:include page="sale_miniCart.jsp"></jsp:include>
		
		
	</div>


	
	<br/>
</body>
</html>