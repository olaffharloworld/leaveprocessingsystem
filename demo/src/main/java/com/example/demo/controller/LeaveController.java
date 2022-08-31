package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.model.Employee;
import com.example.demo.model.LeaveApplication;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.LeaveApplicationService;
import com.example.demo.service.LeaveTypeService;
import com.example.demo.validator.LeaveApplicationValidator;


@Controller
public class LeaveController {

	@Autowired
	private LeaveApplicationService laservice;
	@Autowired
	private LeaveTypeService ltservice;
	@Autowired
	private EmployeeService emservice;
	@Autowired
	private LeaveApplicationValidator laValidator;

	@InitBinder("leaveapplication")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.addValidators(laValidator);
	}
	

	@RequestMapping(value="/applyleave",method=RequestMethod.GET)
	public ModelAndView applyLeave(ModelAndView mav)
	{
		mav.addObject("leaveapplicaion",new LeaveApplication());
		mav.setViewName("applyleave");
		return mav;
		
	}
	
	@RequestMapping(value="/applyleave",method=RequestMethod.POST)
	public ModelAndView submitApplyLeave(ModelAndView mav,@Valid LeaveApplication leaveapplication, BindingResult result)
	{
		if (result.hasErrors()) {
			return new ModelAndView("applyleave");
			}
		Employee employee=emservice.GetEmloyee();
		leaveapplication.setEmployeeName(employee.getName());
		leaveapplication.setManagerName(employee.getManagerId());
		laservice.applyLeave(leaveapplication);
		mav.addObject("successMessage","Your Leave Request is registered");
		mav.setView(new RedirectView("/viewmyhistory"));
		return mav;
	}
	@RequestMapping(value="/viewmyhistory",method=RequestMethod.GET)
	public ModelAndView showMyLeaves(ModelAndView mav)
	{
		Employee employee=emservice.GetEmloyee();
		List<LeaveApplication> leavelist=laservice.getAllLeavesOfUser(employee.getName());
		mav.addObject("leaveList",leavelist);
		mav.setViewName("myleavehistory");
		return mav;
	}
	@RequestMapping(value="/viewsubordinate")
	public ModelAndView showcubordinate(ModelAndView mav)
	{
		String curmanagername=emservice.GetEmloyee().getEmployeeId();
		mav.addObject("leaveList",laservice.getAllNonActiveLeavesViaManager(curmanagername));
		mav.setViewName("viewsubordinate");
		return mav;
	}
	
	@RequestMapping(value="/editmyleave/{id}",method=RequestMethod.GET)
	public ModelAndView showEditUserForm(@PathVariable("id") int id)
	{
		ModelAndView mav = new ModelAndView("edit_myleaveapplication");
		LeaveApplication leaveapplication=laservice.findApplicationById(id);
		mav.addObject("leaveapplication", leaveapplication);		
		return mav;
	}
	@RequestMapping(value="/editmyleave/{id}",method=RequestMethod.POST)
	public ModelAndView editUser(@PathVariable("id") int id,@ModelAttribute @Valid LeaveApplication leaveapplication
			,BindingResult result)
	{
		if (result.hasErrors()) {
			return new ModelAndView("edit_myleaveapplication");
			}
		Employee employee=emservice.GetEmloyee();
		leaveapplication.setEmployeeName(employee.getName());
		leaveapplication.setManagerName(employee.getManagerId());
		ModelAndView mav = new ModelAndView("redirect:/viewmyhistory");
		String message="employee was successfully updated";
		System.out.println(message);
		laservice.updateLeave(leaveapplication);
		return mav;
		
		
		
	}
	@RequestMapping(value="/deletemyleave/{id}",method=RequestMethod.GET)
	public String deleteUser(@PathVariable("id") int id)
	{
		laservice.delete(id);
		
		return "forward:/viewmyhistory";
	}
	
	@RequestMapping(value="/manageleaves",method=RequestMethod.GET)
	public ModelAndView manageLeaves(ModelAndView mav)
	{
		String curmanagername=emservice.GetEmloyee().getEmployeeId();
		mav.addObject("leaveList",laservice.getAllActiveLeavesViaManager(curmanagername	));
		mav.setViewName("manageLeaves");
		return mav;
	}
	@RequestMapping(value="/manageleaves/{action}/{id}",method=RequestMethod.GET)
	public ModelAndView acceptOrRejectLeaves(ModelAndView mav,
			@PathVariable("action") String action, 
			@PathVariable("id") int id)
	{
		LeaveApplication leaveapplication=laservice.findApplicationById(id);
		if(action.equals("accept"))
		{
			leaveapplication.setAcceptRejectFlag(true);
			leaveapplication.setActive(false);
		}
		else if(action.equals("reject"))
		{
			leaveapplication.setAcceptRejectFlag(false);
			leaveapplication.setActive(false);
		}
		laservice.processLeave(leaveapplication);
		mav.addObject("successMessage","Updated Successfully!");
		mav.setView(new RedirectView("/manageleaves"));
		return mav;
	}
	
	
}
