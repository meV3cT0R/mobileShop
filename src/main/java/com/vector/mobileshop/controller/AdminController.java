package com.vector.mobileshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vector.mobileshop.model.Admin;
import com.vector.mobileshop.model.Message;
import com.vector.mobileshop.repository.MessageRepository;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	MessageRepository messageRepo;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getAdmin() {
		return "admin";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute Admin admin,Model model) {
		if(admin.getUserName().equals("admin") && admin.getPassword().equals("admin"))
			return getDashboard(model);
		
		return "index";
	}
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String getDashboard(Model model) {
		try {
			List<Message> listOfMessage = messageRepo.findAll();
			listOfMessage.stream().forEach(value->System.out.println(value.getMessageData()));
			model.addAttribute("listOfMessage", listOfMessage);
			return "adminDashboard";
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return "admin";
	}
	
	@RequestMapping(value="/dashboard/deleteMessageData", method=RequestMethod.POST)
	public String deleteMessage(@ModelAttribute Message message,Model model) {
		System.out.println("Message Id: " +message.getId());
		messageRepo.deleteById(message.getId());
		
		return getDashboard(model);
		
	}
} 


