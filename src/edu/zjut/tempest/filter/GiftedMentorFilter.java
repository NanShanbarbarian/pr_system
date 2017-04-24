package edu.zjut.tempest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import edu.zjut.tempest.entity.ActivityManage;
import edu.zjut.tempest.service.ActivityManageService;

public class GiftedMentorFilter implements Filter {

	private ActivityManageService amService;
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		javax.servlet.http.HttpServletRequest httpRequest = (javax.servlet.http.HttpServletRequest)request;
		
		ActivityManage am = amService.getByName("优才导师计划");
		if (am!=null && am.getIsOpen() == 0) {   //如果优才导师计划关闭
			((javax.servlet.http.HttpServletResponse)response).sendRedirect(httpRequest.getContextPath()+"/content/mentor_close.jsp");
		} else {
			chain.doFilter(request, response);//继续执行后续请求处理操作（执行Servlet）
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext sc = fConfig.getServletContext(); 
        XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        
        if(cxt != null && cxt.getBean("amService") != null && amService == null)
        	amService = (ActivityManageService) cxt.getBean("amService");        
	}

}
