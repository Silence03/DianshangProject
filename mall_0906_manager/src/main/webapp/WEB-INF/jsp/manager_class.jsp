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
	一级分类<select name="flbh1" id="class_1" onchange="get_attr_class2(this.value)"></select>
	二级分类<select name="flbh2" id="class_2"></select>


<script type="text/javascript">
	$(function() {
		$.getJSON("js/json/class_1.js", function(data) {
			$.each(data, function(i, n) {
				$("#class_1").append("<option value="+n.id+">" + n.flmch1 + "</option>");
			});
			get_attr_class2($("#class_1")[0].value);
		});
	});
	function get_attr_class2(class_1_id) {
		//alert(class_1_id);

		$.getJSON("js/json/class_2_" + class_1_id + ".js", function(data) {
			$("#class_2").empty();
			$.each(data, function(i, n) {
				$("#class_2").append("<option value="+n.id+">" + n.flmch2 + "</option>");
			});
		});
	
	};
</script>
</body>
</html>