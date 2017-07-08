package com.irahul.hellospringannotation;

import org.springframework.stereotype.Component;

/**
 * A test greeter. Using a mock is also an option (like EasyMock)
 * @author rahul
 *
 */
@Component
public class TestGreeter implements Greeter {

	public String sayHello() {
		return "Did you test?";
	}

}
