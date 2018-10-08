package com.springmvc.advice;

import org.springframework.core.annotation.Order;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice
@Order(1) //定义@ControllerAdvice的执行顺序，值小的优先执行
public class MultiControllerAdvice01 {
	
	@ModelAttribute
	public void initAttribute(){  
		System.out.println(this.getClass().getName()+" -- attributes");
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		System.out.println(this.getClass().getName());
	}

	/**
	 * 首先会根据ControllerAdvice的顺序查找，在同一个ControllerAdvice中，
	 * 会比对异常类型，类型相同的方法会被执行
	 * 如果找不到异常类型相等的方法，会执行Exception.class的异常处理方法
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@Order(1)
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
	@Order(Integer.MAX_VALUE)
	@ResponseBody
	public String arithmeticExceptionHandler(Exception e){
		System.out.println(this.getClass().getName()+" -- arithmeticExceptionHandler处理异常："+e.getMessage());
		return "系统异常";  
	}
}
