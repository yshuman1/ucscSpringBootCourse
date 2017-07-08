package com.irahul.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller with GET /hello
 * @author rahul
 *
 */
@RestController
public class HelloController {

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	public String hello(){
		return "Hello World!";
	}
	
	@RequestMapping(value="/hello", method=RequestMethod.POST)
	public String helloPost(){
		return "POST Hello World!";
	}
}
