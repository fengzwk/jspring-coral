package com.springmvc.advice;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
@Order(Integer.MAX_VALUE)
public class MultiControllerAdvice {
	
	@ModelAttribute
	public void initAttribute(){  
		System.out.println(this.getClass().getName()+" -- attributes");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		System.out.println(this.getClass().getName());
	}

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHandler(Exception e){
		System.out.println(this.getClass().getName()+"处理异常："+e.getMessage());
		return "系统异常";
	}
}
