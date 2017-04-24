package edu.zjut.tempest.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 用户登录验证过滤器类
 * @author Tempest
 *
 */
public class LoginValidateFilter implements Filter {
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		javax.servlet.http.HttpServletRequest httpRequest=(javax.servlet.http.HttpServletRequest)request;
			
		if (httpRequest.getSession().getAttribute("user")==null) {
			((javax.servlet.http.HttpServletResponse)response).sendRedirect("../index.jsp");
		} else {
			chain.doFilter(request, response);//继续执行后续请求处理操作（执行Servlet）
		}
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
