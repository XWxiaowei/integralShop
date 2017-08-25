
package com.shop.admin.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.enums.CatalogLevel;
import com.shop.enums.OrderStatus;
import com.shop.enums.PayType;
import com.shop.model.OrderQuery;
import com.shop.model.ShopCatalog;
import com.shop.model.ShopMerchant;
import com.shop.model.ShopOrder2;
import com.shop.service.ShopCatalogService;
import com.shop.service.ShopMerchantService;
import com.shop.service.ShopOrderService;
import com.shop.util.PageTagQuerys;

@Controller
@RequestMapping("/manage/tab2/order")
public class AdminOrderController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(AdminOrderController.class);
	@Autowired
	private ShopOrderService orderService;
	@Autowired
	private ShopMerchantService merchantService;
	@Autowired
	private ShopCatalogService catalogService;

	/**
	 * @Desc 首次进入订单列表页面
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午12:09:59
	 */
	@RequestMapping(value = "/index")
	public String index(Model model, HttpServletRequest request) {
		getBindData(model);
		logger.info("返回订单列表页面（首次）");
		return "manage/order/list";
	}

	/**
	 * @Desc 多条件查询订单列表
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午12:17:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query")
	public String query(PageTagQuerys pageTagQuerys, Model model,
			HttpServletRequest request,
			@RequestParam(required = true) OrderQuery orderQuery) {
		getBindData(model);
		// 翻页时带入条件
		setCondition(request, orderQuery);
		orderQuery.setIpageIndex((pageTagQuerys.getCurr() - 1)
				* pageTagQuerys.getSize());
		orderQuery.setIpageSize(pageTagQuerys.getSize());
		Object[] iPagination = orderService.queryOrder(orderQuery);
		Integer iTotalRecord = 0;
		List<ShopOrder2> orderList = new ArrayList<>();
		if (iPagination != null) {
			iTotalRecord = (Integer) iPagination[0];
			orderList = (List<ShopOrder2>) iPagination[1];
		}
		// 返回参数
		pageTagQuerys.setCount(iTotalRecord);
		model.addAttribute("pageTagQuerys", pageTagQuerys);
		model.addAttribute("shopOrders", orderList);
		return "manage/order/list";
	}

	@RequestMapping(value = "/detail")
	public String detail(@RequestParam(required = true) String orderId,
			HttpServletRequest request, Model model) {
		ShopOrder2 shopOrder = orderService.selectByOrderId(Long
				.valueOf(orderId));
		model.addAttribute("shopOrder", shopOrder);
		model.addAttribute("orderStatusMap", OrderStatus.toMap());
		return "manage/order/edit";
	}

	/**
	 * @Desc 获取下拉框绑定的数据
	 * @param model
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午1:12:49
	 */
	private void getBindData(Model model) {
		// 查询所有的商户
		List<ShopMerchant> merchantList = merchantService.selectAll();
		// 查询所有的一级分类
		List<ShopCatalog> catalogList = catalogService
				.selectByLevel(CatalogLevel.FIRST.getCode());

		model.addAttribute("shopMerchants", merchantList);
		model.addAttribute("shopCatalogs", catalogList);
		model.addAttribute("orderStatusMap", OrderStatus.toMap());
		model.addAttribute("payTypeMap", PayType.toMap());
	}

	/**
	 * 翻页时传输条件
	 * 
	 * @param request
	 */
	private void setCondition(HttpServletRequest request, OrderQuery orderQuery) {
		if (StringUtils.isNotBlank(request.getParameter("query1"))) {
			orderQuery
					.setOrderId(Long.parseLong(request.getParameter("query1")));
		}
		if (StringUtils.isNotBlank(request.getParameter("query2"))) {
			orderQuery.setMerchantId(Long.parseLong(request
					.getParameter("query2")));
		}
		if (StringUtils.isNotBlank(request.getParameter("query3"))) {
			orderQuery.setCatalogId(Long.parseLong(request
					.getParameter("query3")));
		}
		if (StringUtils.isNotBlank(request.getParameter("query4"))) {
			orderQuery.setOrderStatus(Integer.parseInt(request
					.getParameter("query4")));
		}
		if (StringUtils.isNotBlank(request.getParameter("query5"))) {
			orderQuery.setProductName(request.getParameter("query5"));
		}
		if (StringUtils.isNotBlank(request.getParameter("query6"))) {
			orderQuery.setStartTime(request.getParameter("query6"));
		}
		if (StringUtils.isNotBlank(request.getParameter("query7"))) {
			orderQuery.setEndTime(request.getParameter("query7"));
		}
		if (StringUtils.isNotBlank(request.getParameter("query8"))) {
			orderQuery.setPayType(Integer.parseInt(request
					.getParameter("query8")));
		}
	}

}
