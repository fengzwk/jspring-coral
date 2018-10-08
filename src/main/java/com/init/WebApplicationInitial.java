package com.init;

import javax.servlet.Filter;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import com.springmvc.filter.CommonFilter;

public class WebApplicationInitial{ // extends AbstractDispatcherServletInitializer

	/*@Override
	protected WebApplicationContext createServletApplicationContext() {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(WebConfig.class);
		applicationContext.getResource("classpath:appconfig.properties");
		return applicationContext;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"*.do"};
	}

	@Override
	protected WebApplicationContext createRootApplicationContext() {
		return null;
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new CommonFilter()};
	}*/
}
