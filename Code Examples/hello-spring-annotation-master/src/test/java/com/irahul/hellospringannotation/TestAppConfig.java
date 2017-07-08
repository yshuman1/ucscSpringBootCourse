package com.irahul.hellospringannotation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.irahul.hellospringannotation.config.AppConfig;

/**
 * Test the AppConfig
 * @author rahul
 *
 */
public class TestAppConfig {
	@SuppressWarnings("resource")
	@Test
	public void testHelloWorldBeans(){
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
 
		HelloWorldSpring obj = (HelloWorldSpring) context.getBean("helloWorld");		
		
		System.out.println(obj.execute());			
		
		Assert.assertEquals("Namaste", obj.execute());
	}
}
