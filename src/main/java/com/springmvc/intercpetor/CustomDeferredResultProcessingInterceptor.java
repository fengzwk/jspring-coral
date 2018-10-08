package com.springmvc.intercpetor;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.DeferredResultProcessingInterceptor;

public class CustomDeferredResultProcessingInterceptor implements DeferredResultProcessingInterceptor{

	@Override
	public <T> void beforeConcurrentHandling(NativeWebRequest request, DeferredResult<T> deferredResult)
			throws Exception {
		System.out.println("invoke beforeConcurrentHandling!!");
		DeferredResultProcessingInterceptor.super.beforeConcurrentHandling(request, deferredResult);
	}

	@Override
	public <T> void preProcess(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
		System.out.println("invoke preProcess!!");
		DeferredResultProcessingInterceptor.super.preProcess(request, deferredResult);
	}

	@Override
	public <T> void postProcess(NativeWebRequest request, DeferredResult<T> deferredResult, Object concurrentResult)
			throws Exception {
		System.out.println("invoke postProcess!!");
		DeferredResultProcessingInterceptor.super.postProcess(request, deferredResult, concurrentResult);
	}

	@Override
	public <T> boolean handleTimeout(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
		System.out.println("invoke handleTimeout!!");
		return DeferredResultProcessingInterceptor.super.handleTimeout(request, deferredResult);
	}

	@Override
	public <T> boolean handleError(NativeWebRequest request, DeferredResult<T> deferredResult, Throwable t)
			throws Exception {
		System.out.println("invoke handleError!!");
		return DeferredResultProcessingInterceptor.super.handleError(request, deferredResult, t);
	}

	@Override
	public <T> void afterCompletion(NativeWebRequest request, DeferredResult<T> deferredResult) throws Exception {
	    System.out.println("invoke afterCompletion!!");
		DeferredResultProcessingInterceptor.super.afterCompletion(request, deferredResult);
	}

}
