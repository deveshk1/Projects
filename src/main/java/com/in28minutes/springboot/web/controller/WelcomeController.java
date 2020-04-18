package com.in28minutes.springboot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("name")  ---------> removed bcz we got the session from spring security
public class WelcomeController {

	
	@RequestMapping(value="/", method = RequestMethod.GET)
	
	public String showWelcomePage(ModelMap model){
		model.put("name",getLoggedinUserName());//
		return "welcome";
	}
	
	/*get the logged in user name from spring security
	 * the loggedin user in spring security is called as PRINCIPLE i.e who ever is loggged in is called PRINCIPLE*/
	
	private String getLoggedinUserName() 
	{
		//1st get the principle name
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); //get loggedin user bean
		
		//check if this principle is instance of specific class(USERDETAIL--->another class used to store user details)
		
		if(principal instanceof UserDetails) //from loggedin user bean we wll get the username through this UserDetails
		{
			return ((UserDetails)principal).getUsername(); // and return username
		}
		return principal.toString();
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
//		
//		boolean isValidUser = service.validateUser(name, password);
//		
//		if (!isValidUser) {
//			model.put("errorMessage", "Invalid Credentials");
//			return "login";
//		}
//		
//		model.put("name", name);
//		model.put("password", password);
//		
//		return "welcome";
//	}

}