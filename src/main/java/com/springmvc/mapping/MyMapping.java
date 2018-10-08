package com.springmvc.mapping;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class MyMapping extends RequestMappingHandlerMapping {

	  
	@Override
	protected RequestCondition<?> getCustomMethodCondition(Method method) {
		// TODO Auto-generated method stub
		Parameter[] parameters = method.getParameters();
		System.out.println(parameters);
		return super.getCustomMethodCondition(method);
	}
}
