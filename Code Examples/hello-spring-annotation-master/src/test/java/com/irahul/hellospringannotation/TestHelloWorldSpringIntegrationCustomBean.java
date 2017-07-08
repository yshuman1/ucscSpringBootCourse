package com.irahul.hellospringannotation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:spring-beans-annotation.xml" })
public class TestHelloWorldSpringIntegrationCustomBean extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	@Qualifier(value="helloWorldSpring")
	private HelloWorldSpring hws;
	
	@Autowired
	@Qualifier("testGreeter")
	private Greeter greeter;
	
	@Test
	public void testGreeterHelloWorld(){
		System.out.println("Original message:"+hws.execute());
		Assert.assertEquals("Namaste", hws.execute());
		
		hws.setGreeter(greeter);
		
		System.out.println(greeter.sayHello());
		Assert.assertEquals("Did you test?", hws.execute());
	}
}
