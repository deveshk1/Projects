package com.in28minutes.springboot.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.LoginService;
import com.in28minutes.springboot.web.service.TodoService;

@Controller
@SessionAttributes("name")
public class TodoController {

	@Autowired
	TodoService service;

	@InitBinder
	public void initBinder(WebDataBinder binder) //webdatabinder is there to support the format
	{
	//Date format- dd/mm/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); //simpledateformat is inbuilt for date binding
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	
	
	}
	// to add show todo list
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = (String) model.get("name");
		model.put("todos", service.retrieveTodos(name));
		return "list-todos";
	}
// to SHOW ADD TODO PAGE
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, (String) model.get("name"),
				"Default Desc", new Date(), false));
		return "todo";
	}

	// TO DELETE A TODO
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		service.deleteTodo(id);
		return "redirect:/list-todos";
	}

	// TO UPDATE A TODO GET METHOOD, 
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) 
	//to pass data from controller to view, use ModelMap....step19
	
	{
		
		/*get details of tobe updated TODO using id and create retriveTodo(int id) in....step19
		todoService.java*/

		Todo todo = service.retrieveTodo(id);
		//to show these details on screen we need to add in modal map  ....step19
		
		model.put("todo", todo); //once we put value to mode,....step19
		return "todo";  // send the user to  todo page to show details of user....step19
	}

	//TO UPDATE A TODO POST METHOOD-----------to handle the update with new desc, then create updateTodo in todoservice.java
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}
		
		todo.setUser((String) model.get("name"));
		
		service.updateTodo(todo);

		return "redirect:/list-todos";
	}

	// TO ADD A TODO
	// BEAN-FORM AND FORM-BEAN DOUBLE BINDING TAKES PALCE in Controller
	/*
	 * here, we have enabled validation for 10 characters using @Valid annotation
	 * to know if validaiton was success, we use BindingResult  whcih returns TRUE/FALSE
	 * */
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) { // if result has error, user goes back to todo page i.e add-todo page
			return "todo";
		}

		service.addTodo((String) model.get("name"), todo.getDesc(), todo.getTargetDate(),  //getTargetDate() to enter custom date in addTODO
				false);
		return "redirect:/list-todos";
	}
}