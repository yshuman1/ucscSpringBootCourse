package com.irahul.hellospringannotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A Spring version
 * @author rahul
 *
 */
@Component
public class HelloWorldSpring {
	@Autowired
	@Qualifier("hindiGreeter")
	private Greeter greeter;
	
	@Autowired
	@Qualifier("englishGreeter")
	private Greeter englishGreeter;
	
	public void setGreeter(Greeter greeter) {
		this.greeter = greeter;
	}
	
	public String execute(){
		return greeter.sayHello();
	}
}
