package com.shop.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.service.ShopOrderService;

/**
 * 功能说明：商品支付
 * 
 * @author xiang.wei
 *
 */
@Controller
@RequestMapping("/checkOut")
public class CheckOutController extends BaseController {
	private final Logger logger = LoggerFactory
			.getLogger(CheckOutController.class);
	@Autowired
	private ShopOrderService orderService;
	
	/**
	 * 功能说明：订单支付页面(普通订单)
	 * 
	 * @param productId
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月12日 下午7:49:52
	 */
	@RequestMapping(value = "/index")
	public String checkOut(String productId,Integer count, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if (productId == null) {
			logger.error("支付校验，无效请求：商品id为空");
			return "redirect:/exception/error.html";
		}
		
		return "shop/detail";
	}
	
	/**
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月12日 下午8:48:39
	 */
	@RequestMapping(value = "/confimPay")
	public String confimPay(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		return null;
	}
}
