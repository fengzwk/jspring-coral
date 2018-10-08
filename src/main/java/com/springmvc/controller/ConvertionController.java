package com.springmvc.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.springmvc.common.User;

@Controller
public class ConvertionController {	
	
	@RequestMapping("/owners/{ownerId}/pets/{petId}")
	public void MatrixVariable(@MatrixVariable MultiValueMap<String, String> matrixVars,
	        @MatrixVariable(pathVar="petId") MultiValueMap<String, String> petMatrixVars){
		 System.out.println("ownerId --- "+matrixVars);
		 System.out.println("petId --- "+petMatrixVars);
	}
	
	@RequestMapping("/test.do")
	public void test(){
		System.out.println("访问成功test");
	}

    @RequestMapping("/index.do")
    public void index(Long id, HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

    	 List<String> uList = new ArrayList<String>();//存储所有url集合  
    	    WebApplicationContext wac = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);//获取上下文对象  
    	    RequestMappingHandlerMapping bean = wac.getBean(RequestMappingHandlerMapping.class);//通过上下文对象获取RequestMappingHandlerMapping实例对象  
    	    
    	    Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();  
    	    
    	    for (RequestMappingInfo rmi : handlerMethods.keySet()) {  
    	        PatternsRequestCondition prc = rmi.getPatternsCondition();  
    	        Set<String> patterns = prc.getPatterns();  
    	        for (String uStr : patterns) {  
    	            System.out.println(uStr);
    	        }  
    	    }  
    }
    
    @GetMapping("/pet.do")
    public void setupForm(@RequestParam("petId") int petId,
    					  @RequestParam("birthDay") Date birthDay,
    					  @RequestParam("newDate") LocalDate newDate,
    					  @RequestParam("username") Optional<String> username){
    	System.out.println(petId);
    	System.out.println(birthDay);
    	System.out.println(newDate);
    	System.out.println(username.orElse("admin"));
    }
    
    @GetMapping("/pets.do")
    public void setupForms(@RequestParam  Map<String,Object> map){
    	 System.out.println(map.toString());
    }
    
    @GetMapping("/requestHeader.do")
    public void requestHeader(
    			@RequestHeader("Accept-Encoding") String encoding){ //如果请求头中没有Accep-Encoding，则无法请求成功
    	 System.out.println(encoding);
    }
    
    @GetMapping("/requestHeaders.do")
    public void requestHeaders(
    			@RequestHeader Map<String,Object> map){  
    	 System.out.println(map.toString());
    }
    
    @GetMapping("/requestHeaderSeparated.do")
    public void requestHeaderSeparated(
    			@RequestHeader("Accept") List<String> accept){  
    	accept.forEach(System.out::println);
    }
    
    @GetMapping("/cookie.do")
    public void cookie(
    		@CookieValue("JSESSIONID") String cookie){  
    	System.out.println(cookie);;
    }
    
    @GetMapping("/processSubmit.do")
    public void processSubmit(@ModelAttribute User user){  
    	System.out.println(user);
    }
    
    @PostMapping("/getInfo.do")
    public void getUser(@ModelAttribute User user, BindingResult result){
    	
		if(!result.hasErrors()){
			System.out.println(user);
		}else{
			System.out.println("数据绑定出错！！！");
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
    }
    
    @RequestMapping("/getSessionUser.do")
    public void  getSessionUser(@SessionAttribute User user){
    	System.out.println(user);
    }
}
