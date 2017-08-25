package com.shop.site.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseController {

	private final static Logger logger = LoggerFactory
			.getLogger(BaseController.class);
	protected static final String SESSION_USER_ID = "userId";

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}

	public static HttpServletResponse getResponse() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getResponse();
	}

	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
			logger.error("获取session异常", e);
		}
		return session;
	}

	public static Long getUserId() {
		if (getSession() == null) {
			return null;
		}
		Long userId = null;
		try {
			userId = (Long) getSession().getAttribute(SESSION_USER_ID);
		} catch (Exception e) {
			logger.error("获取userId异常", e);
		}
		return userId;
	}

}
