package com.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonView;
import com.springmvc.common.User;
import com.springmvc.common.User.BaseView;

@Controller
public class CommonController {
	

	@GetMapping("/getParams.do")
	public void getCustRequestParams(@RequestAttribute(required=false) User user){
		System.out.println("commoncontroller -- "+user);
	} 
	
	@GetMapping("redirect.do")
	public String redirect(@ModelAttribute User user,RedirectAttributes redirectModel){
		System.out.println("redirect -- "+user);
		
		redirectModel.addAttribute("age", user.getAge());
		redirectModel.addAttribute("address", user.getAddress());
		redirectModel.addAttribute("username", user.getUsername());
		return "redirect:/processSubmit.do";
	}
	
	/*@GetMapping("/{petId}/{ownerId}.do")
	public String redirectPath(@PathVariable String petId,@PathVariable String ownerId){
		System.out.println("redirectPath : petId = "+petId+", ownerId = "+ownerId);
		return "redirect:/redirect/{ownerId}/{petId}.do";
	}
	
	@GetMapping("/redirect/{ownerId}/{petId}.do")
	public void redirectedPath(@PathVariable String petId,@PathVariable String ownerId){
		System.out.println("redirectedPath : petId = "+petId+", ownerId = "+ownerId);
	}*/ 
	
	@GetMapping("/getResponseBody.do")
	@ResponseBody
	public User getResponseBody(){
		User user = new User();
		user.setAddress("tianjin");
		user.setUsername("admin");
		user.setAge(34);
		user.setBirthday(new Date());
		return user;
	}
	
	@GetMapping("/getJsonView.do")
	//将返回结果序列化到responsebody中，此处不会跳转视图，而是将User写到response中直接返回
	//类似ajax请求
	@ResponseBody  
	@JsonView(BaseView.class)
	public User getJsonView(){
		User user = new User();
		user.setAddress("tianjin");
		user.setUsername("admin");
		user.setAge(34);
		user.setBirthday(new Date());
		return user;
	}
	
	@GetMapping("/testLogin.do")
	@ResponseBody
	public String login(){
		System.out.println("执行testLogin.do");
		return "login";
	}
}
