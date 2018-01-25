<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>

</head>
<body>
	<h2>商品信息添加</h2>
	<form action="save_spu.do" method="post" enctype="multipart/form-data">
		一级分类<select name="flbh1" id="spu_class_1" onchange="get_spu_class2(this.value)"></select>
		二级分类<select name="flbh2" id="spu_class_2"></select>
		商品品牌<select name="pp_id" id="spu_class_3"></select><br/>
		<hr>
		商品名称<input type="text" name="shp_mch"/><br/>
		商品描述<textarea name="shp_msh"></textarea><br/>
		商品图片<br>
		<div id="button_id" style="border:1px solid black;width:100px;height:100px;float: left">
			<img id="img_0" src="image/upload_hover.png" width="100px" height="100px"  onclick="button_img_click(0)">
			<input type="file" id="file_0" name="files" onchange="button_img_change(0)" style="display:none"/><br>
		</div>
		<br><br><br><br><br>
		<input type="submit" value="提交"/>
	</form>
	
<script type="text/javascript">
	$(function(){
		$.getJSON("js/json/class_1.js",function(data){
			$.each(data,function(i,n){
				$("#spu_class_1").append("<option value="+n.id+">"+n.flmch1+"</option>");
			});
			get_spu_class2($("#spu_class_1")[0].value);
		});
	});
	function get_spu_class2(class_1_id){
		//alert(class_1_id);
		
		$.getJSON("js/json/class_2_"+class_1_id+".js",function(data){
			$("#spu_class_2").empty();
			$.each(data,function(i,n){
				$("#spu_class_2").append("<option value="+n.id+">"+n.flmch2+"</option>");
			});
		});
		$.getJSON("js/json/tm_class_1_"+class_1_id+".js",function(data){
			$("#spu_class_3").empty();
			$.each(data,function(i,n){
				$("#spu_class_3").append("<option value="+n.id+">"+n.ppmch+"</option>");
			});
		});
	};
	function button_img_click(index){
		$("#file_"+index).click();

	}
	function button_img_change(index){
		// 获得图片缓存，转化为url对象，替换image的src属性
		var file = $("#file_"+index)[0].files[0];
		var url = window.URL.createObjectURL(file);
		$("#img_"+index).attr("src",url);
		//如果用户选择了最后一张图片
		var length = $("#button_id :file").length;
		if(length==(index+1)){
			add_img(index);
		}
	};
	//上传一张图片后追加一张点击按钮图片
	function add_img(index){
		var a = '<img id="img_'+(index+1)+'" src="image/upload_hover.png" width="100px" height="100px" onclick="button_img_click('+(index+1)+')">';
		var b = '<input type="file" id="file_'+(index+1)+'" name="files" onchange="button_img_change('+(index+1)+')" style="display:none"/><br>';
		$("#button_id").append(a+b);
	};

</script>	
</body>
</html>