package com.vector.mobileshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vector.mobileshop.model.Message;
import com.vector.mobileshop.repository.MessageRepository;

@Controller
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	MessageRepository messageRepository;
	
	@RequestMapping(value="/messageSubmit",method=RequestMethod.POST)
	public String submitForm(@ModelAttribute Message message,Model model) {
		messageRepository.save(message);
		
		return new MobileShop().getIndexPage(model);
	}
}
