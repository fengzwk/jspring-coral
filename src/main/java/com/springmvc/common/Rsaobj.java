package com.springmvc.common;

import java.io.Serializable;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Rsaobj implements Serializable{
	
	private RSAPublicKey publickey;
	
	private RSAPrivateKey privateKey;

	public Rsaobj() {
		 
	}

	public Rsaobj(RSAPublicKey publickey, RSAPrivateKey privateKey) {
		this.publickey = publickey;
		this.privateKey = privateKey;
	}

	public RSAPublicKey getPublickey() {
		return publickey;
	}

	public void setPublickey(RSAPublicKey publickey) {
		this.publickey = publickey;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}
}
