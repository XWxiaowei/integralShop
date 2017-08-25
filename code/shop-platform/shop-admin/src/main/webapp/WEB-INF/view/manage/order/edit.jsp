<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
	contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="mytaglibs" prefix="my"%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- JQuery -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jquery.js"></script>

<!-- jQuery.MD5 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jQuery.md5.js"></script>

<!-- bootstrap -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/bootstrap.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/bootstrap.js"></script>

<!-- 页面验证 -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/jquery.validate.js"></script>

<!-- 常用js -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/manage/Js/common.js"></script>

<!-- 常用样式 -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/manage/Css/style.css" />
<style type="text/css">
.content {
	margin: 30px 0 0 30px;
}
table.content {
	margin: 30px 0 0 30px;
	border-collapse: separate;
	border-spacing: 15px;
}

.ticket {
	border-top: 1px solid #000;
	margin: 10px 0px 0px 30px;
}

table.ticket {
	border-collapse: separate;
	border-spacing: 15px;
}
.field {
	padding-left: 50px;
}
</style>
</head>
<body>
	<table class="content">
		<tr>
			<td>订单号：</td>	
			<td>${shopOrder.orderId }</td>
			<td nowrap class="field">订单状态:</td>
			<td>
				<c:forEach items="${orderStatusMap}" var="orderStatusItem">
					<c:if test="${orderStatusItem.key==shopOrder.orderStatus}">${orderStatusItem.value}</c:if>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>下单时间：</td>
			<td><fmt:formatDate value="${shopOrder.createTime}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
			<td nowrap class="field">支付时间：</td>
			<td><fmt:formatDate value="${shopOrder.payTime}" pattern="yyyy.MM.dd HH:mm:ss"/></td>
		</tr>
		<tr>
			<td>买家手机号:</td>
			<td>${mallUser.userPhone}</td>
		</tr>
		<tr>
			<td>收货人:</td>
			<td>${mallAddress.receiverName}</td>
		</tr>
		<tr>
			<td>收货手机号：</td>
			<td>${mallAddress.receiverPhone}</td>
			<td></td>
		</tr>
		<c:if test="${platformId == 1002}">
		<tr>
			<td valign="top">收货地址：</td>
			<td style="max-width:300px;">${mallAddress.province} ${mallAddress.city} ${mallAddress.area} ${mallAddress.receiverAddress}</td>
			<td></td>
		</tr>
		<tr>
			<td valign="top">发票类型：</td>
			<td style="max-width:300px;">
				<c:forEach items="${invoiceTypeMap}" var="invoiceTypeItem">
					<c:if test="${invoiceTypeItem.key==mallInvoice.type}">${invoiceTypeItem.value}</c:if>
				</c:forEach>
			</td>
			<td></td>
		</tr>
		<tr>
			<td valign="top">发票抬头：</td>
			<td style="max-width:300px;">${mallInvoice.title}</td>
			<td></td>
		</tr>
		<tr>
			<td valign="top">物流公司：</td>
			<td style="max-width:300px;">
				<c:forEach items="${transCompanyMap}" var="transCompanyItem">
					<c:if test="${transCompanyItem.key==shopOrder.transCompany}">${transCompanyItem.value}</c:if>
				</c:forEach>
			</td>
			<td></td>
		</tr>
		<tr>
			<td valign="top">物流编号：</td>
			<td style="max-width:300px;">${shopOrder.transport}</td>
			<td></td>
		</tr>
		</c:if>
		<tr>
			<td>商家名称:</td>
			<td>${mallMerchant.merchantName}</td>
		</tr>
		<c:if test="${platformId == 1002}">
		<tr>
			<td>产品编码:</td>
			<td>${mallProduct.couponId}</td>
		</tr>
		</c:if>
		<tr>
			<td colspan="1" rowspan="2" width="100px" valign="top">
			<img src="<%=request.getContextPath()%>/manage/tab2/product/showImage.html?filePath=${mallProduct.logoPicture}" width="100px">
			</td>
			<td>${mallProduct.productName}</td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
           	<td></td>
           	<td></td>
		</tr>
		<tr>
		<c:if test="${mallProduct.virtualCoin != null}">
		  <td colspan="2"><label>单价：
		  		<c:if test="${platformId == 1002}"><fmt:formatNumber type="number" value="${mallProduct.price/100}" pattern="0.00" maxFractionDigits="2"/>元</c:if>
		  		<c:if test="${platformId == 1001 && mallProduct.virtualCoin != null}">${mallProduct.virtualCoin}积分</c:if>
		  		<c:if test="${platformId == 1002 && mallProduct.virtualCoin != null}">+${mallProduct.virtualCoin}和米</c:if>
		      </label></td>
		  <td colspan="2">购买数量：${shopOrder.count}<c:if test="${platformId == 1001}">张</c:if></td>
	      <td colspan="2"><label>总价：
	      		<c:if test="${platformId == 1002}"><fmt:formatNumber type="number" value="${shopOrder.price/100}" pattern="0.00" maxFractionDigits="2"/>元</c:if>
	            <c:if test="${platformId == 1002 && shopOrder.hemiPrice != null}">+${shopOrder.hemiPrice}和米</c:if>
	            <c:if test="${platformId == 1001 && shopOrder.hemiPrice != null}">${shopOrder.hemiPrice}积分</c:if>
	          </label></td>	
	      <td></td>
		</c:if>
		<c:if test="${mallProduct.virtualCoin == null}">
		  <td colspan="2"><label>单价：<fmt:formatNumber type="number" value="${mallProduct.price/100}" pattern="0.00" maxFractionDigits="2"/>元
		  		<c:if test="${platformId == 1002 && mallProduct.virtualCoin != null}">+${mallProduct.virtualCoin}和米</c:if>
		      </label></td>
		  <td colspan="2">购买数量：${shopOrder.count}<c:if test="${platformId == 1001}">张</c:if></td>
	      <td colspan="2"><label>总价：<fmt:formatNumber type="number" value="${shopOrder.price/100}" pattern="0.00" maxFractionDigits="2"/>元
	            <c:if test="${platformId == 1002 && shopOrder.hemiPrice != null}">+${shopOrder.hemiPrice}和米</c:if>
	          </label></td>	
	      <td></td>
	     </c:if>
		</tr>
	</table>
	<table class="ticket">
		<tr>
		  <td colspan="6" width="800px"><!-- 电子券收货信息: --></td>
	    </tr>
		<c:forEach var="couponPossword" items="${couponPosswords}">
			<tr>
				<td width="25%"><%-- <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>${mallProduct.productName} --%></td>
				<td width="25%"><!-- ***************** --></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>