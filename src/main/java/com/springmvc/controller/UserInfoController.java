package com.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springmvc.common.User;

@Controller
@RequestMapping("/user")
//@SessionAttributes("user")
public class UserInfoController {
	
	@InitBinder //请求进入前的参数类型转换
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
        
    }

	@RequestMapping("/update.do")
	public void update(User user,BindingResult result){
		if(!result.hasErrors()){
			System.out.println(user);
		}
	}
	
	@RequestMapping("/add.do")
	public void add(User user){
		System.out.println(user);
	}
	
	@RequestMapping("/requestBody.do")
	public void requesteBody(@RequestBody(required=false) User user){
		/**
		 * @RequestBody 注解的使用注意事项：
		 * 1、必须以post方式提交数据，同时content-type=applicatioin/json。
		 *    当将required=false时，可以使用GET方式提交，此时@RequestBody注解的属性user将为Null
		 *    
		 * 2、application/json类型已经被w3c遗弃，所以在表单的encType=application/json将会被替换成默认类型
		 *   不会生效，不建议在表单上提交带有RequestBody注解的Controller
		 *   
		 * 3、必须引入jackson的jar包；jackson-databind,jackson-annotation,jackson-core
		 * 
		 * 4、必须开启<mvc:annotation-driven/>，注解驱动
		 */
		System.out.println(user);
	}
}
