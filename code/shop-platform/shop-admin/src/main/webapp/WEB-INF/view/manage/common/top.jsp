<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
	contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>

<script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>';
var global_userid = '<%= session.getAttribute("adminid")%>';
if(global_userid == null || global_userid == 'null'){
	top.location = contextPath + '/manage/login.html';
}
</script>
<!-- JQuery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jquery.js"></script>

<!-- jQuery.MD5 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jQuery.md5.js"></script>
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jquery.showLoading.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/bootstrap.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/bootstrap.js"></script>

<!--  日期组件 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/datetimepicker.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- 页面验证 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jquery.validate.js"></script>

<!-- 常用js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/common.js"></script>

<!-- 常用样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/style.css" />
	
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/showLoading.css" />
	
<style type="text/css">
  span.hide.pagenum{
  color : red;
    	}
</style>
</head>
<body>
