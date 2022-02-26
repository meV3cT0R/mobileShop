package com.vector.mobileshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vector.mobileshop.model.Product;
import com.vector.mobileshop.repository.ProductRepository;

@Controller
public class MobileShop {
	@Autowired
	ProductRepository productRepo;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getIndexPage(Model model) {
		List<Product> listOfProduct = productRepo.findAll();
		model.addAttribute("listOfProduct",listOfProduct);
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
	
	@RequestMapping(value="/mobileDescription",method=RequestMethod.GET)
	public String getMobileDescriptionPage(@ModelAttribute Product phone,Model model) {
		model.addAttribute("phone",phone);
		return "mobileDescription";
	}
}
