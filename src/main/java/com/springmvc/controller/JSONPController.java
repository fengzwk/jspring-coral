package com.springmvc.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.common.User;

@Controller
public class JSONPController {

	@RequestMapping("/getJSONPInfo.do")
	@ResponseBody
	public User getJSONPInfo(){
		System.out.println("访问成功！！");
		User user = new User();
		user.setAddress("tianjin");
		user.setUsername("admin");
		user.setAge(34);
		user.setBirthday(new Date());
		return user;
	}
}
