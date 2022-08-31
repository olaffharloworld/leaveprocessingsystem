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

import com.example.demo.model.LeaveBalance;
import com.example.demo.model.LeaveType;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.LeaveBalanceService;
import com.example.demo.service.LeaveTypeService;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import com.example.demo.validator.LeaveBalanceValidator;


@Controller
public class LeaveBalanceController {

	@Autowired 
	private LeaveBalanceService service;
	@Autowired 
	private LeaveTypeService ltservice;
	@Autowired
	private UserService uservice;
	@Autowired
	private RoleService rservice;
	@Autowired
	private LeaveBalanceValidator lbValidator;

	@InitBinder("leavebalance")
	private void initEmployeeBinder(WebDataBinder binder) {
		binder.addValidators(lbValidator);
	}
	@RequestMapping("/leavebalance")
	public String ViewBalancePage(Model model)
	{
		List<LeaveBalance> listLeaveBalances=service.listAll();
		model.addAttribute("listLeaveBalances", listLeaveBalances);
		
		return "leavebalancelist";
	}
	
	
	@RequestMapping("/newleavebalance")
	public String showNewLeaveBalanceForm(Model model) {
		LeaveBalance leavebalance = new LeaveBalance();
		model.addAttribute("leavebalance", leavebalance);
		List<User> listUsers=uservice.listAll();
		model.addAttribute("listUsers", listUsers);
		List<LeaveType> listleaveTypes=ltservice.listAll();
		model.addAttribute("listleaveTypes", listleaveTypes);
		return "newleavebalance";
	}
	
	/*
	 * @RequestMapping(value = "/create", method = RequestMethod.GET) public
	 * ModelAndView newDepartmentPage() { ModelAndView mav = new
	 * ModelAndView("department-new", "department", new Department());
	 * mav.addObject("eidlist", eService.findAllEmployeeIDs()); return mav; }
	 * 
	 * @RequestMapping(value = "/create", method = RequestMethod.POST) public
	 * ModelAndView createNewDepartment(@ModelAttribute @Valid Department
	 * department, BindingResult result) {
	 * 
	 * if (result.hasErrors()) return new ModelAndView("department-new");
	 * 
	 * ModelAndView mav = new ModelAndView("forward:/admin/department/list");
	 * dService.createDepartment(department); return mav; }
	 */
	
	/*
	 * @RequestMapping("/new") public String showNewUserForm(Model model) { User
	 * user=new User(); model.addAttribute("user", user); List<Role>
	 * listRoles=rservice.listAll(); model.addAttribute("listRoles",listRoles);
	 * return "newuser"; }
	 */
	
	/*
	 * @RequestMapping(value="/save",method = RequestMethod.POST) public String
	 * saveUser(@ModelAttribute("user") User user) { service.save(user); return
	 * "forward:/list"; }
	 */
	
	@RequestMapping(value = "/saveleavebalance", method = RequestMethod.POST)
	public String saveLeaveBalance(@ModelAttribute("leavebalance") @Valid LeaveBalance leavebalance,
			BindingResult result) {
		if (result.hasErrors()) {
			return "newleavebalance";
			}
		service.save(leavebalance);
		return "forward:/leavebalance";
	}


	@RequestMapping(value="/editeleavebalance/{userleaveid}",method=RequestMethod.GET)
	public ModelAndView showEditLeaveBalanceForm(@PathVariable("userleaveid") Integer userleaveid)
	{
		ModelAndView mav = new ModelAndView("edit_leavebalance");
		LeaveBalance leavebalance= service.get(userleaveid);
		mav.addObject("leavebalance", leavebalance);
		List<User> listUsers=uservice.listAll();
		mav.addObject("listUsers", listUsers);
		List<LeaveType> listleaveTypes=ltservice.listAll();
		mav.addObject("listleaveTypes", listleaveTypes);
		return mav;
	}
	
	@RequestMapping(value="/editeleavebalance/{userleaveid}",method=RequestMethod.POST)
	public ModelAndView editLeaveBalance(@PathVariable("userleaveid") Integer userleaveid,
			@ModelAttribute @Valid LeaveBalance leavebalance,BindingResult result )
	{
		if (result.hasErrors()) {
			return new ModelAndView("edit_leavebalance");
			}
		ModelAndView mav = new ModelAndView("forward:/leavebalance");
		String message="Leave Balance was successfully updated";
		System.out.println(message);
		service.changeUser(leavebalance);
		return mav;
	}
	
	
	@RequestMapping(value="/deleteleavebalance/{userleaveid}",method=RequestMethod.GET)
	public String deleteLeaveBalance(@PathVariable("userleaveid") Integer userleaveid)
	{
		service.delete(userleaveid);
		
		return "redirect:/leavebalance";
	}

}
