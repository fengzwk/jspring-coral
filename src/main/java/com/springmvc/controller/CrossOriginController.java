package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CrossOriginController {

	@GetMapping("/getCORSInfo.do")
	@CrossOrigin(value ="http://localhost:8080/springmvc",allowedHeaders = {"springmvc"})
	@ResponseBody
	public String getCORSInfo(){
		System.out.println("CORSInfo访问成功！！");
		return "corsInfo";
	}
}
