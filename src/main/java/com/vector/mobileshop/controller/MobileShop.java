package com.vector.mobileshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MobileShop {
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getIndexPage() {
		return "index";
	}
	
	@RequestMapping(value="/about",method=RequestMethod.GET)
	public String getAboutPage() {
		return "about";
	}
	
	@RequestMapping(value="/contact",method=RequestMethod.GET)
	public String getContactPage() {
		return "contact";
	}
}
