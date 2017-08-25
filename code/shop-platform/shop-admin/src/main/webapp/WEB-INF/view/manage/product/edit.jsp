<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
	contentType="text/html;charset=UTF-8"%>
<jsp:include page="../common/top.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/manage/assets/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/manage/assets/ueditor/ueditor.all.min.js">
	
</script>
<style>
#preview {
	width: 260px;
	height: 190px;
	border: 1px solid #000;
	overflow: hidden;
}

#imghead {
	filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);
}
</style>
<div class="modal hide fade in" id="msg-dialog" role="dialog"  backdrop="static"
   aria-labelledby="myModalLabel" aria-hidden="true">  
		  <div class="modal-dialog">  
		    <div class="modal-content message_align">  
		      <div class="modal-header">  
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>  
		        <h4 class="modal-title">转账失败</h4>  
		      </div>  
		      <div class="modal-body text-center">
		      	<img src="<%=request.getContextPath() %>/manage/Images/loader.gif" id="loadding"> 
		        <p class="alert alert-danger" id="message"></p>  
		      </div>  
		      <div class="modal-footer">  
		         <button type="button" class="btn btn-default" data-dismiss="modal" id="confirm">确定</button>  
		         <button type="button" class="btn btn-default" data-dismiss="modal" id="cancel">取消</button>  
		      </div>  
		    </div><!-- /.modal-content -->  
		  </div><!-- /.modal-dialog -->  
		</div><!-- /.modal --> 
<form
	action="<%=request.getContextPath()%>/manage/tab2/product/update.html"
	method="post" id="productInfo" enctype="multipart/form-data">
	<div class="form-group">
		<input type="hidden" name="produtId" value="${product.produtId}" />
	    <input type="hidden" id="titlePicture" value="${product.titlePicture}" />
		<input type="hidden" id="logoPicture" value="${product.logoPicture}" />
		<table>
			<tr>
				<td width="10%" class="tableleft">商品名称<span style="color: red;">&nbsp;*</span></td>
				<td width="23%" colspan="2"><select>
					<option value="">---请选择商户---</option>
					<c:forEach varStatus="ls" var="merchant" items="${merchants}">
						<option value="${merchant.merchantId==product.merchantId}">
							${merchant.merchantName}
						</option>
					</c:forEach>
				</select>
				</td>
				<td width="10%" class="tableleft">商品大类<span style="color: red">&nbsp;*</span></td>
				<td width="23%" colspan="2"><select id="selectCatalog" class="required" onChange="checkCatalog()">
					<option value="">---请选择大类---</option>
					<c:forEach var="catalog" items="${catalogs}">
						<option value="${catalog.catalogId}"
							${catalog.catalogId eq parentCatalogId ? 'selected="selected"' : ''}>
							${catalog.catalogName}</option>
					</c:forEach>
			     </select></td>
				<td width="10%" class="tableleft">商品小类<span style="color: red">&nbsp;*</span></td>
				<td width="23%" colspan="2"><select name="catalogId" class="required" id="selectChildCatalog">
					<option value="">---请选择类别---</option>
					<c:forEach var="catalog" items="${secondCatalogList}">
						<option value="${catalog.catalogId}"
							${catalog.catalogId eq product.catalogId ? 'selected="selected"' : ''}>
							${catalog.catalogName}</option>
					</c:forEach>
			</select></td>
			</tr>
			<tr>
				<td class="tableleft">商品名称<span style="color: red">&nbsp;*</span></td>
			<td colspan="5"><input class="span6 required" type="text"
				name="productName" value="${product.productName}" maxlength="64" />
			</td>
			<td class="tableleft">商品ID</td>
			<td colspan="2"><input type="text" readonly="readonly" name="productId" placeholder="由系统自动生成"
				value="${product.productId}" maxlength="20" /></td>
			</tr>
				<tr>
			<td class="tableleft">市场价（元）</td>
			<td colspan="2"><input type="hidden" id="marketPrice" name="marketPrice" value="${product.marketPrice}" maxlength="8"/>
				<input type="text" id="marketPriceShow" onkeyup="clearNoNum(this)" value='<fmt:formatNumber value="${product.marketPrice/100}" pattern="0.00"/>'/></td>
			<td class="tableleft">进价（元）</td>
			<td colspan="2"><input type="hidden" id="costPrice" name="costPrice" value="${product.costPrice}" maxlength="8"/>
				<input type="text" id="costPriceShow" onkeyup="clearNoNum(this)" value='<fmt:formatNumber value="${product.costPrice/100}" pattern="0.00"/>'/></td>
			<td class="tableleft">销售价（元）</td>
			<td colspan="2"><input type="hidden" id="price" name="price" value="${product.price}" maxlength="8"/>
				<input type="text" id="priceShow" onkeyup="clearNoNum(this)" value='<fmt:formatNumber value="${product.price/100}" pattern="0.00"/>'/></td>
		</tr>
		<tr>
			<td class="tableleft">库存数量<span style="color: red">&nbsp;*</span></td>
			<td colspan="2"><input type="text" name="inventory" class="required digits min:1"
				value="${product.inventory}" maxlength="8" min="1"/></td>
			<td class="tableleft">限购数目</td>
			<td colspan="2"><input type="text" name="numLimit" class="digits"
				value="${product.numLimit}" maxlength="8" min="1"/></td>
			<!-- <td class="tableleft">促销信息</td> -->
			<%-- <td colspan="2"><input id="promotionCheckbox" type="checkbox"  
					<c:if test="${mallProduct.promotion == 1}">checked</c:if>
				/>&nbsp;促销
				<input hidden id="promotion" name="promotion" value="${mallProduct.promotion}"/>
			</td> --%>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td class="tableleft">商品详情<span style="color: red">&nbsp;*</span></td>
			<td colspan="8">
				<input type="hidden" class="span12 required" name="info" id="info" value='${product.info}' />
				<script id="editor" type="text/plain" style="width:96%;height:500px;" class="required"></script>
			</td>
		</tr>
		<tr>
			<td class="tableleft">商品标题图标<span style="color: red">&nbsp;*</span></td>
			<td colspan="3"><img src="showImage.html?filePath=${product.titlePicture }" width="100px" /> <input
				type="file" style="display: none" id="file1" name="file1" class="required"/> <i class="icon-share uploadFile"></i>
			</td>
			<td class="tableleft">商品logo图标<span style="color: red">&nbsp;*</span></td>
			<td colspan="4"><img src="showImage.html?filePath=${product.logoPicture }" width="100px" /> <input
				type="file" style="display: none" id="file2" name="file2" class="required"/> <i class="icon-share uploadFile"></i>
			</td>
		</tr>
		<tr>
			<td class="tableleft">操作</td>
			<td colspan="3">
				<input type="radio" value="0" name="onsell"
					<c:if test="${product.onsell == 0}">checked=checked</c:if>
					<c:if test="${adminAuthority == 3 || adminAuthority == 0}">onClick="javascript:return false;"</c:if> />上架
				<c:if test="${product.onsell == null}">
					<input type="radio" value="1" name="onsell" checked=checked
					<c:if test="${adminAuthority == 3 || adminAuthority == 0}">onClick="javascript:return false;"</c:if> />下架 
				</c:if>
				<c:if test="${product.onsell != null}">
					<input type="radio" value="1" name="onsell"
					<c:if test="${product.onsell == 1}">checked=checked</c:if>
					<c:if test="${adminAuthority == 3 || adminAuthority == 0}">onClick="javascript:return false;"</c:if> />下架 
				</c:if>
			</td>
			<td colspan="5">
				<a class="btn btn-primary" id="saveBtn">保存</a>&nbsp;&nbsp;
				<a type="button" class="btn btn-success" id="backid" >取消</a>
				<span style="color: red" id="message"></span>
			</td>
		</tr>
	</table>
	</div>
</form>

<script>
	$(function(){
		
		checkCatalog();
		//图片上传预览   IE是用了滤镜
		function previewImage(file){
			var MIXWIDTH=260;
			var MIXHEIGHT=180;
			if(file.files&&file.files[0]){
				var img=file.parentElement.firstChild;
				var imgL=file.value.split(".");
				var imgF=imgL[img.length-1];
				if(!(imgF.toLowerCase()=='jpg' || imgF.toLowerCase()=='png' || imgF.toLowerCase()=='bmp')){
					file.select();
					img src="";
					$('#confirm').unbind('click');
					showDialog("温馨提示","请选择jpg,png;bmp格式图片","1");
					return;
				}
				img.onload=function(){
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
							img.offsetWidth, img.offsetHeight);
					img.width = rect.width;
					img.height = rect.height;
				}
				var reader=new FileReader();
				reader.onload=function(evt){
					var img1=new Image();
					img1.src=evt.target.result;
					img.src=evt.target.result;
				}
				reader.readAsDataURL(file.files[0]);
			}
			//兼容IE
			else{
				var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
				file.select();
				var src=document.selection.createRange().text;
				div.innerHTML='<img id=imghead>'
				var img=document.getElementById("imghead");
				img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src=src;
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT,
						img.offsetWidth, img.offsetHeight);
				status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width
						+ ',' + rect.height);
				div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
			}
		}
		function  clacImgZoomParam(maxWidth,maxHeight,width,height) {
			var param={
					top:0,
					left:0,
					width:width,
					height:height
			};
			if (width>maxWidth||height>maxHeight) {
				rateWidth=width/maxWidth;
				rateHeight=height/maxHeight;
				if(rateWidth>rateHeight){   //?
					param.width=maxWidth;
					param.height=Math.round(height / rateWidth);
				}else{
					param.width=Math.round(width/rateHeight);
					param.height=height;
				}
			}
			param.left = Math.round((maxWidth - param.width) / 2);
			param.top = Math.round((maxHeight - param.height) / 2);
			return param;
		}
		jQuery.extend(jQuery.validator.message,{
			  required: "必填",
			  remote: "请修正该字段",
			  email: "请输入正确格式的电子邮件",
			  url: "请输入合法的网址",
			  date: "请输入合法的日期",
			  dateISO: "请输入合法的日期 (ISO).",
			  number: "请输入合法的数字",
			  digits: "只能输入正整数",
			  creditcard: "请输入合法的信用卡号",
			  equalTo: "请再次输入相同的值",
			  accept: "请输入拥有合法后缀名的字符串",
			  maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
			  minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
			  rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
			  range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
			  max: jQuery.validator.format("请输入一个最大为{0} 的值"),
			  min: jQuery.validator.format("请输入一个最小为{0} 的值")
		});
		$('#savaBtn').click(function () {
			//判断输入值的大小
			if (parseFloat($('#marketPrice').val())>999999.99) {
				$('#confirm').unbind('click');
				showDialog("温馨提示", "市场价最大值为999999.99元", "1");
				return;
			}
			if(parseFloat($("#costPriceShow").val())>999999.99){
				$('#confirm').unbind("click");
				showDialog("温馨提示", "进价最大值为999999.99元", "1");
				return;
			}
			if(parseFloat($("#priceShow").val())>999999.99){
				$('#confirm').unbind("click");
				showDialog("温馨提示", "销售价最大值为999999.99元", "1");
				return;
			}
			//价格赋值,元转分
			clearNoNum($("#marketPriceShow"));
			clearNoNum($("#costPriceShow"));
			clearNoNum($("#priceShow"));
			$("#info").val(UE.getEditor('editor').getContent()+"\n");

			if(!$('#productInfo').valid()) {
				$('#confirm').unbind("click");
				showDialog("温馨提示", "请完善信息", '1');
				return;
			}
			if(!($('#file1').val() || $('#titlePicture').val())) {
				$('#confirm').unbind("click");
				showDialog("温馨提示", "请上传标题图片", '1');
				return;
			}
			if(!($('#file2').val() || $('#logoPicture').val())) {
				$('#confirm').unbind("click");
				showDialog("温馨提示", "请上传logo图片", '1');
				return;
			}
				
		});
		$('#backid').click(function(){
	    	showDialog("温馨提示","您是否确认取消", 2);
	    	$('#confirm').click(function(){
	    		window.location.href="<%=request.getContextPath()%>/manage/tab2/product/list.html";
			});
		 });
		$('#selectCatalog').on('change',function(){
			if ('0'==$(this).val()) {
				$('#selectChildCatalog').empty();
				$('#selectChildCatalog').append("<option value="">---请选择类别---</option>");
				return;
			} 
			$.post(
				"selectCatalog.do",
				{
					catalogId:$(this).val()
				},
				function (msg,result) {
					$('#selectChildCatalog').empty();
					$('#selectChildCatalog').append("<option value="">---请选择类别---</option>");
					for ( var i in msg) {
						$('#selectChildCatalog').append(
							"<option value="+msg[i].catalogId+">"+msg[i].catalogName+"</option>"
						);
					}
				}
			);
			
		});
		$('#otherDay1').datetimepicker().on('changeDate',function(ev){
			var dateTime=
		});
		$('#otherDay2').datetimepicker().on('changeDate',function(ev){
			
		});
		//点击打开文件选择器  prev方法获取前一个同辈元素
		$(".uploadFile").click(function () {
			$(this).prev().click();
			$(this).prev().on('change',function(){
				previewImage(this);
			});
		});
	});
	function checkCatalog(){
		if($("#selectCatalog option:selected").text().indexOf("虚拟")>=0){
			$("#otherDate1").addClass("required");
			$("#otherDate2").addClass("required");
		}else{
			$("#otherDate1").removeClass("required");
			$("#otherDate2").removeClass("required");
		}
	}
	function showDialog(title, msg, btn){
		$('#msg-dialog .model-title').html(title);
		if (msg) {
			$("#message").show().html(msg);
			$("#loadding").hide();
			if (btn=="1") {
				$("#cancel").hide();
			} else if (btn=="2") {
				$("#cancel").show();
			}
		}else {
			$("#loadding").show();
			$("#message").hide();
			if(btn == "1"){
				$("#cancel").hide();
			}
			else if(btn == "2"){
				$("#cancel").show();
			}
		}
		$("#msg-dialog").modal({backdrop:'static', keyboard:false});
	}
</script>
<jsp:include page="../common/bottom.jsp" />