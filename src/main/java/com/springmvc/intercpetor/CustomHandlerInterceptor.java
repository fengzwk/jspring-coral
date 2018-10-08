package com.springmvc.intercpetor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//AsyncHandlerInterceptor是HandlerInterceptor的子接口，可以拦截AsyncRequest
public class CustomHandlerInterceptor implements AsyncHandlerInterceptor{ 

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("invoke preHandle"); 
		return AsyncHandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("invoke postHandle"); 
		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("invoke afterCompletion"); 
		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * AsyncRequest不能调用postHandler和afterCompletion，所以必须实现这个方法，用于postProcess
	 */
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("invoke afterConcurrentHandlingStarted"); 
		AsyncHandlerInterceptor.super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
}
