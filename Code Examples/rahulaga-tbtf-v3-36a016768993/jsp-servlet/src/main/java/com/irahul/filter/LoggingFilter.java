package com.irahul.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {

		//request part of the filter
		String ipAddress = req.getRemoteAddr();
		//req.getParameterMap().put("name",new String {"bar"});
		//req.setAttribute(arg0, arg1);
		
		logger.info("Logging request in the filter IP " + ipAddress);
		
		//servlet
		chain.doFilter(req, resp);
		
		//response part of the filter
		logger.info("Response logging");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
