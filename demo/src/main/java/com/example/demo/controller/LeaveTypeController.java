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

import com.example.demo.model.LeaveType;
import com.example.demo.service.LeaveTypeService;
import com.example.demo.validator.LeaveTypeValidator;



@Controller
public class LeaveTypeController {

	@Autowired 
	private LeaveTypeService service;
	@Autowired
	private LeaveTypeValidator ltValidator;

	@InitBinder("leavetype")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.addValidators(ltValidator);
	}
	
	@RequestMapping("/leavetype")
	public String ViewLeaveType(Model model)
	{
		List<LeaveType> listLeaveTypes=service.listAll();
		model.addAttribute("listLeaveTypes", listLeaveTypes);
		
		return "leavelist";
	}
	
	@RequestMapping("/newleavetype")
	public String showNewLeaveTypeForm(Model model)
	{
		LeaveType leavetype= new LeaveType();
		model.addAttribute("leavetype",leavetype);
		
		return "newleavetype";
	}
	
	@RequestMapping(value="/saveleavetype",method = RequestMethod.POST)
	public String saveLeaveType(@ModelAttribute("leavetype") @Valid LeaveType leavetype,BindingResult result)
	{
		if (result.hasErrors()) {
			return "newleavetype";
			}
		service.save(leavetype);
		return "forward:/leavetype";
	}
	
	@RequestMapping(value="/editleavetype/{typeId}",method=RequestMethod.GET)
	public ModelAndView showEditUserForm(@PathVariable("typeId") String typeId)
	{
		ModelAndView mav = new ModelAndView("edit_leavetype");
		LeaveType leavetype= service.get(typeId);
		mav.addObject("leavetype", leavetype);		
		return mav;
	}
	@RequestMapping(value="/editleavetype/{typeId}",method=RequestMethod.POST)
	public ModelAndView editUser(@ModelAttribute @Valid LeaveType leavetype, BindingResult result, 
			@PathVariable("typeId") String typeId)
	{
		if (result.hasErrors()) {
			return new ModelAndView("edit_leavetype");
			}
		ModelAndView mav = new ModelAndView("forward:/leavetype");
		String message="employee was successfully updated";
		System.out.println(message);
		service.changeUser(leavetype);
		return mav;
	}
	
	
	@RequestMapping(value="/deleteleavetype/{typeId}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable("typeId") String typeId)
	{
		service.delete(typeId);
		
		return "redirect:/leavetype";
	}

}
