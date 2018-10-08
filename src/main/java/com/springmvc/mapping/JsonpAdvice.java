package com.springmvc.mapping;

import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice{
	
	public JsonpAdvice(){
		super("callback");
	}

}
