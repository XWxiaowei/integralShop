<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"
	contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE HTML>
<html>
<head>
<title>后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<%=request.getContextPath()%>/manage/assets/css/dpl-min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/manage/assets/css/bui-min.css"
	rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/manage/assets/css/main.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="header">
		<div class="dl-title"></div>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user"> <% 
    			String userid = (String)session.getAttribute("adminid"); 
    			if(userid != null)
    				out.write(userid); 
    		%>
			</span> <a href="<%=request.getContextPath()%>/manage/login_out.html"
				title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>

	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<% 	Set<String> authCodes = (Set<String>)request.getAttribute("authCodes"); 
	    			if(authCodes != null){
	    				if(authCodes.contains("100000")){ %>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-home">权限管理</div></li>
				<% } if(authCodes.contains("200000")){ %>
                <li class="nav-item dl-selected"><div
                        class="nav-item-inner nav-register">商品管理</div></li>
				<% } if(authCodes.contains("300000")){ %>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-register">运营管理</div></li>
				<% } if(authCodes.contains("400000")){ %>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-register">配置管理</div></li>
				<% } if(authCodes.contains("500000")){ %>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-register">回退管理</div></li>
				<% } if(authCodes.contains("600000")){ %>
				<li class="nav-item dl-selected"><div
						class="nav-item-inner nav-order">redis管理</div></li>
				<% } if(authCodes.contains("700000")){ %>
				<li class="nav-item dl-selected"><div
				        class="nav-item-inner nav-distribution">报表</div></li>
		        <% }if(authCodes.contains("900000")){ %>
				<li class="nav-item dl-selected"><div
		        class="nav-item-inner nav-distribution">商户管理</div></li>
        		<% }if(authCodes.contains("C00000")){ %>
				<li class="nav-item dl-selected"><div
		        class="nav-item-inner nav-distribution">业务管理</div></li>
        		<% }if(authCodes.contains("A00000")){ %>
				<li class="nav-item dl-selected"><div
				class="nav-item-inner nav-order">红包详情</div></li>
				<% } if(authCodes.contains("B00000")){ %>
                <li class="nav-item dl-selected"><div
                class="nav-item-inner nav-order">结算</div></li>
				<% }
	    	    }
	    		%>
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">
		</ul>
	</div>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/manage/assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/manage/assets/js/bui-min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/manage/assets/js/common/main-min.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/manage/assets/js/config-min.js"></script>
	<script>
	<%--与后台authority表进行权限匹配，保证code与authority表中的code一致--%>
    BUI.use('common/main',function(){
        var config = [
	    <%if(authCodes != null){
			if(authCodes.contains("100000")){ %>
        		{id:'100',menu:[{text:'权限管理',items:[{id:'1001',text:'角色管理',href:'<%=request.getContextPath()%>/manage/tab1/role/index.html'},
        		                                     {id:'1002',text:'用户管理',href:'<%=request.getContextPath()%>/manage/tab1/admin/index.html'}]}]}
         <% } if(authCodes.contains("200000")){ 
                if(authCodes.contains("100000")){%>
        		,
        		<%}%>
        		{id:'210',menu:[{text:'商品管理',items:[{id:'2101',text:'商品管理',href:'<%=request.getContextPath()%>/manage/tab2/product/list.html'},
									        		 {id:'2102',text:'订单查询',href:'<%=request.getContextPath()%>/manage/tab2/order/query.html'},
									        		 {id:'2103',text:'商户管理',href:'<%=request.getContextPath()%>/manage/tab2/merchant/list.html'},
									        		 {id:'2104',text:'推荐管理',href:'<%=request.getContextPath()%>/manage/tab2/commend/list.html'},
									        		 {id:'2105',text:'广告管理',href:'<%=request.getContextPath()%>/manage/tab2/banner/list.html'},
									        		 {id:'2106',text:'类目管理',href:'<%=request.getContextPath()%>/manage/tab2/catalog/list.html'}]}]}
        <% } if(authCodes.contains("300000")){ 
               if(authCodes.contains("100000") || authCodes.contains("200000")){%>
        		,
        		<%}%>
        	    {id:'310',menu:[{text:'运营管理',items:[{id:'3101',text:'用户管理',href:'<%=request.getContextPath()%>/manage/tab3/userCenter/userExport.html'}]}]}
        <% } if(authCodes.contains("400000")){ 
        	if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000")){%>
    		,
    		<%}%>
    		 {id:'410',menu:[{text:'配置管理',items:[{id:'4101',text:'查询配置',href:'<%=request.getContextPath()%>/manage/sysconf/index.html'}]}]}
    	<% } if(authCodes.contains("500000")){ 
    		if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000") || authCodes.contains("400000")){%>
    		,
    		<%}%>
    		{id:'510',menu:[{text:'回退管理',items:[{id:'5101',text:'积分回退',href:'<%=request.getContextPath()%>/manage/tab5/refund/point.html'},
    		                                  {id:'5102',text:'电子券回退',href:'<%=request.getContextPath()%>/manage/tab5/refund/redpackage.html'},
    		                                  {id:'5103',text:'无密支付冲正',href:'<%=request.getContextPath()%>/manage/tab5/refund/nosecretpayment.html'}]}]}
    	<% } if(authCodes.contains("600000")){ 
    		if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000") || authCodes.contains("400000") || authCodes.contains("500000")){%>
    		,
    		<%}%>
		    {id:'610',menu:[{text:'redis管理',items:[{id:'6101',text:'redis管理',href:'<%=request.getContextPath()%>/manage/redisadmin/index.html'}]}]}
	    <% } if(authCodes.contains("700000")){ 
    		if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000") || authCodes.contains("400000") || authCodes.contains("500000") || authCodes.contains("600000")){%>
    		,
    		<%}%>
		    {id:'810',menu:[{text:'报表',items:[{id:'8101',text:'支付结算汇总表',href:'<%=request.getContextPath()%>/manage/tab8/statement/settlesummary.html?hp=hp'},
		                                       {id:'8102',text:'支付明细表',href:'<%=request.getContextPath()%>/manage/tab8/statement/orderdetail.html?hp=hp'},
		                                       {id:'8103',text:'结出明细表',href:'<%=request.getContextPath()%>/manage/tab8/statement/billoutdetail.html'},
		                                       {id:'8104',text:'分润明细表',href:'<%=request.getContextPath()%>/manage/tab8/statement/transferdetail.html?hp=hp'},
		                                       {id:'8105',text:'退款明细表',href:'<%=request.getContextPath()%>/manage/tab8/statement/refunddetail.html'},
		                                       {id:'8106',text:'结入明细表',href:'<%=request.getContextPath()%>/manage/tab8/statement/billindetail.html'}]}]}
	    <% }if(authCodes.contains("900000")){ 
    		if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000") || authCodes.contains("400000") || authCodes.contains("500000") || authCodes.contains("600000") || authCodes.contains("700000")|| authCodes.contains("800000")){%>
    		,
    		<%}%>
		    {id:'910',menu:[{text:'商户管理',items:[ {id:'9101',text:'商户管理',href:'<%=request.getContextPath()%>/manage/tab9/merchant/index.html'},
		                                         {id:'C101',text:'业务管理',href:'<%=request.getContextPath()%>/manage/tab9/payinfo/index.html'},
		                                         {id:'9102',text:'产品管理',href:'<%=request.getContextPath()%>/manage/tab9/product/index.html'}]}]}
		                                       
	    
	    <% }if(authCodes.contains("A00000")){
		    if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000") || authCodes.contains("400000") || authCodes.contains("500000") || authCodes.contains("600000") || authCodes.contains("700000")|| authCodes.contains("800000")|| authCodes.contains("900000")||authCodes.contains("C00000")){%>
			,
			<%}%>
		    {id:'A10',menu:[{text:'红包详情',items:[{id:'A101',text:'详情查询',href:'<%=request.getContextPath()%>/manage/redpacket/findDetails.html'}]}]}
	    <% }if(authCodes.contains("B00000")){
            if(authCodes.contains("100000") || authCodes.contains("200000") || authCodes.contains("300000") || authCodes.contains("400000") || authCodes.contains("500000") || authCodes.contains("600000") || authCodes.contains("700000")|| authCodes.contains("800000")|| authCodes.contains("900000")||authCodes.contains("C00000")|| authCodes.contains("A00000")){%>
            ,
            <%}%>
            {id:'B10',menu:[{text:'结算',items:[{id:'B101',text:'重新结算',href:'<%=request.getContextPath()%>/manage/settle/redoIndex.html'}]}]}
        <% }
        }
        %>
        ];
        new PageUtil.MainPage({modulesConfig:config});
    });
    //通过src路径 定位到对应的iframe,用于跨域
    function findFrameForSrc(src){
    	return $("#J_NavContent").find("iframe[src='"+src+"']");
    }
</script>
</body>
</html>