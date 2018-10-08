package com.springmvc.controller;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {
	
	/*@ExceptionHandler(Exception.class)
	public HttpEntity<String> exceptionHandler(Exception e){
		System.out.println("处理异常："+e.getMessage());
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.parseMediaType("text/html;charset=utf-8"));
		 HttpEntity<String> httpEntity = new HttpEntity<>("系统异常");
		return httpEntity;
	}*/
	
	@GetMapping("/getExceptionInfo.do") 
	public void getExceptionInfo(){
		int a = 1/0;
	}

}
