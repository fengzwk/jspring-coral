package com.springmvc.advice;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
@Order(20)
public class MultiControllerAdvice02 {
	
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
	
	/**
	 * 首先会根据ControllerAdvice的顺序查找，在同一个ControllerAdvice中，
	 * 会比对异常类型，类型相同的方法会被执行
	 * 如果找不到异常类型相等的方法，会执行Exception.class的异常处理方法
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ArithmeticException.class)
	@ResponseBody
	public String arithmeticExceptionHandler(Exception e){
		System.out.println(this.getClass().getName()+" -- arithmeticExceptionHandler处理异常："+e.getMessage());
		return "系统异常";
	}
}
