package com.irahul.hellospringannotation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.irahul.hellospringannotation.HelloWorldSpring;

/**
 * Load beans by creating context from XML
 * @author rahul
 *
 */
public class TestHelloWorldSpring {

	@SuppressWarnings("resource")
	@Test
	public void testHelloWorldBeans(){
		ApplicationContext context = 
				new ClassPathXmlApplicationContext("spring-beans-annotation.xml");
 
		HelloWorldSpring obj = (HelloWorldSpring) context.getBean("helloWorldSpring");		
		
		System.out.println(obj.execute());			
		
		Assert.assertEquals("Namaste", obj.execute());
	}
}
