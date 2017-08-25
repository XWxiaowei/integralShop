package com.shop.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageTagQuerys extends TagSupport {
	private static final long serialVersionUID = 1982761940175989699L;
	private final Logger logger = LoggerFactory.getLogger(PageTagQuerys.class);
	HttpServletRequest request = (HttpServletRequest) this.pageContext
			.getRequest();
	private String href;
	private int curr = 1;// 当前页
	private int size = 10;// 每页条数
	private int total = 0;// 总页数
	private int count = 0;// 记录总数

	private String query1 = "";

	private String query2 = "";

	private String query3 = "";

	private String query4 = "";

	private String query5 = "";

	private String query6 = "";

	private String query7 = "";

	private String query8 = "";

	private String query9 = "";

	private String query10 = "";

	private String query11 = "";

	private String query12 = "";

	private String readonly = "false";

	@Override
	public int doStartTag() {
		JspWriter out = this.pageContext.getOut();

		int temp = curr - 1;
		if (curr > total) {
			curr = total;
		}
		try {
			out.println("<td></td>");
			StringBuffer buffer = new StringBuffer();
			buffer.append("总记录数 " + count + " 条，总页数 " + total + " 页，每页显示 "
					+ Integer.toString(size) + " 条，");
			buffer.append("当前是第" + Integer.toString(curr)
					+ "页。&nbsp;&nbsp;&nbsp;&nbsp;");
			if (curr != 1 && total > 1) {// 添加上一页地址
				String url=getUrl(temp);
				url=url+"'>上一页</a>&nbsp;  ";
				buffer.append(url);
			}
			if (curr < total && total > 1) {// 添加下一页地址
				temp=curr+1;
				String url=getUrl(temp);
				url=url+"'>下一页</a>&nbsp;  ";
				buffer.append(url);
			}
			if (total > 1) {// 添加第一页地址和最后一页地址
				if (curr != 1) {// 添加第一页地址
					String url=getUrl(1);
					url=url+"'>下一页</a>&nbsp;  ";
					buffer.append(url);
				}
				if (curr != total) { // 添加最后一页地址
					String url=getUrl(total);
					url=url+"'>下一页</a>&nbsp;  ";
					buffer.append(url);
				}
			}

		} catch (Exception e) {
			logger.error("IO错误{}", e);
		}
		
		return EVAL_BODY_INCLUDE;
	}
	
	public String getUrl(int temp){
	       String url= "<a href='" + request.getContextPath() + href + "?currentPage=" + temp
                   + "&pageCount=" + total + "&query1=" + query1 + "&query2=" + query2
                   + "&query3=" + query3 + "&query4=" + query4 + "&query5=" + query5
                   + "&query6=" + query6 + "&query7=" + query7 + "&query8=" + query8
                   + "&query9=" + query9 + "&query10=" + query10 + "&query11=" + query11
                   + "&query12=" + query12;
	       if ("true".equals(readonly)) {//有没有readonly标签，且值为true
               url = url + "&readonly=true";
           }
	       return url;
	}
	
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getCurr() {
		return curr;
	}

	public void setCurr(int curr) {
		this.curr = curr;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getQuery1() {
		return query1;
	}

	public void setQuery1(String query1) {
		this.query1 = query1;
	}

	public String getQuery2() {
		return query2;
	}

	public void setQuery2(String query2) {
		this.query2 = query2;
	}

	public String getQuery3() {
		return query3;
	}

	public void setQuery3(String query3) {
		this.query3 = query3;
	}

	public String getQuery4() {
		return query4;
	}

	public void setQuery4(String query4) {
		this.query4 = query4;
	}

	public String getQuery5() {
		return query5;
	}

	public void setQuery5(String query5) {
		this.query5 = query5;
	}

	public String getQuery6() {
		return query6;
	}

	public void setQuery6(String query6) {
		this.query6 = query6;
	}

	public String getQuery7() {
		return query7;
	}

	public void setQuery7(String query7) {
		this.query7 = query7;
	}

	public String getQuery8() {
		return query8;
	}

	public void setQuery8(String query8) {
		this.query8 = query8;
	}

	public String getQuery9() {
		return query9;
	}

	public void setQuery9(String query9) {
		this.query9 = query9;
	}

	public String getQuery10() {
		return query10;
	}

	public void setQuery10(String query10) {
		this.query10 = query10;
	}

	public String getQuery11() {
		return query11;
	}

	public void setQuery11(String query11) {
		this.query11 = query11;
	}

	public String getQuery12() {
		return query12;
	}

	public void setQuery12(String query12) {
		this.query12 = query12;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public Logger getLogger() {
		return logger;
	}

}
