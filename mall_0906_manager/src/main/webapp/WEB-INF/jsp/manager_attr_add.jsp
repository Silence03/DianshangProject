<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>

<title>Insert title here</title>
</head>
<body>
	<h2>商品属性添加</h2>
	
	<form action="save_attr.do" method="post">
		 <jsp:include page="manager_class.jsp"></jsp:include> 
		
		<hr>
		<table id="attrtab" border="1px">
			<tr>
				<td>属性名:<input type="text" name="list_attr[0].shxm_mch"/></td>
				<td></td>
				<td><a onclick="add_attr_btn()">添加值</a></td>
			</tr>
			<tr id="tr_1" style="" >
				<td>属性值:<input type="text" name="list_attr[0].list_value[0].shxzh"/></td>
				<td>单位:<input type="text" name="list_attr[0].list_value[0].shxzh_mch"/></td>
				<td onclick="del_attr_btn(1)"><a>删除</a></td> 
				<!-- <td ><a onclick="this.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode)">删除</a></td>
			 --></tr>
	
		</table>
		<input type="submit" value="提交"/>
	</form>	
	
<script type="text/javascript">


	//添加属性值单元格
	function add_attr_btn(){
		var index = $("#attrtab tr").length-1;
		//alert(index);
		var common="";
		common+='<tr id="tr_'+(index+1)+'" style="">';
		common+='	<td>属性值:<input type="text" name="list_attr[0].list_value['+index+'].shxzh"/></td>';
		common+='	<td>单位:<input type="text" name="list_attr[0].list_value['+index+'].shxzh_mch"/></td>';
		common+='	<td><a onclick="del_attr_btn('+(index+1)+')">删除</a></td>';
		common+='</tr>';
		$("#attrtab").append(common);
	};
	//删除添加属性值单元格
	function del_attr_btn(index){
		$("#tr_"+index).empty();
		$("#tr_"+index).attr("style",'display:none');
		//alert(index);
	};
</script>
</body>
</html>