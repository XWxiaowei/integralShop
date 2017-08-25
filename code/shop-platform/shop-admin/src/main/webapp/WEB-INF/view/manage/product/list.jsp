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
<form class="form-inline definewidth m20"
	action="<%=request.getContextPath()%>/manage/tab2/product/query.html"
	method="get">
	商家名称： <select id="productMerchant" name="productMerchant">
		<option value=''>---请选择商家---</option>
		<c:forEach var="merchant" items="${merchants}">
			<option value="${merchant.merchantId}"
				<c:if test="${merchant.merchantId==selectMerchantId}">
						selected="selected"
					</c:if>>${merchant.merchantName}</option>
		</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;&nbsp; 商品名称： <input type="text" name="productName"
		id="productName" maxlength="64" class="abc input-default"
		placeholder="" value="${selectProductName}">&nbsp;&nbsp;&nbsp;&nbsp;
	商品分类： <select id="productCatalog" name="productCatalog">
		<option value=''>---请选择商品分类---</option>
		<c:forEach var="catalog" items="${catalogs}">
			<option value="${catalog.catalogId}"
				<c:if test="${catalog.catalogId==selectCatalogId}">
						selected="selected"
					</c:if>>${catalog.catalogName}</option>
		</c:forEach>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;<br />
	<br />

	<div id="date-other">
		商品新增时间：
		<div id="otherDay1" class="input-append date form_date_day"
			data-link-field="dtp_input1" data-date-format="yyyy-mm-dd"
			data-date="">

			<input size="12" type="text" value="${createTimeStart}"
				name="createTimeStart" id="createTimeStart" readonly
				style="width: 100px" placeholder="请选择开始时间" onChange="setEndDate()" />
			<span class="add-on"><i class="icon-remove"></i></span> <span
				class="add-on"><i class="icon-calendar"></i></span>
		</div>
		-
		<div id="otherDay2" class="input-append date form_date_day"
			data-link-field="dtp_input1" data-date-format="yyyy-mm-dd"
			data-date="">
			<input size="12" type="text" value="${createTimeEnd}"
				name="createTimeEnd" id="createTimeEnd" readonly
				style="width: 100px" placeholder="请选择结束时间" onChange="setStartDate()" />
			<span class="add-on"><i class="icon-remove"></i></span> <span
				class="add-on"><i class="icon-calendar"></i></span>
		</div>

		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-primary"
			onClick="clearSerchForm()">清空</button>
	</div>
</form>
<form class=""
	action="<%=request.getContextPath()%>/manage/tab2/product/delete.html">
	<input type="hidden" name="query1" value="${productQuery.merchantId}">
	<input type="hidden" name="query2" value="${productQuery.productName}">
	<input type="hidden" name="query3" value="${productQuery.catalogId}">
	<input type="hidden" name="query4" value="${productQuery.productName}">
	<input type="hidden" name="query5" value="${productQuery.merchantId}">
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th></th>
				<th>商家名称</th>
				<th style="min-width: 80px;">商品ID</th>
				<th style="min-width: 100px;">商品名称</th>
				<th>商品分类</th>
				<th>商品新增时间</th>
				<th>单价</th>
				<th>剩余数量</th>
				<th style="min-width: 40px;">是否上架</th>
				<th style="min-width: 80px;">操作</th>
			</tr>
		</thead>
		<c:choose>
			<c:when test="${productList!=null}">
				<c:forEach varStatus="ls" var="product" items="${productList}">
					<tr>
						<td><input type="checkbox" name="checkIds"
							value="${product.id}"
							<c:if test="${product.onsell == 0}"> disabled </c:if> /></td>
						<td><c:forEach var="merchant" items="${merchants}">
								<c:if test="${product.merchantId == merchant.merchantId}">
						${merchant.merchantName}
					</c:if>
							</c:forEach></td>
						<td style="max-width: 130px;">${product.productId}</td>
						<td style="max-width: 250px;">${product.productName}</td>
						<td><c:forEach var="catalog" items="${catalogs}">
								<c:if test="${product.catalogId == catalog.catalogId}">
						${catalog.catalogName}
					</c:if>
							</c:forEach></td>
						<td><fmt:formatDate value="${product.createTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td style="min-width: 30px;"><fmt:formatNumber type="number"
								value="${product.price/100}" pattern="0.00"
								maxFractionDigits="2" /></td>
						<td>${product.inventory}</td>
						<td><c:if test="${product.onsell == 0}">上架</c:if> <c:if
								test="${product.onsell == 1}">下架</c:if></td>
						<td><c:if test="${product.onsell == 0}">
								<a class="btn btn-link" disabled id="edit"
									href="javascript:editOnsell()">编辑</a>
								<a class="btn btn-link" id="onsell0"
									href="<%=request.getContextPath()%>/manage/tab2/product/onsell.html?onsell=0&productId=${product.productId}
					&query1=${productQuery.merchantId}&query2=${productQuery.productName}
					&query3=${productQuery.catalogId}&query4=${productQuery.createTimeStart}
					&query5=${productQuery.createTimeEnd}"
									<c:if test="${adminAuthority != 1 && adminAuthority !=2 }">disabled onclick="return false"</c:if>>下架</a>
							</c:if> <c:if test="${product.onsell == 1}">
								<a class="btn btn-link" id="edit"
									href="<%=request.getContextPath()%>/manage/tab2/product/edit.html?id=${product.id}">编辑</a>
								<a class="btn btn-link" id="onsell0"
									href="<%=request.getContextPath()%>/manage/tab2/product/onsell.html?onsell=1&id=${product.id}
					&query1=${productQuery.merchantId}&query2=${productQuery.productName}
					&query3=${productQuery.catalogId}&query4=${productQuery.createTimeStart}
					&query5=${productQuery.createTimeEnd}"
									<c:if test="${adminAuthority != 1 && adminAuthority !=2 }">disabled onclick="return false"</c:if>>上架</a>
							</c:if></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:when test="${productList==null}">
				<tr>
					<td colspan="11">没有符合条件的商品</td>
				</tr>
			</c:when>
		</c:choose>
		<tr>
		<td colspan=9></td>
		<td>
			<a class="btn btn-success" id="addnew"
			href="javascript:addProduct()" >新增</a>
			&nbsp;&nbsp;
			<button class="btn btn-danger" onClick="return delcfm()">删除</button> 
		</td>
	</tr>
	</table>
</form>
<div class="box-tab-list" style="width: 85%" align="center"></div>

<!-- 信息删除确认 -->  
<div class="modal hide fade in" id="delcfmModel" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">  
  <div class="modal-dialog">  
    <div class="modal-content message_align">  
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>  
        <h4 class="modal-title">提示信息</h4>  
      </div>  
      <div class="modal-body">  
        <p class="alert alert-warning">
       		 是否删除该商品？
		</p>  
      </div>  
      <div class="modal-footer">  
         <button type="button" class="btn btn-default" data-dismiss="modal">取消</button> 
         <a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal">确定</a>  
      </div>  
    </div><!-- /.modal-content -->  
  </div><!-- /.modal-dialog -->  
</div><!-- /.modal -->  


<!-- 提示窗口 -->
<div class="modal hide fade in" id="msg-dialog" role="dialog"  backdrop="static"
   aria-labelledby="myModalLabel" aria-hidden="true">  
	<div class="modal-dialog">  
		<div class="modal-content message_align">  
			<div class="modal-header">  
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>  
				<h4 class="modal-title"></h4>  
			</div>  
			<div class="modal-body text-center">
				<img src="<%=request.getContextPath() %>/manage/Images/loader.gif" id="loadding"> 
				<p class="alert alert-danger" id="message"></p>  
			</div>  
			<div class="modal-footer">  
				<button type="button" class="btn btn-default" data-dismiss="modal" id="confirm">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal" id="cancel">取消</button> 
         		<a  onclick="urlSubmit()" class="btn btn-success" data-dismiss="modal" id="delSubmit">确定</a>  
			</div>  
		</div><!-- /.modal-content -->  
	</div><!-- /.modal-dialog -->  
</div><!-- /.modal --> 

<!-- 分页 -->
<table class="table table-bordered table-hover definewidth m10">
	<my:showUserInfoQuerysNew href="/manage/tab2/product/query.html"
		curr="${pageTagQuerys.curr}" size="${pageTagQuerys.size}"
		total="${pageTagQuerys.total}" count="${pageTagQuerys.count}"
		query1="${productQuery.merchantId}" query2="${productQuery.productName}" 
		query3="${productQuery.catalogId}" query4="${productQuery.createTimeStart}"
		query5="${productQuery.createTimeEnd}"
	/>
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
	if($("#createTimeStart").val() != null){
		$('#otherDay2').datetimepicker('setStartDate', $("#createTimeStart").val());
	}
	 
	if($("#createTimeEnd").val() != null){
		$('#otherDay1').datetimepicker('setEndDate', $("#createTimeEnd").val());
	}
	
	// 上架商品编辑操作结果
	var usersEdit = "${usersEdit}";
	if(usersEdit != ""  && usersEdit != null){
		showDialog("温馨提示", usersEdit, "1");
	}

});
//清空删选条件
function clearSerchForm() {
    $("#productName").val("");
    $("#productMerchant").val("");
    $("#productCatalog").val("");
    $("#createTimeStart").val("");
    $("#createTimeEnd").val("");
    
	$('#otherDay2').datetimepicker('setStartDate', "");
	$('#otherDay1').datetimepicker('setEndDate', "");
	
	
}
function addProduct(){
	window.location.href="<%=request.getContextPath()%>/manage/tab2/product/add.html";
}
function delcfm(){
	selectNum = $("input[type='checkbox']:checked").length;
	if(selectNum == 0){
		showDialog("温馨提示", "请选择要删除的商品", "1");
	}
	else{
		showDialog("温馨提示", "是否删除该商品？", "2");
	}
    return false;
}
//编辑上架商品
function editOnsell() {
	showDialog("温馨提示", "上架商品不能编辑", "1");
}
//提交删除请求表单
function urlSubmit(){  
   	$('#delform').submit();
} 

//显示提示窗口
function showDialog(title, msg, button){
	$("#msg-dialog .modal-title").html(title);
	if(msg){
		$("#message").show().html(msg);
		$("#loadding").hide();
	}else{
		$("#loadding").show();
		$("#message").hide();
	}
	if(button == "1"){
		$("#confirm").show();
		$("#delSubmit").hide();
		$("#cancel").hide();
	}
	else if(button == "2"){
		$("#confirm").hide();
		$("#delSubmit").show();
		$("#cancel").show();
	}
	$("#msg-dialog").modal({backdrop:'static', keyboard:false});
}

</script>
<jsp:include page="../common/bottom.jsp" />



