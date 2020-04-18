package com.in28minutes.springboot.web.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")  // i want this to hand;e error so we are adding "error" keyword //this redirects to error.jsp

public class ErrorController {
	
	//we can add handlers for specific exceptions using below code
	
	@ExceptionHandler(Exception.class)
	
	
	public ModelAndView handleException(HttpServletRequest request, Exception ex)
	{
	/*we want this method to populate the error details and redirect it to error view
	 * instead of using spring error page we want a custom error view
	 * 
	 * ModelAndView = Model + View   --> we cans store  both Model and View Details
	 * */
		
	ModelAndView mv = new ModelAndView();
	mv.addObject("exception",ex.getStackTrace()); // exception details that has happend
	mv.addObject("url",request.getRequestURL()); //add object to model indicating which URL caused the EXeption
	mv.setViewName("error");// also we wud set the view name to error page
	return mv;
		
	}

}

/*What we have done here is , a simple controller method handleException which gets details in param
and when exception is thrown it will enter the method  ,,,,and we are putting values in model and redirecting user to error page*/ 