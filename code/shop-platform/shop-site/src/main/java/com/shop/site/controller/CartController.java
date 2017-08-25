package com.shop.site.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.model.CartItem;
import com.shop.model.ShopProduct;
import com.shop.service.CartService;

/**
 * 功能说明：购物车页面
 * 
 * @author xiang.wei
 *
 */
@Controller
@RequestMapping("/product")
public class CartController extends BaseController {
	@Autowired
	private CartService cartService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,
			HttpServletResponse response, ShopProduct product) {
		HttpSession session = request.getSession();
		if (session.getAttribute(SESSION_USER_ID + "_" + getUserId()) == null) {
			cartService.setMap(new LinkedHashMap<>());
		} else {
			cartService.setMap((Map<Long, CartItem>) session
					.getAttribute(SESSION_USER_ID + "_" + getUserId()));
		}
		int count = Integer.valueOf(request.getParameter("count"));
		CartItem item = new CartItem(product, count);
		cartService.addCart(item);
		session.setAttribute(SESSION_USER_ID + "_" + getUserId(),
				cartService.getMap());
		return "shop/cart";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/addProduct")
	public void addProduct(HttpServletRequest request,
			HttpServletResponse response, ShopProduct product) {
		HttpSession session = request.getSession();
		int count = Integer.valueOf(request.getParameter("count"));
		if (session.getAttribute(SESSION_USER_ID + "_" + getUserId()) == null) {
			cartService.setMap(new LinkedHashMap<>());
		} else {
			cartService.setMap((Map<Long, CartItem>) session
					.getAttribute(SESSION_USER_ID + "_" + getUserId()));
		}
		CartItem item = new CartItem(product, count);
		cartService.addCart(item);
		session.setAttribute(SESSION_USER_ID + "_" + getUserId(),
				cartService.getMap());
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/delProduct")
	public void delProduct(String productId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		if (session.getAttribute(SESSION_USER_ID + "_" + getUserId()) == null) {
			cartService.setMap(new LinkedHashMap<>());
		} else {
			cartService.setMap((Map<Long, CartItem>) session
					.getAttribute(SESSION_USER_ID + "_" + getUserId()));
		}
		cartService.removeCart(Long.getLong(productId));
		session.setAttribute(SESSION_USER_ID + "_" + getUserId(),
				cartService.getMap());
	}
}
