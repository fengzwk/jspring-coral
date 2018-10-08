package com.springmvc.controller;

import java.util.Date;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.springmvc.common.User;

@Controller
public class ModelAttributeController {

	@ModelAttribute()
	public void populateModel(@RequestParam String number,Model model){
		User user = new User();
		user.setAddress("tianjin");
		user.setUsername("admin");
		user.setAge(34);
		user.setBirthday(new Date());
		model.addAttribute("adminUser",user);
	}
	
	@ModelAttribute(name="updateAdminUser")
	public void updateModel(@RequestParam String number,Model model){
		User user = new User();
		user.setAddress("shanghai");
		user.setUsername("root");
		user.setAge(67);
		user.setBirthday(new Date());
		model.addAttribute(user); //这里增加的user,默认的modelAttribute的name是user,因为没有返回值，所以updateAdminUser不生效
	}
	
	@ModelAttribute(name="subAdminUser")
	public User addUser(@RequestParam String number,Model model){
		User user = new User();
		user.setAddress("beijing");
		user.setUsername("subAdmin");
		user.setAge(56);
		user.setBirthday(new Date());
		return user;
	}
	
	@GetMapping("/getModelAttributesInfo.do")
	public void getModelAttributesInfo(@ModelAttribute(name="subAdminUser") User subAdminUser, 
										@ModelAttribute(name="adminUser") User adminUser,
										User user, //将会获得updateModel方法放入的user
										Model model){
		 System.out.println("subAdminUser : "+subAdminUser);
		 System.out.println("adminUser : "+adminUser);
	}
}
