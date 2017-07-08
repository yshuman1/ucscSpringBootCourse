package com.irahul.hellospringannotation;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class) 
public class TestHelloWorldSpringNoXml extends AbstractJUnit4SpringContextTests {
	
	@Configuration
	@ComponentScan(basePackages={"com.irahul.hellospringannotation"})
	static class TestAppConfig {}
	
	@Autowired
	@Qualifier(value="helloWorldSpring")
	private HelloWorldSpring hws;
	
	@Autowired
	@Qualifier(value="hindiGreeter")
	private Greeter greeter;
	
	@Test
	public void testAutowiredHelloWorld(){
		System.out.println(hws.execute());
		System.out.println(greeter.sayHello());
		
		Assert.assertEquals("Namaste", hws.execute());
	}
}
