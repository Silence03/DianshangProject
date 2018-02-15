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
	<table class="table table-striped table-bordered table-hover">
		   <thead>
		     <tr>
		       <th>商品图片</th>
		       <th>商品名称</th>
		       <th>商品属性</th>
		       <th>商品价格</th>
		       <th>商品数量</th>
		       <th>操作</th>
		     </tr>
		   </thead>
		   <tbody>
			   <c:forEach items="${list_cart }" var="cart">
				     <tr>
				       <td><img src="upload/image/${cart.shp_tp }" alt="" width="70px"></td>
				       <td><input type="checkbox" onclick="check_item(${cart.sku_id},checked)"  ${cart.shfxz=="1"?"checked":"" }/> ${cart.sku_mch }</td>
				       <td>
				       		颜色：<span style='color:#ccc'>白色</span><br>
				       		尺码：<span style='color:#ccc'>L</span>
				       </td>
				       <td>￥${cart.hj }</td>
				       <td><input type="text" name="min" value="${cart.tjshl }" style="width:50px;text-align:center"></td>
				       <td>删除</td>
				     </tr>
			  </c:forEach>
		  
		   </tbody>
	 	</table>
	 <div class="Cprice">
		<div class="price">总价：￥${sum }</div>
		<div class="jiesuan">
			<form action="goto_checkout.do" method="post">
				<input type="hidden" name="sum" value="${sum }"/>
				<input type="submit" value="去结算"/>
			</form>
		</div>
	 </div>

	
</body>
</html>