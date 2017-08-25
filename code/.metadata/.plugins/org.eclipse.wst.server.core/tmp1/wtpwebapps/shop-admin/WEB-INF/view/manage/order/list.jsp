<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="mytaglibs" prefix="my"%>
<jsp:include page="../common/top.jsp" />
<form id="order_query" class="form-inline definewidth m20"
	action="<%=request.getContextPath()%>/manage/tab2/order/query.html"
	method="post">
	<table style="text-align: left; margin-left: 35px;" cellspacing="5"
		cellpadding="5">
		<tr>
			<td><label for="orderId">订单号</label></td>
			<td><input type="text" id="orderId" maxlength="20"
				name="orderId" onkeyup="value=value.replace(/[^\d]/g,'')"
				onclick="CheckNumber()" onchange="CheckNumber(this)"
				value="${orderQuery.orderId }" style="width: 156px; margin: 0px;"
				class="field_required" /></td>
			<td><label for="goodsName">商品名称</label></td>
			<td><input type="text" name="productName" id="productName"
				class="field_required" maxlength="64" value="${productName}"
				style="width: 156px; margin: 0px;" /></td>
			<td><label for="payType">支付方式</label></td>
			<td><select class="field_select" id="payType" name="payType"
				value="${payType}" style="width: 170px; margin: 0px;">
					<option value="">---请选择支付方式---</option>
					<c:forEach items="${payTypeMap}" var="payTypeItem">
						<option value="${payTypeItem.key}"
							<c:if test="${payTypeItem.key==payType}">selected</c:if>>${payTypeItem.value}</option>
					</c:forEach>
			</select></td>
			<td><label for="orderStatus">订单状态</label></td>
			<td><select class="field_select" id="orderStatus"
				name="orderStatus" value="${orderQuery.orderStatus}"
				style="width: 170px; margin: 0px;">
					<option value="">---请选择订单状态---</option>
					<c:forEach items="${orderStatusMap}" var="orderStatus">
						<option value="${orderStatus.key}"
							<c:if test="${orderStatus.key==orderStatus}">selected</c:if>>${orderStatus.value}</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><label for="shopName">商家名称</label></td>
			<td><select class="field_select" id="merchantId"
				name="merchantId" value="${merchantId}"
				style="width: 170px; margin: 0px;">
					<option value="">---请选择商家---</option>
					<c:forEach items="${shopMerchants}" var="shopMerchant">
						<option value="${shopMerchant.merchantId}"
							<c:if test="${shopMerchant.merchantId==merchantId}">selected</c:if>>${shopMerchant.merchantName}</option>
					</c:forEach>
			</select></td>
			<td><label for="categories">商品分类</label></td>
			<td><select class="field_select" id="catalogId" name="catalogId"
				value="${catalogId}" style="width: 170px; margin: 0px;">
					<option value="">---请选择商品分类---</option>
					<c:forEach items="${shopCatalogs}" var="shopCatalog">
						<option value="${shopCatalog.catalogId}"
							<c:if test="${shopCatalog.catalogId==catalogId}">selected</c:if>>${shopCatalog.catalogName}</option>
					</c:forEach>
			</select></td>
			<td><label for="singleTime">支付时间</label></td>
			<td><div id="date-begin1" class="input-append date form_date"
					data-link-field="dtp_input1" data-date-format="yyyy-mm-dd"
					data-date="" style="width: 170px;">
					<input size="12" type="text" value="${startTime}" id="startTime"
						name="startTime" readonly style="width: 100px"
						onChange="setEndDate()" /> <span class="add-on"><i
						class="icon-remove"></i></span> <span class="add-on"><i
						class="icon-calendar"></i></span>
				</div></td>
			<td style="text-align: center;"><label for="to">至</label></td>
			<td><div id="date-begin2" class="input-append date form_date"
					data-link-field="dtp_input1" data-date-format="yyyy-mm-dd"
					data-date="" style="width: 170px; margin: 0px;">
					<input size="12" type="text" value="${endTime}" id="endTime"
						name="endTime" readonly style="width: 100px"
						onChange="setStartDate()" /> <span class="add-on"><i
						class="icon-remove"></i></span> <span class="add-on"><i
						class="icon-calendar"></i></span>
				</div></td>
		</tr>
		<tr>
			<td colspan=6></td>
			<td colspan=2 style="text-align: right;">
				<button type="button" id="search" class="btn btn-primary"
					onclick="javascript:subquery(this);">查询</button>&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" id="remove" class="btn btn-success"
					onclick="clearSerchForm()">清空</button>&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</form>
<form id="cancelCfm" class="form-inline definewidth m20"
	action="<%=request.getContextPath()%>/manage/tab2/order/cancelOrder.html?"
	method="get">

	<input type="hidden" id="query1" name="query1" value="${orderId}" /> <input
		type="hidden" id="query2" name="query2" value="${merchantId}" /> <input
		type="hidden" id="query3" name="query3" value="${catalogId}" /> <input
		type="hidden" id="query4" name="query4" value="${orderStatus}" /> <input
		type="hidden" id="query5" name="query5" value="${productName}" /> <input
		type="hidden" id="query6" name="query6" value="${startTime}" /> <input
		type="hidden" id="query7" name="query7" value="${endTime}" /> <input
		type="hidden" id="query8" name="query8" value="${payType}" />
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th width="5px;"><input type="checkbox" id="doSelectAll" /></th>
				<th style="width: 90px; min-width: 90px;"><span>下单时间</span></th>
				<th style="width: 90px; min-width: 90px;"><span>订单号</span></th>
				<th style="width: 115px; min-width: 115px;"><span>商品名称</span></th>
				<th style="width: 90px; min-width: 90px;"><span>产品编码</span></th>
				<th style="width: 50px; min-width: 50px;"><span>商品分类</span></th>
				<th style="width: 50px; min-width: 50px;"><span>收货人</span></th>
				<th style="width: 50px; min-width: 50px;"><span>购买数量</span></th>
				<th style="width: 35px; min-width: 35px;"><span>金额</span></th>
				<th style="width: 90px; min-width: 90px;"><span>支付时间</span></th>
				<th style="width: 50px; min-width: 50px;"><span>支付方式</span></th>
				<th style="width: 50px; min-width: 50px;"><span>订单状态</span></th>
				<th style="width: 35px; min-width: 35px;"><span>操作</span></th>
			</tr>
		</thead>
		<c:choose>
			<c:when test="${shopOrders!=null}">
				<c:forEach var="shopOrder" items="${shopOrders}" varStatus="ls">
					<tr>
						<td><c:if test="${shopOrder.orderStatus == 2}">
								<input type="checkbox" name="checkIds"
									value="${shopOrder.orderId}">
							</c:if> <c:if test="${shopOrder.orderStatus != 2}">
								<input type="checkbox" name="checkIds" disabled
									value="${shopOrder.orderId}">
							</c:if></td>
						<td><fmt:formatDate value="${shopOrder.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><a
							href="<%=request.getContextPath()%>/manage/tab2/order/detail.html?orderId=${shopOrder.orderId}">${shopOrder.orderId}</a></td>
						<td>${shopOrder.productName}</td>
						<td>${shopOrder.couponId}</td>
						<td>${shopOrder.catalogName}</td>
						<td>${shopOrder.receiverName}</td>
						<td>${shopOrder.count}</td>
						<td><fmt:formatNumber type="number"
								value="${shopOrder.price/100}" pattern="0.00"
								maxFractionDigits="2" /></td>
						<td><fmt:formatDate value="${shopOrder.payTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><c:forEach items="${payTypeMap}" var="payTypeItem">
								<c:if test="${payTypeItem.key==shopOrder.payType}">${payTypeItem.value}</c:if>
							</c:forEach></td>
						<td><c:forEach items="${orderStatusMap}" var="orderStatus">
								<c:if test="${orderStatus.key==shopOrder.orderStatus}">${orderStatus.value}</c:if>
							</c:forEach></td>
						<td><c:if test="${shopOrder.orderStatus == 0}">
								<a class="btn-link"
									href="javascript:deliverClick(deliverOid${ls.count })">发货</a>
								<input type="hidden" id="deliverOid${ls.count }"
									value="${shopOrder.orderId}" />
							</c:if></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${shopOrders==null}">
				<tr>
					<td colspan="13">没有符合条件的订单</td>
				</tr>
			</c:when>
		</c:choose>
		<tr>
			<td colspan=10></td>
			<td colspan=3>
				<button class="btn btn-danger" onClick="return delcfm()">取消订单</button>
				<a class="btn btn-success" onClick="saveExcel()">Excel导出</a>
			</td>
		</tr>
	</table>
</form>
<table class="table table-bordered table-hover definewidth m10">
	<my:showUserInfoQuerysNew href="/manage/tab2/order/query.html"
		curr="${pageTagQuerys.curr}" size="${pageTagQuerys.size}"
		total="${pageTagQuerys.total}" count="${pageTagQuerys.count}"
		query1="${orderId}" query2="${merchantId}" query3="${catalogId}"
		query4="${orderStatus}" query5="${productName}" query6="${startTime}"
		query7="${endTime}" query8="${payType}" />
</table>
<script>
 $(function(){
	 //对日期框进行控制
	$(".form_date").datetimepicker({
		  language: 'zh-CN',
          weekStart: 1,
          todayBtn: 1,
          autoclose: 1,
          todayHighlight: 1,
          startView: 2,
          minView: 2,
          forceParse: 0
	});
	if($("#startTime").val() != null){
		$('#date-begin2').datetimepicker('setStartDate', $("#startTime").val());
	}
	 
	if($("#endTime").val() != null){
		$('#date-begin1').datetimepicker('setEndDate', $("#endTime").val());
	}
	//输入框限制数字
	var orderid = document.getElementById("orderId");
	orderid.onkeyup = function(){
		this.value=this.value.replace(/\D/g,'');
	}
 });
 function clearSerchForm() {
 	$(".field_required").val("");
 	$("#startTime").val("");
 	$("#endTime").val("");
 	$(".field_select").val("");
 	$('#date-begin2').datetimepicker('setStartDate', "");
 	$('#date-begin1').datetimepicker('setEndDate', "");
 }
 function subquery(obj){
	$("#order_query").attr("action","<%=request.getContextPath()%>/manage/tab2/order/query.html");
	$("#order_query").submit();
	}
</script>