package com.irahul.tbtf.externalresource;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;

@Component
public class UserServiceResponseErrorHandler extends DefaultResponseErrorHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		logger.error("User Service error "+response.getStatusCode());
		//just logging for now. You can throw your own exceptions if you want.
	}
}
