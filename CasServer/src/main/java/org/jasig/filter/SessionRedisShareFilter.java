package org.jasig.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class SessionRedisShareFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest)request;
		HttpServletResponse hresponse = (HttpServletResponse)response;
		hrequest.getSession().setAttribute("SessionRedisShareFilter_flag", System.currentTimeMillis());
		System.out.println("#########################"+hrequest.getSession().getAttribute("SessionRedisShareFilter_flag"));
		chain.doFilter(hrequest, hresponse);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
