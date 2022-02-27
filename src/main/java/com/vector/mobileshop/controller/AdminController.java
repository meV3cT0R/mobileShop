package com.vector.mobileshop.controller;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vector.mobileshop.FileUploadUtil;
import com.vector.mobileshop.model.Admin;
import com.vector.mobileshop.model.Message;
import com.vector.mobileshop.model.Product;
import com.vector.mobileshop.repository.MessageRepository;
import com.vector.mobileshop.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	MessageRepository messageRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	MobileShop mobileShop;
	@RequestMapping(value="",method=RequestMethod.GET)
	public String getAdmin() {
		return "admin";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@ModelAttribute Admin admin,Model model,HttpServletRequest request) {
		if(admin.getUserName().equals("admin") && admin.getPassword().equals("admin")) {
			HttpSession session = request.getSession();
			session.setAttribute("activeAdmin", "admin");
			return getDashboard(model,request);
		}
		
		return "index";
	}
	
	@RequestMapping(value="/dashboard",method=RequestMethod.GET)
	public String getDashboard(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("activeAdmin")==null) return mobileShop.getIndexPage(model);
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
	public String deleteMessage(@ModelAttribute Message message,Model model,HttpServletRequest request) {
		System.out.println("Message Id: " +message.getId());
		messageRepo.deleteById(message.getId());
		
		return getDashboard(model,request);
		
	}
	
	@RequestMapping(value="/products",method=RequestMethod.GET)
	public String getAddProducts(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("activeAdmin")==null) return mobileShop.getIndexPage(model);
		return "products";
	}
	
	@RequestMapping(value="/products/addProduct",method=RequestMethod.POST)
	public String addProducts(Product product,@RequestParam("image") MultipartFile multipartFile) throws IOException{
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		product.setProductImage(fileName);
		Product savedProduct = productRepo.save(product);
		String uploadDir = "src/main/resources/static/images/"+savedProduct.getId();
		FileUploadUtil.saveFile(uploadDir,fileName,multipartFile);
		
		return "products";
	}
} 


