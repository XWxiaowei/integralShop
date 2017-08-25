package com.shop.admin.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class BaseController {
	private final static Logger logger = LoggerFactory
			.getLogger(BaseController.class);

	protected static final String SESSION_USER_ID = "adminid";
	protected static final String SESSION_MERCHANT_ID = "merchantid";

	protected static final String PIC_FOLD_NAME = "pics";

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return attrs.getRequest();
	}

	public static HttpSession getSession() {
		HttpSession session = null;
		try {
			session = getRequest().getSession();
		} catch (Exception e) {
			logger.error("获取session异常:{}", e);
		}
		return session;
	}

	protected String getAdminname() {
		if (getSession() == null) {
			return null;
		}
		return (String) getSession().getAttribute("adminid");
	}

	public static String getBasePicPath() {
		// E:\和我信\商盟新地址\code\branches\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\mall-admin-[1.12]
		String realPath = getSession().getServletContext().getRealPath("");// 获得项目的根路径
		if (realPath.lastIndexOf(File.separator) == realPath.length() - 1) {
			realPath = realPath.substring(0, realPath.lastIndexOf(File.separator));
		}
		return realPath.substring(0, realPath.lastIndexOf(File.separator))
				+ PIC_FOLD_NAME + File.separator;
	}

}
