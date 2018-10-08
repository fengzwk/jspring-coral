package com.springmvc.test;

import org.springframework.web.util.UriComponentsBuilder;

public class UriCompenentBuilderTest {
	
	public static void main(String[] args) {
		String uriTemplate = "http://example.com/hotels/{hotel}";
		UriComponentsBuilder.fromUriString(uriTemplate);
	}
}
