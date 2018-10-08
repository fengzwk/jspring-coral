package com.springmvc.test;

import java.util.Locale;

public class TestMain01 {

	public static void main(String[] args) {
		Locale[] availableLocales = Locale.getAvailableLocales();
		for (Locale locale : availableLocales) {
			System.out.println(locale.getLanguage()+" -- "+locale.getDisplayName());
		}
	}
}
