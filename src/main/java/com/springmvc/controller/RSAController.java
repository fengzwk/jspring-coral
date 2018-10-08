package com.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springmvc.common.Rsaobj;
import com.springmvc.rsa.RSAUtil;

@Controller
public class RSAController {
	
	@PostMapping("/rsaRequest.do")
	@ResponseBody
	public Map<String,Object> rsaRequest(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} 
		
	    System.out.println("----------decrypt------------");  
	    String encrypt_password = request.getParameter("password");  
	    String unencrypt_password = request.getParameter("unpassword");  
	    System.out.println("未经过加密的数据为:unencrypt_password:"+unencrypt_password);  
	    System.out.println("前台加密后的数据为:encrypt_password:"+encrypt_password);  
	      
	    Object object = request.getSession().getAttribute("rsaobj");  
	      
	    if(object!=null){  
	        Rsaobj rsaobj = (Rsaobj) object;  
	             
	        //从 session 中获取私钥  
	        RSAPrivateKey privateKey = rsaobj.getPrivateKey(); 
	        RSAPublicKey publickey = rsaobj.getPublickey();
	        
	        //前端js的RSA默认在加密之前会将原字符翻转，再编译，所以这里需要将解密内容进行翻转
	        String decrypt_password = RSAUtil.decryptStringByJs(privateKey, encrypt_password);
			 
	        //对字符串进行URL解码的编码处理  
	        try {
				decrypt_password = java.net.URLDecoder.decode(decrypt_password,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}  
	          
	        System.out.println("解密后的数据为："+ decrypt_password);  
	        
	        
	        Map<String, Object> map = new HashMap<String, Object>();  
	        map.put("success", true);  
	        map.put("content", decrypt_password);  
	        return map;
	        
		}else{
			Map<String, Object> map = new HashMap<String, Object>();  
	        map.put("success", false);  
	        map.put("content", null);  
	        return map;
		}
	}
	
	@GetMapping("/rsaLogin.do")
	public String rsaLogin(HttpServletRequest request){
		RSAPublicKey publicKey = RSAUtil.getDefaultPublicKey();  
        RSAPrivateKey privateKey = RSAUtil.getDefaultPrivateKey();  
        Rsaobj rsaobj =  new Rsaobj(publicKey, privateKey);  
          
          
        String publicExponent_16 =  new BigInteger(publicKey.getPublicExponent().toString(), 10).toString(16);  
        String modulus_16 = new BigInteger(publicKey.getModulus().toString(), 10).toString(16);  
          
        System.out.println("公钥 16 getModulus:"+modulus_16);  
        System.out.println("公钥 16 getPublicExponent:"+publicExponent_16);  
          
          
        System.out.println("私钥  getModulus  ："+privateKey.getModulus());  
        System.out.println("私钥  getPrivateExponent   ："+privateKey.getPrivateExponent());  
          
          
        //将私钥的 Exponent  Modulus存放至变量中，以便后来构造私钥  
        //MyUtil.PrivateExponent = privateKey.getPrivateExponent();  
        //MyUtil.PrivateModulus = privateKey.getModulus();  
          
          
        request.getSession().setAttribute("modulus_16",modulus_16);  
        request.getSession().setAttribute("publicExponent_16",publicExponent_16);  
        request.getSession().setAttribute("rsaobj",rsaobj);  
          
       return "rsaLogin";   
	}

}
