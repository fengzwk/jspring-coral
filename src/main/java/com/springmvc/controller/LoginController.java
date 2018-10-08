package com.springmvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("loginController")
@RequestMapping(value = "/login")
public class LoginController {
	
	@Value("${jdbc_username}")
	private String username;  

	@RequestMapping(value = "/getLoginInfo.do")
	public void loginInfo(HttpServletRequest request){
		Locale locale = request.getLocale();
		System.out.println(locale.getLanguage()+" -- "+locale.getDisplayName());
		
		System.out.println("username :"+username);
	}
	
	/*@RequestMapping("/{id}.do")
	public void upload(@PathVariable String id,@PathVariable String loginInfo){
		System.out.println(id+" -- "+loginInfo);
	}*/
	
	@RequestMapping("/{name:[a-z]+}-{version:\\d\\.\\d}{ext:\\.[a-z]+}.do")
	public void getVersionInfo(@PathVariable String name,@PathVariable String version,@PathVariable String ext){
		System.out.println(name+"-"+version+ext);  
	} 
	
	@RequestMapping("/${jdbc_username}/getUserInfo.do")
	public void getUserInfo(){
		System.out.println("访问成功");  
	} 
	
	@RequestMapping(value = "/sendContentType.do",consumes ={"text/plain"})
	public void sendContentType(){
		System.out.println("访问成功 : sendContentType");  
		 
	} 
	
	@RequestMapping(value = "/sendAccept.do",produces={"text/plain"})
	@ResponseBody
	public void sendAccept(HttpServletResponse response){
		System.out.println("访问成功 : sendAccept");  
		PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.write("返回的消息：123");
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	@RequestMapping(value = "/sendParam.do",params={"myParam=myValue"})
	public void sendParam(){
		System.out.println("访问成功 : sendParam");  
	}
	
	@RequestMapping(value = "/sendHttpEntity.do")
	@ResponseBody 
	public HttpEntity<String> sendHttpEntity(){
		System.out.println("访问成功 : sendHttpEntity");  
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("myresponse", "httpheader");
		
		HttpEntity<String> httpEntity = new HttpEntity<String>("hello",responseHeaders);
		return httpEntity;
	} 
}
