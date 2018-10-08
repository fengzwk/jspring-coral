package com.springmvc.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

@Controller
public class SimpleController {
	
	@RequestMapping("/getInfo01.do")
	public void getInfo01(WebRequest webRequest, NativeWebRequest nativeWebRequest){
		System.out.println("=======");
	}
	
	@RequestMapping("/getInfo02.do")
	public void getInfo02(HttpMethod httpMethod,ZoneId zoneId){
		System.out.println("=======");
	}
	
	@SuppressWarnings("resource")
	@RequestMapping("/getInfo03.do")
	public void getInfo03(InputStream is){
		System.out.println("=======");
		byte[] bs = new byte[10];
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(new File("D:\\output.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		while(true){
			try {
				int len = is.read(bs,0,bs.length);
				if(len == -1){
					break;
				}
				
				os.write(bs, 0, len);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
		if(null != os){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(null != is){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
