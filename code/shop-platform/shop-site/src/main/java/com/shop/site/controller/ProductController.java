package com.shop.site.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.model.ShopProduct;
import com.shop.service.ShopProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ShopProductService productService;

	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, Model model) {
		List<ShopProduct> promoteProductList = productService
				.queryPromoteProductList();
		model.addAttribute("promoteProductList", promoteProductList);
		return "shop/main";
	}

	@RequestMapping(value = "/more")
	public String more(HttpServletRequest request, Model model) {
		Map<String, Object> param = new HashMap<>();
		if (StringUtils.isNotBlank(request.getParameter("sort_keyword"))) {
			param.put("sort_keyword", request.getParameter("sort_keyword"));
		}
		if (StringUtils.isNotBlank(request.getParameter("productName"))) {
			param.put("productName", request.getParameter("productName"));
		}
//		Object[] pagination = productService.queryPaginationProductPage(param,
//				iPageIndex, iPageSize);
		Object[] pagination=null;
		if (pagination == null) {
			pagination = new Object[2];
			pagination[0] = 0;
			pagination[1] = 0;
		}
		model.addAttribute("itotalRecord", pagination[0]);
		model.addAttribute("record", pagination[1]);
		return "shop/more";
	}

	@RequestMapping(value = "/detail")
	public String detail(HttpServletRequest request, Model model) {
		if (StringUtils.isBlank(request.getParameter("productId"))) {
			model.addAttribute("message", "商品详情：产品id为空");
			return "redirect:/exception/error.html";
		}
		ShopProduct product = productService.selectByProductId(Long
				.valueOf(request.getParameter("productId")));
		model.addAttribute("product", product);
		return "shop/detail";
	}
	
}
