package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.access.tomcat.LogbackValve;

@EnableConfigServer
@SpringBootApplication
@EnableEurekaClient
public class ConfigServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServiceApplication.class, args);
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
