package com.irahul.hellospringannotation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irahul.hellospringannotation.Greeter;
import com.irahul.hellospringannotation.HelloWorldSpring;
import com.irahul.hellospringannotation.impl.EnglishGreeter;
import com.irahul.hellospringannotation.impl.HindiGreeter;

/**
 * Config in java instead of XML
 * Equivalent to spring-beans.xml
 * @author rahul
 *
 */
@Configuration
public class AppConfig {

	@Bean(name="hindiGreeter")
	public Greeter getHindiGreeter(){
		return new HindiGreeter();
	}
	
	@Bean(name="englishGreeter")
	public Greeter getEnglishGreeter(){
		return new EnglishGreeter();
	}
	
	@Bean(name="helloWorld")
	public HelloWorldSpring helloSpring(){
		HelloWorldSpring hws = new HelloWorldSpring();
		hws.setGreeter(getHindiGreeter());
		return hws;
	}	
}
