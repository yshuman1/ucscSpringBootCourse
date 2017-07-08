package com.irahul.hellospringannotation.impl;

import org.springframework.stereotype.Component;

import com.irahul.hellospringannotation.Greeter;

/**
 * Knows Hindi
 * @author rahul
 *
 */
@Component
public class HindiGreeter implements Greeter {

	public String sayHello() {
		return "Namaste";
	}

}
