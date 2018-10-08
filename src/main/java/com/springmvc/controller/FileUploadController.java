package com.springmvc.controller;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

	@PostMapping("uploadFile.do")
	public void uploadFile(@RequestParam("uploadFile") MultipartFile file){
		try {
			byte[] bytes = file.getBytes();
			FileOutputStream os = new FileOutputStream(new File("D:\\uploadfile.txt"));
			
			os.write(bytes, 0, bytes.length);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("uploadFileParam.do")
	public void uploadFileParam(@RequestPart("file-data") MultipartFile file){
		 System.out.println(file);
	}
}
