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
	<c:forEach items="${attrlist }" var="attr"  varStatus="status">
		<div id="attr_${attr.id }" >
			${attr.shxm_mch}:
			<c:forEach items="${attr.list_value }" var="val">
				 ${val.shxzh }${val.shxzh_mch }
			</c:forEach>
			<br/>
		</div>
	</c:forEach>
</body>
</html>