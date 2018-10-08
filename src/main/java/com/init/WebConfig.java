package com.init;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

//@Configuration
//@ComponentScan("com.springmvc")
public class WebConfig {
	
	/*@Bean
	public RequestMappingHandlerMapping getMappingHanding(){
		return new RequestMappingHandlerMapping();
	}
	
	@Bean("multipartResolver")
	public MultipartResolver  getMultipartResolver(){
		CommonsMultipartResolver  multipartResolver  = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1024*1024*1024);
		multipartResolver.setDefaultEncoding("UTF-8");
		return multipartResolver ;
	}
	
	@Bean
	public RequestMappingHandlerAdapter  getRequestMappingHandlerAdapter (){
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter ();
		return requestMappingHandlerAdapter;
	}*/
}
