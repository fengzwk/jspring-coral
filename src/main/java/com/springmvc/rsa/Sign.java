package com.springmvc.rsa;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Sign{
	
	public static byte[] sign(String content,PrivateKey key,String algorithm){
		
		try {
			Signature signature = Signature.getInstance(algorithm);
			signature.initSign(key);
			signature.update(content.getBytes("UTF-8"));
			
			return signature.sign();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static boolean verify(byte[] content,byte[] signature,PublicKey key,String algorithm){
		try {
			Signature stool = Signature.getInstance(algorithm);
			stool.initVerify(key);
			stool.update(content);
			return stool.verify(signature);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return false;
	}
	
	public static void main(String[] args) throws Exception{
		RSAPrivateKey privatekey = RSAUtil.getDefaultPrivateKey();
		RSAPublicKey publickey = RSAUtil.getDefaultPublicKey();
		
		String str = "数字签名";
		byte[] sign = sign(str, privatekey, "MD5withRSA");
		
		 byte[] bs = new byte[128]; //构造一个假数据
		 for (int i=0; i<bs.length; i++) {
			bs[i] = (byte)i;
		 } 
		 
		boolean verify = verify(str.getBytes("utf-8"), sign, publickey, "MD5withRSA");
		System.out.println(verify);
	}
}
