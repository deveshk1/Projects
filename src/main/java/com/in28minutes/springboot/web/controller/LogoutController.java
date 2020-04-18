package com.in28minutes.springboot.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("name")  ---------> removed bcz we got the session from spring security
public class LogoutController {

	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	
	public String logout(HttpServletRequest request,HttpServletResponse response){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
			if(authentication!= null) //if user is already authenticated then logout user
			{
				
				new SecurityContextLogoutHandler().logout(request, response, authentication);
			}
		
		
		return "redirect:/"; //after logout redirect to home page
	}
	
}

/*In this method we are passign request and response , we are getting auth details of user and if not null
the we are using SecurityContextLogoutHandler and we are loggoin user out and to home page*/