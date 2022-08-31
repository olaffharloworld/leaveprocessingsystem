package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.validator.UserValidator;

@Controller

public class ManageStaffController {
	
	@Autowired
	private UserService service;
	@Autowired
	private RoleService rservice;
	@Autowired
	private UserValidator uValidator;

	@InitBinder("user")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.addValidators(uValidator);
	}
	
	@RequestMapping("/")
	public String indexPage(Model model)
	{
		return "index";
	}
	@RequestMapping("/list")
	public String viewAllStaff(Model model)
	{
		List<User> listUsers=service.listAll();
		model.addAttribute("listUsers", listUsers);
		return "stafflist";
	}
	@RequestMapping("/new")
	public String showNewUserForm(Model model)
	{
		User user=new User();
		model.addAttribute("user", user);
		List<Role> listRoles=rservice.listAll();
		model.addAttribute("listRoles",listRoles);
		return "newuser";
	}
	
	
	@RequestMapping(value="/save",method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") @Valid User user,BindingResult result)
	{
		if (result.hasErrors()) {
			return "newuser";
			}
		service.save(user);
		return "forward:/list";
	}
	
	@RequestMapping(value="/edit/{userId}",method=RequestMethod.GET)
	public ModelAndView showEditUserForm(@PathVariable("userId") String userId)
	{
		ModelAndView mav = new ModelAndView("edit_user");
		User user = service.get(userId);
		List<Role> listRoles=rservice.listAll();
		mav.addObject("listRoles", listRoles);
		mav.addObject("user", user);		
		return mav;
	}
	@RequestMapping(value="/edit/{userId}",method=RequestMethod.POST)
	public ModelAndView editUser(@PathVariable("userId") String userId,@ModelAttribute @Valid User user)
	{
		ModelAndView mav = new ModelAndView("forward:/list");
		String message="User was successfully updated";
		System.out.println(message);
		service.changeUser(user);
		return mav;
	}
	
	
	@RequestMapping(value="/delete/{userId}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable("userId") String userId)
	{
		service.delete(userId);
		
		return "redirect:/list";
	}

}
