package com.irahul.hellospringannotation.impl;

import org.springframework.stereotype.Component;

import com.irahul.hellospringannotation.Greeter;

/**
 * Knows English
 * @author rahul
 *
 */
@Component
public class EnglishGreeter implements Greeter {

	public String sayHello() {
		return "Hello World!";
	}

}
