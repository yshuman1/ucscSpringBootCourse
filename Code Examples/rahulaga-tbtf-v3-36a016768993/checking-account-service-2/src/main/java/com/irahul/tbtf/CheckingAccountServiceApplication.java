package com.irahul.tbtf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.irahul.tbtf.externalresource.UserServiceResponseErrorHandler;

import ch.qos.logback.access.tomcat.LogbackValve;

/**
 * Start application
 * @author rahul
 *
 */

@SpringBootApplication
public class CheckingAccountServiceApplication {
	@Value("${userService.connecttimeout}")
	private int connectTimeout;
	
	@Value("${userService.readtimeout}")
	private int readTimeout;

	public static void main(String[] args) {
		SpringApplication.run(CheckingAccountServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate userServiceRestTemplate(UserServiceResponseErrorHandler errorHandler){
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		requestFactory.setConnectTimeout(connectTimeout);
		RestTemplate template = new RestTemplate(requestFactory);
		template.setErrorHandler(errorHandler);
		return template;
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory(){
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
		LogbackValve logbackValve = new LogbackValve();
		logbackValve.setFilename("logback-access.xml");
		tomcat.addContextValves(logbackValve);
		return tomcat;
	}
}
