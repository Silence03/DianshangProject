<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Insert title here</title>
</head>
<body>
	<h3>商品库存属性选择</h3>
	<c:forEach items="${attrlist }" var="attr" varStatus="status">
		<input type="checkbox" name="list_sku_av[${status.index }].shxm_id" value="${attr.id}" onclick="show_value(${attr.id },this.checked)"/> ${attr.shxm_mch}
	</c:forEach>
	<hr>
	<c:forEach items="${attrlist }" var="attr"  varStatus="status">
		<div id="attr_${attr.id }" style="display:none">
			${attr.shxm_mch}:
			<c:forEach items="${attr.list_value }" var="val">
				<input type="radio" name="list_sku_av[${status.index}].shxzh_id" value="${val.id}" id="redio_${val.id }"/> ${val.shxzh }${val.shxzh_mch }
			</c:forEach>
			<br/>
		</div>
	</c:forEach>
	
<script type="text/javascript">
	function show_value(attrid,flag){
		if(flag){
			$("#attr_"+attrid).show();
		}else{
			//$("input[name="+attrname+"]").removeAttr("checked"); 
			$("#attr_"+attrid).hide();
		}
	};
</script>
</body>
</html>