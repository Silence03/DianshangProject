<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/css.css">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var yh_nch = getcook("yh_nch");
		 $("#yh_nch").text(yh_nch);
	});
	function getcook(key){
		var val = " ";
		var cookies1 = document.cookie;
		 var cookies = cookies1.replace(" ","");
		 var cookies2 = cookies.split(";");
		  for(i = 0;i<cookies2.length;i++){
			 var cookies3 = cookies2[i].split("=");
			 if(cookies3[0] == key){
				 val=cookies3[1];
			 }
		 } 
		  return decodeURIComponent(val);
	}
</script>
<title>商城首页</title>
</head>
<body>
	<div class="top">
		<div class="top_text">
			<c:if test="${empty user }">
				<span id="yh_nch">&nbsp;</span><a href="goto_sale_login.do">请登录</a>&nbsp;&nbsp;&nbsp;  <a href="goto_regist.do">免费注册</a>
			</c:if>
			<c:if test="${not empty user }">
				<a >${user.yh_nch }，欢迎回来</a>&nbsp;&nbsp;&nbsp;<a href="#">我的订单</a>&nbsp;&nbsp;&nbsp;<a href="goto_logout.do">退出</a>
			</c:if>
		</div>
	</div>

	
</body>
</html>