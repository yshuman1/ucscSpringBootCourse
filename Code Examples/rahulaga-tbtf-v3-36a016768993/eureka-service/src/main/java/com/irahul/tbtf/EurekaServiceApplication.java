package com.irahul.tbtf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.access.tomcat.LogbackValve;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApplication.class, args);
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
